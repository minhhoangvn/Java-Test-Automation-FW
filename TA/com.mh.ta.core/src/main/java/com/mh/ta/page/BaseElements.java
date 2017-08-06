package com.mh.ta.page;

import java.util.List;
import java.util.function.Supplier;

import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.SeleniumDriverFactory;
import com.mh.ta.interfaces.element.TAElement;
import com.mh.ta.keywords.TAWebKeywords;

public class BaseElements {

	protected TAWebKeywords keywords = ActionKeywords.WebUi();
	protected Supplier<SeleniumDriver> driver = () -> SeleniumDriverFactory.getSeleniumDriver();

	public BaseElements() {
	}

	public TAElement findElement(FindBy by) {
		return driver.get().findElement(by);
	}

	public List<TAElement> findListElement(FindBy by) {
		return driver.get().findListElement(by);
	}

	public TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime) {
		return driver.get().findElementUntilVisible(by, timeOut, pollingTime);
	}
}
