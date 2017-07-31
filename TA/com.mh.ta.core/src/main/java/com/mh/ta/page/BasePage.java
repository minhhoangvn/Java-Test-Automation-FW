package com.mh.ta.page;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.core.exception.InstantiationPageObjectException;
import com.mh.ta.core.helper.ClassInitializer;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.keywords.Keywords;

public class BasePage<E extends BaseElements, V extends BaseValidations<E>> {
	protected WebDriver driver;
	protected Keywords<?, ?> keyword;
	private Class<?> elements;
	private Class<?> valdidations;

	@Inject
	public BasePage(WebDriver driver, Keywords<?, ?> kw) {
		this.driver = driver;
		this.keyword = kw;
		this.initPageClass();
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

	@SuppressWarnings("unchecked")
	public V Validations() {
		try {
			return (V) this.valdidations.getDeclaredConstructor(new Class[] { WebDriver.class })
					.newInstance(this.driver);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException
				| InvocationTargetException | NoSuchMethodException e) {
			throw new InstantiationPageObjectException(
					String.format("Can not found page element with class name %s", this.valdidations.getName()), e,
					true, true);
		}
	}

	private void initPageClass() {
		Class<?> currentClass = getClass();
		this.elements = ClassInitializer.initClass().classInitializer(currentClass, Constant.BASE_ELEMENT_CLASS_NAME);
		this.valdidations = ClassInitializer.initClass().classInitializer(currentClass,
				Constant.BASE_VALIDATION_CLASS_NAME);
	}
}
