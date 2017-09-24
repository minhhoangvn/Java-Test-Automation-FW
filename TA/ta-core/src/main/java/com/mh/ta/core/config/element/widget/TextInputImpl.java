
package com.mh.ta.core.config.element.widget;

import org.openqa.selenium.WebElement;

import com.mh.ta.core.config.element.ElementImpl;

/**
 * @author minhhoang
 *
 */
class TextInputImpl extends ElementImpl implements TextInput {

	public TextInputImpl(WebElement element) {
		super(element);
	}

	@Override
	public String getTextInputValue() {
		return getWrappedElement().getAttribute("value");
	}

	@Override
	public void clearAndSetText(String text) {
		WebElement element = getWrappedElement();
		element.clear();
		element.sendKeys(text);
	}

	@Override
	public void clearText() {
		getWrappedElement().clear();
	}

}
