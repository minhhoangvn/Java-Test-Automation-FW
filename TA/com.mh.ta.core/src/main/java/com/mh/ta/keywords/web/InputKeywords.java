package com.mh.ta.keywords.web;

import com.mh.ta.interfaces.element.TAElement;

class InputKeywords {

	public void inputText(TAElement element, String text) {
		ElementResolvedServices.resolved(element).sendKeys(text);
	}

	public String getInputValue(TAElement element) {
		return ElementResolvedServices.resolved(element).getAttribute("value");
	}
}
