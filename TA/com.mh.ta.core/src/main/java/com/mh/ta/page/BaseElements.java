package com.mh.ta.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.keywords.WebKeywords;

public class BaseElements {

	protected WebKeywords keywords = ActionKeywords.WebUI();
	protected Supplier<WebDriver> driver = () -> DriverFactory.getDriver();

	public BaseElements() {
	}

	public WebElement findElementUntilVisible(By by, long timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver.get()).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(pollingTime, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, WebDriverException.class);
		return wait.until((driver) -> {
			WebDriverWait explicit = new WebDriverWait(driver, timeOut);
			return explicit.until(ExpectedConditions.visibilityOfElementLocated(by));
		});
	}

	public WebElement findElement(By by) {
		return driver.get().findElement(by);
	}

	public List<WebElement> findListElement(By by) {
		return driver.get().findElements(by);
	}
}
