
package com.mh.ta.core.config.element.widget;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.mh.ta.core.config.element.ElementImpl;

/**
 * @author minhhoang
 *
 */
class SelectImpl extends ElementImpl implements Select {
	private org.openqa.selenium.support.ui.Select selectElement;

	public SelectImpl(WebElement element) {
		super(element);
		this.selectElement = new org.openqa.selenium.support.ui.Select(element);
	}

	@Override
	public boolean isMultiple() {
		return selectElement.isMultiple();
	}

	@Override
	public void deselectByIndex(int index) {
		selectElement.deselectByIndex(index);

	}

	@Override
	public void selectByValue(String value) {
		selectElement.selectByValue(value);

	}

	@Override
	public WebElement getFirstSelectedOption() {
		return selectElement.getFirstSelectedOption();
	}

	@Override
	public void selectByVisibleText(String text) {
		selectElement.selectByVisibleText(text);

	}

	@Override
	public void deselectByValue(String value) {
		selectElement.deselectByValue(value);
	}

	@Override
	public void deselectAll() {
		selectElement.deselectAll();
	}

	@Override
	public List<WebElement> getAllSelectedOptions() {
		return selectElement.getAllSelectedOptions();
	}

	@Override
	public List<WebElement> getOptions() {
		return selectElement.getOptions();
	}

	@Override
	public void deselectByVisibleText(String text) {
		selectElement.deselectByVisibleText(text);
	}

	@Override
	public void selectByIndex(int index) {
		selectElement.selectByIndex(index);
	}

	@Override
	public List<String> getListOptionsText() {
		return selectElement.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());

	}

}
