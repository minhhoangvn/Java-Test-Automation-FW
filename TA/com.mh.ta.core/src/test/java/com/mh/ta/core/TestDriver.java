package com.mh.ta.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mh.ta.core.config.MainConfigModule;
import com.mh.ta.core.driver.WebDrivers;

@Guice(modules = { MainConfigModule.class })
public class TestDriver {
	@Inject
	@Named("Firefox")
	private WebDrivers firefox;

	@Inject
	@Named("Chrome")
	private WebDrivers chrome;
	// private WebDrivers driver;

	@Test
	public void testChromeDriver() {

		WebDriver c = chrome.getDriver();
		c.get("http://google.com");
		System.err.println("Test Chrome Driver " + c.getCurrentUrl() + " title " + c.getTitle());
		chrome.diposeDriver();
		// driver.diposeDriver();
	}

	@Test
	public void testFirefoxDriver() {
		WebDriver c = firefox.getDriver();
		c.get("http://google.com");
		System.err.println("Test Firefox Driver " + c.getCurrentUrl() + " title " + c.getTitle());
		firefox.diposeDriver();
	}

}
