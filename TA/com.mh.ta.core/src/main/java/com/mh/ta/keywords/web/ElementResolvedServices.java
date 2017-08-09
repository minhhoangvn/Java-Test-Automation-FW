package com.mh.ta.keywords.web;

import org.openqa.selenium.WebElement;

import com.mh.ta.interfaces.element.TAElement;

class ElementResolvedServices {

	public static WebElement resolved(TAElement element) {
		return (WebElement) element.getElement();
	}
}
