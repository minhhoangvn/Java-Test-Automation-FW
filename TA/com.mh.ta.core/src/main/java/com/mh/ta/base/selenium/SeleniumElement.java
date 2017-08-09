package com.mh.ta.base.selenium;

import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.mh.ta.base.selenium.services.SeleniumElementFinder;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.interfaces.element.TAElement;

public class SeleniumElement implements TAElement {
	protected WebElement parentElement;
	private SeleniumElementFinder finder = GuiceInjectFactory.instance().getObjectInstance(SeleniumElementFinder.class);

	@Inject
	public SeleniumElement(@Assisted WebElement element) {
		this.parentElement = element;
	}

	public WebElement getParentElement() {
		return parentElement;
	}

	public void setParentElement(WebElement parentElement) {
		this.parentElement = parentElement;
	}

	@Override
	public TAElement findElement(FindBy by) {
		return finder.findElementContex(this.parentElement, by);
	}

	@Override
	public List<TAElement> findListElement(FindBy by) {
		return finder.findListElementContex(this.parentElement, by);
	}

	@Override
	public String getElementAttribute(String key) {
		return this.parentElement.getAttribute(key);
	}

	@Override
	public String getElementInnerText() {
		return this.parentElement.getAttribute("innerHTML");
	}

	@Override
	public boolean isElementDisplayed() {
		return this.parentElement.isDisplayed();
	}

	@Override
	public void setElementAttribute(String key, String value) {
		SeleniumDriver driver = (SeleniumDriver) DriverFactory.getDriver();
		String js = format("arguments[0].setAttribute('%s',arguments[1]);", key);
		driver.executeJavaScript(js, parentElement, value);
	}

	@Override
	public boolean isElementEnable() {
		return this.parentElement.isEnabled();
	}


	@Override
	public String getElementText() {
		return parentElement.getText();
	}

	@Override
	public void highlightElement(String borderColor, String backgroundColor, int timeOut) {
		SeleniumDriver driver = (SeleniumDriver) DriverFactory.getDriver();
		String orig = this.parentElement.getAttribute("style");
		driver.executeJavaScript(format("arguments[0].setAttribute('%s',arguments[1]);", "style"), this.parentElement,
				format("border: 3px solid %s; background-color: %s;", borderColor, backgroundColor));
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException ignore) {
		}
		driver.executeJavaScript(format("arguments[0].setAttribute('%s',arguments[1]);", "style"), this.parentElement,
				orig);
	}

	@Override
	public TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime) {
		return finder.findElementUntilVisible(by, timeOut, pollingTime);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getElement() {
		return (T) this.parentElement;
	}
}
