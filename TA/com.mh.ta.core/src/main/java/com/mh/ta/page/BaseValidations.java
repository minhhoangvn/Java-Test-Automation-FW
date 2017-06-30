package com.mh.ta.page;

import org.openqa.selenium.WebDriver;

import com.mh.ta.core.exception.InstantiationPageObjectException;
import com.mh.ta.core.factory.DriverFactory;

public class BaseValidations<E extends BaseElements> {
	protected WebDriver driver;
	private Class<E> elements;

	public BaseValidations(Class<E> element) {
		System.err.println("Init In Base Validation ");
		System.err.println(element);
		this.driver = DriverFactory.getDriver();
		this.elements = element;
	}

	protected E Elements() {
		try {
			return (E) this.elements.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new InstantiationPageObjectException(
					String.format("Can not found page element with class name %s", this.elements.getName()));
		}
	}

}
