package com.mh.ta.base.selenium;

import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.DriverFactory;
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
		return finder.findElement(by);
	}

	@Override
	public List<TAElement> findListElement(FindBy by) {
		return finder.findListElement(by);
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

	@HighLightElement
	@Override
	public void inputTextToElement(String value) {
		parentElement.sendKeys(value);

	}

	@Override
	public String getElementText() {
		return parentElement.getText();
	}

	@Override
	public String getInputValue() {
		return parentElement.getAttribute("value");
	}

	@Override
	public void selectByText(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectByIndex(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getListSelectOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TAElement getSelectedElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deselectAllOptions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectAllOptions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickElement() {
		this.parentElement.click();
	}

	@Override
	public void mouseClickElement() {
		// TODO Auto-generated method stub

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
}
