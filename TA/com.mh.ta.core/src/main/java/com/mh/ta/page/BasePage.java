package com.mh.ta.page;

import java.util.function.Supplier;

import com.google.inject.Inject;
import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.helper.ClassInitializer;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.keywords.web.WebKeywords;

public class BasePage<E extends BaseElements, V extends BaseValidations<E>> {

	private Class<?> elements;
	private Class<?> valdidations;
	@Inject
	@ApplicationConfig
	protected MainConfig config;
	protected WebKeywords keywords = ActionKeywords.WebUi();
	protected Supplier<SeleniumDriver> driver = () -> {
		return (SeleniumDriver) DriverFactory.getDriver();
	};

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
