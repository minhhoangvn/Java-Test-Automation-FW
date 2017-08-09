package com.mh.ta.keywords.web;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mh.ta.base.selenium.services.SeleniumElementFactory;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.interfaces.element.TAElement;

class ComboboxKeywords {
	SeleniumElementFactory elementFactory = GuiceInjectFactory.instance()
			.getObjectInstance(SeleniumElementFactory.class);

	public void selectOptionByText(TAElement element, String options) {
		Select select = new Select(ElementResolvedServices.resolved(element));
		select.selectByVisibleText(options);
	}

	public void selectOptionByIndex(TAElement element, int options) {
		Select select = new Select(ElementResolvedServices.resolved(element));
		select.selectByIndex(options);
	}

	public void deselectAllOption(TAElement element) {
		Select select = new Select(ElementResolvedServices.resolved(element));
		select.deselectAll();
	}

	public List<String> getListOptionsText(TAElement element) {
		Select select = new Select(ElementResolvedServices.resolved(element));
		return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public TAElement getSelectedElement(TAElement element) {
		Select select = new Select(ElementResolvedServices.resolved(element));
		return elementFactory.create(select.getFirstSelectedOption());
	}
}
