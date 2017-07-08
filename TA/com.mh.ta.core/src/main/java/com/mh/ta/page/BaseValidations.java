package com.mh.ta.page;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.core.exception.InstantiationPageObjectException;
import com.mh.ta.core.helper.ClassInitializer;
import com.mh.ta.core.helper.Constant;

public class BaseValidations<E extends BaseElements> {
	protected WebDriver driver;
	private Class<?> elements;

	@Inject
	public BaseValidations(WebDriver driver) {
		this.driver = driver;
		this.initElementClass();
	}

	@SuppressWarnings("unchecked")
	protected E Elements() {
		try {
			return (E) this.elements.getDeclaredConstructor(new Class[] { WebDriver.class }).newInstance(this.driver);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InstantiationPageObjectException(
					String.format("Can not found page element with class name %s", this.elements.getName()), e, true,
					true);
		}
	}

	private void initElementClass() {
		Class<?> currentClass = getClass();
		this.elements = ClassInitializer.initClass().classInitializer(currentClass, Constant.BASE_ELEMENT_CLASS_NAME);
	}

}
