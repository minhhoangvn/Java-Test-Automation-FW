package com.mh.ta.keywords.web;

import org.openqa.selenium.interactions.Actions;

import com.mh.ta.factory.DriverFactory;
import com.mh.ta.interfaces.element.TAElement;

class ButtonKeywords {

	public void clickToElement(TAElement element) {
		ElementResolvedServices.resolved(element).click();
	}

	public void hoverMouseToElement(TAElement element) {
		Actions action = DriverFactory.getDriver().getWindowsAction();
		action.moveToElement(ElementResolvedServices.resolved(element)).build().perform();
	}
}
