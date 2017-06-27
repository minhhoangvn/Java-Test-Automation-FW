package com.mh.ta.core;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.core.webdriver.ChromeBrowser;

public class TestDriver {
	private WebDriver driver = null;

	@After
	public void closeDriver() {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void testInstanceDriver() {
		ChromeBrowser chrome = new ChromeBrowser();
		driver = chrome.startDriver();
		assertNotNull(driver);

	}
}
