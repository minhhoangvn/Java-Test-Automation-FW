package com.mh.ta.page;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

import com.mh.ta.core.exception.InstantiationPageObjectException;
import com.mh.ta.core.factory.DriverFactory;

public class BasePage<E extends BaseElements, V extends BaseValidations<E>> {
	protected WebDriver driver;
	private Class<E> elements;
	private Class<V> valdidations;

	public BasePage(Class<E> element, Class<V> validation) {
		//this.driver = DriverFactory.getDriver();
		this.elements = element;
		this.valdidations = validation;
	}

	protected E Elements() {
		try {
			return (E) this.elements.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new InstantiationPageObjectException(
					String.format("Can not found page element with class name %s", this.elements.getName()), e, true,
					true);
		}
	}

	public V Validations() {
		try {
			return (V) this.valdidations.getDeclaredConstructor(new Class[] { this.elements.getClass() })
					.newInstance(this.elements.getClass());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InstantiationPageObjectException(
					String.format("Can not found page element with class name %s", this.valdidations.getName()), e,
					true, true);
		}
	}
}
