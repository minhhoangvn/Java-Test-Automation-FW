
package com.mh.ta.core.config.driver.services.internal;

import static com.mh.ta.core.config.driver.services.internal.ImplementedByProcessor.getWrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.mh.ta.core.config.element.Element;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
class ElementHandler implements InvocationHandler {
	private final ElementLocator locator;
	private final Class<?> wrappingType;

	public <T> ElementHandler(Class<T> interfaceType, ElementLocator locator) {
		this.locator = locator;
		boolean isWebElementType = WebElement.class.isAssignableFrom(interfaceType);
		boolean isWebElementDecorateType = Element.class.isAssignableFrom(interfaceType);		
		if (!(isWebElementType || isWebElementDecorateType)) {
			throw new TestContextException("interface type not assignable to Field in Page Object.");
		}
		this.wrappingType = getWrapperClass(interfaceType);
	}

	@Override
	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		final WebElement element;
		try {
			element = locator.findElement();
		} catch (NoSuchElementException e) {
			if ("toString".equals(method.getName())) {
				return "Proxy element for: " + locator.toString();
			}
			throw e;
		}
		if ("getWrappedElement".equals(method.getName())) {
			return element;
		}
		Constructor<?> cons = wrappingType.getConstructor(WebElement.class);
		cons.setAccessible(true);
		Object thing = cons.newInstance(element);
		try {
			return method.invoke(wrappingType.cast(thing), objects);
		} catch (InvocationTargetException e) {
			// Unwrap the underlying exception
			throw e.getCause();
		}
	}
}
