
package com.mh.ta.core.config.element.widget;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.config.element.ElementImpl;

/**
 * @author minhhoang
 *
 */
class ButtonImpl extends ElementImpl implements Button {

	public ButtonImpl(WebElement element) {
		super(element);
	}

	@Override
	public void mouseClick() {
		Actions action = new Actions(DriverFactory.instance().getDriver());
		action.moveToElement(getWrappedElement()).click().build().perform();
	}

	@Override
	public void javascriptClick() {
		Driver driver = DriverFactory.instance().getDriver();
		driver.executeJavaScript("arguments[0].click();", getWrappedElement());
	}

	@Override
	public void clickToElement() {
		getWrappedElement().click();
	}

}
