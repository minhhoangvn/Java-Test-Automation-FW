
package com.mh.ta.core.config.element.widget;

import org.openqa.selenium.WebElement;

import com.mh.ta.core.config.element.ElementImpl;

/**
 * @author minhhoang
 *
 */
class LabelImpl extends ElementImpl implements Label {

	public LabelImpl(WebElement element) {
		super(element);
	}

	@Override
	public String getFor() {
		return getWrappedElement().getAttribute("for");
	}

	@Override
	public String getStyle() {
		return getWrappedElement().getAttribute("style");
	}

	@Override
	public String getInnerText() {
		return getWrappedElement().getAttribute("innerText");
	}	
}
