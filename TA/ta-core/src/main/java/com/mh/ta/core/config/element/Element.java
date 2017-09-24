
package com.mh.ta.core.config.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import com.google.inject.ImplementedBy;

/**
 * @author minhhoang
 *
 */
@ImplementedBy(ElementImpl.class)
public interface Element extends WebElement, WrapsElement, Locatable {
	boolean elementWired();

	public void highlightElement(String borderColor, String backgroundColor, int timeOut);

	public void hoverElement();
	
	public void setElementAttribute(String key, String value);
	
	public String getElementValue();
}
