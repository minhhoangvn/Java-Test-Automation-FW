package com.mh.ta.guice.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.google.inject.name.Named;

@Guice(modules = { WebDriversModules.class })
public class TestGuiceDriverFactory {
	@Inject
	@Named("Port1")
	int Port1;

	@Inject
	@Named("Port2")
	int Port2;

	@Inject
	@Named("Chrome")
	WebDrivers chrome;

	@Inject
	@Named("Firefox")
	WebDrivers firefox;

	@Test
	public void testChrome() throws InterruptedException {
		WebDriver c = chrome.getDriver();
		c.get("http://www.gooogle.com");
		c.get("http://vtc.vn");
		Thread.sleep(5000);
		c.get("http://www.google.com");
		c.findElement(By.id("lst-ib")).sendKeys("Test Guice");
		chrome.diposeDriver();
	}

	@Test
	public void testFirefox() throws InterruptedException {
		WebDriver c = firefox.getDriver();
		c.get("http://www.gooogle.com");
		c.get("http://vtc.vn");
		Thread.sleep(2000);
		c.get("http://www.google.com");
		c.findElement(By.id("lst-ib")).sendKeys("Test Guice");
		firefox.diposeDriver();
	}
}
