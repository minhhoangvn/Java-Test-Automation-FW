package com.mh.ta.core.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriverManager;

public class BrowserDriverManagerFactory implements DriverManagerFactory<BrowserDriverManager, WebDriver, Browser> {
	final Map<Browser, BrowserDriverManager> browserManagerBinder;

	public BrowserDriverManagerFactory() {
		try {
			browserManagerBinder = new HashMap<Browser, BrowserDriverManager>();
			Browser[] browsers = Browser.values();
			for (Browser browser : browsers) {
				Class<?> cls = Browser.getBrowserClass(browser.toString());
				BrowserDriverManager driverManager = (BrowserDriverManager) cls.newInstance();
				this.browserManagerBinder.put(browser, driverManager);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error in init BrowserDriverFactory " + e);
		}
	}

	@Inject
	public BrowserDriverManagerFactory(Map<Browser, BrowserDriverManager> mapBinder) {
		this.browserManagerBinder = mapBinder;
	}

	@Override
	public WebDriver getDriver(Browser type) {
		WebDriver driver = this.browserManagerBinder.get(type).getDriver();
		return driver;
	}

	@Override
	public BrowserDriverManager getDriverManager(Browser type) {
		BrowserDriverManager driverManager = this.browserManagerBinder.get(type);
		return driverManager;
	}

}
