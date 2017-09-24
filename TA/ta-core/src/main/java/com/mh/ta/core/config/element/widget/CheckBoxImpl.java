
package com.mh.ta.core.config.element.widget;

import org.openqa.selenium.WebElement;

import com.mh.ta.core.config.element.ElementImpl;

/**
 * @author minhhoang
 *
 */
class CheckBoxImpl extends ElementImpl implements CheckBox {

	public CheckBoxImpl(WebElement element) {
		super(element);
	}

	@Override
	public void toggle() {
		getWrappedElement().click();
	}

	@Override
	public void check() {
		if (!isChecked()) {
			toggle();
		}
	}

	@Override
	public void uncheck() {
		if (isChecked()) {
			toggle();
		}
	}

	@Override
	public boolean isChecked() {
		return getWrappedElement().isSelected();
	}

}
