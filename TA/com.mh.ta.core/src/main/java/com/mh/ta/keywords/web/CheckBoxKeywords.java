package com.mh.ta.keywords.web;

import com.mh.ta.interfaces.element.TAElement;

class CheckBoxKeywords {
	public void checkOption(TAElement element) {
		if (ElementResolvedServices.resolved(element).isSelected() == false)
			ElementResolvedServices.resolved(element).click();
	}

	public void uncheckOption(TAElement element) {
		if (ElementResolvedServices.resolved(element).isSelected() == true)
			ElementResolvedServices.resolved(element).click();
	}
}
