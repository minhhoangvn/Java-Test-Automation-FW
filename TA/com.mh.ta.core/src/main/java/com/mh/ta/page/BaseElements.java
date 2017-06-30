package com.mh.ta.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mh.ta.core.factory.DriverFactory;

public class BaseElements {
	protected WebDriver driver;

	public BaseElements() {
		this.driver = DriverFactory.getDriver();
	}

	public WebElement findElementUntilVisible(By by, long timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(this.driver).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(pollingTime, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, WebDriverException.class);
		return wait.until((driver) -> {
			WebDriverWait explicit = new WebDriverWait(driver,timeOut);
			return explicit.until(ExpectedConditions.visibilityOfElementLocated(by));
		});
	}
}
