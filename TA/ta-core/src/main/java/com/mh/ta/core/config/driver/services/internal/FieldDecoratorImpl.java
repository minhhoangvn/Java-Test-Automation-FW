
package com.mh.ta.core.config.driver.services.internal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.annotation.FindByUntil;
import com.mh.ta.core.config.driver.services.ElementLocatorFactoryImpl;
import com.mh.ta.core.config.element.Element;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
public class FieldDecoratorImpl implements FieldDecorator {

	protected ElementLocatorFactory factory;

	public FieldDecoratorImpl() {
		factory = GuiceInjectFactory.instance().createObjectInstance(ElementLocatorFactoryImpl.class);
	}

	@Override
	public Object decorate(ClassLoader loader, Field field) {

		if (!elementField(field)) {
			return null;
		}

		if (!field.getType().isInterface()) {
			throw new TestContextException("Field element is not interface type. Please check field " + field.getName()
					+ " in " + field.getDeclaringClass());
		}

		ElementLocator locator = factory.createLocator(field);
		if (locator == null) {
			return null;
		}

		Class<?> fieldType = field.getType();
		if (WebElement.class.equals(fieldType)) {
			fieldType = Element.class;
		}

		if (WebElement.class.isAssignableFrom(fieldType)) {
			return proxyForLocator(loader, fieldType, locator);
		} else if (List.class.isAssignableFrom(fieldType)) {
			Class<?> erasureClass = getErasureClass(field);
			return proxyForListLocator(loader, erasureClass, locator);
		} else {
			return null;
		}
	}

	private boolean elementField(Field field) {
		boolean isElementField = WebElement.class.isAssignableFrom(field.getType());
		boolean isListField = isDecoratableList(field);
		return isElementField || isListField;
	}

	private Class<?> getErasureClass(Field field) {
		// Type erasure in Java isn't complete. Attempt to discover the generic
		// interfaceType of the list.
		Type genericType = field.getGenericType();
		if (!(genericType instanceof ParameterizedType)) {
			return null;
		}
		return (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
	}

	private boolean isDecoratableList(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) {
			return false;
		}

		Class<?> erasureClass = getErasureClass(field);
		if (erasureClass == null) {
			return false;
		}

		if (!WebElement.class.isAssignableFrom(erasureClass)) {
			return false;
		}	
		return !isNullAnnotationPageObjectFind(field);
	}

	private boolean isNullAnnotationPageObjectFind(Field field) {
		boolean isFindBy = field.getAnnotation(FindBy.class) == null;
		boolean isFindBys = field.getAnnotation(FindBys.class) == null;
		boolean isFindAll = field.getAnnotation(FindAll.class) == null;
		boolean isFindByUntil = field.getAnnotation(FindByUntil.class) == null;
		return isFindBy && isFindBys && isFindAll && isFindByUntil;
	}

	protected <T> T proxyForLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
		InvocationHandler handler = new ElementHandler(interfaceType, locator);

		T proxy;
		proxy = interfaceType.cast(Proxy.newProxyInstance(loader,
				new Class[] { interfaceType, WebElement.class, WrapsElement.class, Locatable.class }, handler));
		return proxy;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
		InvocationHandler handler = new ElementListHandler(interfaceType, locator);
		List<T> proxy;
		proxy = (List<T>) Proxy.newProxyInstance(loader, new Class[] { List.class }, handler);
		return proxy;
	}

}
