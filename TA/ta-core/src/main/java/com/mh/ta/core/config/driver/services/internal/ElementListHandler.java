
package com.mh.ta.core.config.driver.services.internal;

import static com.mh.ta.core.config.driver.services.internal.ImplementedByProcessor.getWrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.mh.ta.core.config.element.Element;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
class ElementListHandler implements InvocationHandler {
	private final ElementLocator locator;
	private final Class<?> wrappingType;

	public <T> ElementListHandler(Class<T> interfaceType, ElementLocator locator) {
		this.locator = locator;
		boolean isWebElementType = WebElement.class.isAssignableFrom(interfaceType);
		boolean isWebElementDecorateType = Element.class.isAssignableFrom(interfaceType);		
		if (!(isWebElementType || isWebElementDecorateType)) {
			throw new TestContextException("interface type not assignable to List<T> Field in Page Object.");
		}
		this.wrappingType = getWrapperClass(interfaceType);
	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		List<Object> wrappedList = new ArrayList<>();
		Constructor<?> cons = wrappingType.getConstructor(WebElement.class);
		for (WebElement element : locator.findElements()) {
			cons.setAccessible(true);
			Object thing = cons.newInstance(element);
			wrappedList.add(wrappingType.cast(thing));
		}
		try {
			return method.invoke(wrappedList, objects);
		} catch (InvocationTargetException e) {
			// Unwrap the underlying exception
			throw e.getCause();
		}
	}
}
