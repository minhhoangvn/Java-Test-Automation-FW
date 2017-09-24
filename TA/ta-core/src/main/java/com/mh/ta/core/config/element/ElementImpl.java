
package com.mh.ta.core.config.element;

import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.config.settings.DriverConfig;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.helper.SystemUtility;

/**
 * @author minhhoang
 *
 */
public class ElementImpl implements Element {
	private final WebElement parentElement;

	@Inject
	public ElementImpl(@Assisted final WebElement element) {
		this.parentElement = element;
		runHighlightElement();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) {
		return parentElement.getScreenshotAs(target);
	}

	@Override
	public void click() {
		parentElement.click();
	}

	@Override
	public void submit() {
		parentElement.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		parentElement.sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		parentElement.clear();
	}

	@Override
	public String getTagName() {
		return parentElement.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return parentElement.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return parentElement.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return parentElement.isEnabled();
	}

	@Override
	public String getText() {
		return parentElement.getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return parentElement.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return parentElement.findElement(by);
	}

	@Override
	public boolean isDisplayed() {
		return parentElement.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return parentElement.getLocation();
	}

	@Override
	public Dimension getSize() {
		return parentElement.getSize();
	}

	@Override
	public Rectangle getRect() {
		return parentElement.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		return parentElement.getCssValue(propertyName);
	}

	@Override
	public WebElement getWrappedElement() {
		return parentElement;
	}

	@Override
	public Coordinates getCoordinates() {
		return ((Locatable) parentElement).getCoordinates();
	}

	@Override
	public boolean elementWired() {
		return (parentElement != null);
	}

	@Override
	public void hoverElement() {
		Driver driver = DriverFactory.instance().getDriver();
		Actions actions = driver.createWindowAction();
		actions.moveToElement(getWrappedElement()).perform();
	}

	@Override
	public void highlightElement(String borderColor, String backgroundColor, int timeOut) {
		String styleString = "style";
		Driver driver = DriverFactory.instance().getDriver();
		String orig = this.parentElement.getAttribute(styleString);
		driver.executeJavaScript(format("arguments[0].setAttribute('%s',arguments[1]);", styleString),
				this.parentElement,
				format("border: 3px solid %s; background-color: %s;", borderColor, backgroundColor));
		SystemUtility.sleep(timeOut);
		driver.executeJavaScript(format("arguments[0].setAttribute('%s',arguments[1]);", styleString),
				this.parentElement, orig);
	}

	@Override
	public void setElementAttribute(String key, String value) {
		String sourceJsScript = "arguments[0].setAttribute(arguments[1], arguments[2]);";
		Driver driver = DriverFactory.instance().getDriver();
		driver.executeJavaScript(sourceJsScript, parentElement, key, value);
	}

	@Override
	public String getElementValue() {
		return this.parentElement.getAttribute("value");

	}

	private void runHighlightElement() {
		MainConfig config = GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class,
				ApplicationConfig.class);
		DriverConfig driverConfig = config.getDriverConfig();
		boolean isHighLigh = driverConfig.isHighLighElement();
		if (isHighLigh) {
			highlightElement(driverConfig.getBorderColor(), driverConfig.getBackGroundColor(),
					driverConfig.getTimeOutHighlight());
		}
	}
}
