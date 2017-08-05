package com.mh.ta.keywords;

import static java.lang.String.format;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.factory.WebDriverFactory;

public class WebKeywords {
	private WebDriver driver = WebDriverFactory.getDriver();

	private JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

	@HighLightElement
	public void clickToElement(WebElement element) {
		element.click();
	}

	@HighLightElement
	public void inputText(WebElement element, String text) {
		element.sendKeys(text);
	}

	public String getElementAttribute(WebElement element, String key) {
		return element.getAttribute(key);
	}

	public void setElementAttribute(WebElement element, String key, String value) {
		jsExecutor.executeScript(format("arguments[0].setAttribute('%s',arguments[1]);", key), element, value);
	}

	public void sleepInMilliseconds(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException ignore) {

		}
	}
}
