package com.mh.ta.core.core.element;

import java.util.concurrent.TimeUnit;

public interface Element extends ElementFinder<Element>{
	void click();

	void mosueClick();

	String getElementAttribute(String value);

	void setElementAttribute(String attributeName, String value);

	String getElementText();

	void setText(CharSequence... value);

	String getInnerHTMLString();

	void waitForElementVisible(int timeout, TimeUnit unit);

	void waitForElementPresent(int timeout, TimeUnit unit);

	Boolean isVisible();
}
