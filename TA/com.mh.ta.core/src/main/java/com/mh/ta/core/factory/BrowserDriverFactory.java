package com.mh.ta.core.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriver;

public class BrowserDriverFactory implements DriverFactory<BrowserDriver, WebDriver, Browser> {
	final Map<Browser, BrowserDriver> browserManagerBinder;

	public BrowserDriverFactory() {
		try {
			browserManagerBinder = new HashMap<Browser, BrowserDriver>();
			Browser[] browsers = Browser.values();
			for (Browser browser : browsers) {
				Class<?> cls = Browser.getBrowserClass(browser.toString());
				BrowserDriver driverManager = (BrowserDriver) cls.newInstance();
				this.browserManagerBinder.put(browser, driverManager);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error in init BrowserDriverFactory " + e);
		}
	}

	@Inject
	public BrowserDriverFactory(Map<Browser, BrowserDriver> mapBinder) {
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
