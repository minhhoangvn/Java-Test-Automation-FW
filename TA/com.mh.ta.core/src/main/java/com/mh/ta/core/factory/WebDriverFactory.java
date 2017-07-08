package com.mh.ta.core.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriver;

public class WebDriverFactory implements DriverFactory<BrowserDriver, WebDriver, Browser> {
	final Map<Browser, BrowserDriver> browserManagerBinder;
	private Map<Browser, BrowserDriver> browserMap = new HashMap<Browser, BrowserDriver>();

	@Inject
	public WebDriverFactory(Map<Browser, BrowserDriver> mapBinder) {
		this.browserManagerBinder = mapBinder;
	}

	@Override
	public void createDriverManager(Browser browser) {
		if (!browserMap.containsKey(browser)) {
			BrowserDriver browserDriverManager = this.browserManagerBinder.get(browser);
			browserMap.put(browser, browserDriverManager);
		}
	}

	@Override
	public WebDriver getDriver(Browser type) {
		this.validateInitBrowserManager(type);
		WebDriver driver = this.browserMap.get(type).getDriver();
		return driver;
	}

	@Override
	public BrowserDriver getDriverManager(Browser type) {
		this.validateInitBrowserManager(type);
		BrowserDriver driverManager = this.browserMap.get(type);
		return driverManager;
	}

	@Override
	public void removeDriverManager(Browser type) {
		if (this.browserMap.containsKey(type))
			this.browserMap.remove(type);

	}

	private void validateInitBrowserManager(Browser type) {
		if (this.browserMap.get(type) == null)
			throw new RuntimeException(
					"Please call createDriverManager method before can using method getDriverManager or method getDriver");
	}
}
