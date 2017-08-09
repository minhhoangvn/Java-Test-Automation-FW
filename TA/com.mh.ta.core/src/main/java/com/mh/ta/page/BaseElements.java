package com.mh.ta.page;

import java.util.List;
import java.util.function.Supplier;

import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.interfaces.element.TAElement;
import com.mh.ta.keywords.web.WebKeywords;

public class BaseElements {

	protected WebKeywords keywords = ActionKeywords.WebUi();
	protected Supplier<SeleniumDriver> driver = () -> {
		return (SeleniumDriver) DriverFactory.getDriver();
	};

	public BaseElements() {
	}

	protected TAElement findElement(FindBy by) {
		return driver.get().findElement(by);
	}

	protected List<TAElement> findListElement(FindBy by) {
		return driver.get().findListElement(by);
	}

	protected TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime) {
		return driver.get().findElementUntilVisible(by, timeOut, pollingTime);
	}
}
