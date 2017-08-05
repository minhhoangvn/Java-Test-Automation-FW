package com.mh.ta.page;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;

import com.mh.ta.core.helper.ClassInitializer;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.keywords.WebKeywords;

public class BasePage<E extends BaseElements, V extends BaseValidations<E>> {

	private Class<?> elements;
	private Class<?> valdidations;

	protected WebKeywords keywords = ActionKeywords.WebUI();
	protected Supplier<WebDriver> driver = () -> DriverFactory.getDriver();

	public BasePage() {
		this.initPageClass();
	}

	@SuppressWarnings("unchecked")
	protected E Elements() {
		return (E) GuiceInjectFactory.instance().getObjectInstance(this.elements);
	}

	@SuppressWarnings("unchecked")
	public V Validations() {
		return (V) GuiceInjectFactory.instance().getObjectInstance(this.valdidations);
	}

	private void initPageClass() {
		Class<?> currentClass = getClass();
		this.elements = ClassInitializer.initClass().classInitializer(currentClass, Constant.BASE_ELEMENT_CLASS_NAME);
		this.valdidations = ClassInitializer.initClass().classInitializer(currentClass,
				Constant.BASE_VALIDATION_CLASS_NAME);
	}
}
