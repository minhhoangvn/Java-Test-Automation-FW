package com.mh.ta.page;	

import org.openqa.selenium.WebDriver;

import com.mh.ta.core.helper.ClassInitializer;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.WebDriverFactory;
import com.mh.ta.keywords.WebKeywords;

public class BaseValidations<E extends BaseElements> {

	private Class<?> elements;
	protected WebKeywords keywords = ActionKeywords.WebUI();
	protected WebDriver driver = WebDriverFactory.getDriver();

	public BaseValidations() {
		this.initElementClass();
	}

	@SuppressWarnings("unchecked")
	protected E Elements() {
		return (E) GuiceInjectFactory.instance().getObjectInstance(this.elements);
	}

	private void initElementClass() {
		Class<?> currentClass = getClass();
		this.elements = ClassInitializer.initClass().classInitializer(currentClass, Constant.BASE_ELEMENT_CLASS_NAME);
	}

}
