package com.mh.ta.interfaces.element;

import com.mh.ta.interfaces.driver.IFinder;

public interface TAElement extends IFinder, InputElement, SelectElement, ButtonElement {

	public String getElementAttribute(String key);

	public String getElementInnerText();

	public String getElementText();

	public boolean isElementDisplayed();

	public void setElementAttribute(String key, String value);

	public boolean isElementEnable();

	public void highlightElement(String borderColor, String backgroundColor, int timeOut);
}
