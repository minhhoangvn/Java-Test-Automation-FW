package com.mh.ta.core.factory;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriver;

public class WebDriverFactory implements DriverFactory<BrowserDriver, WebDriver, Browser> {
	final Map<Browser, BrowserDriver> browserManagerBinder;

	@Inject
	public WebDriverFactory(Map<Browser, BrowserDriver> mapBinder) {
		this.browserManagerBinder = mapBinder;
	}

	@Override
	public WebDriver getDriver(Browser type) {
		WebDriver driver = this.browserManagerBinder.get(type).getDriver();
		return driver;
	}

	@Override
	public BrowserDriver getDriverManager(Browser type) {
		BrowserDriver driverManager = this.browserManagerBinder.get(type);
		return driverManager;
	}

}
