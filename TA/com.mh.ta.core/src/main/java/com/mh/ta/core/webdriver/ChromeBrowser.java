package com.mh.ta.core.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mh.ta.core.config.DriverConfig;
import com.mh.ta.core.driver.WebBrowser;

public class ChromeBrowser extends WebBrowser {

	public ChromeBrowser() {
		super();
	}

	public ChromeBrowser(DriverConfig config) {
		super();
		super.setConfig(config);
	}

	@Override
	public WebDriver startDriver() {

		return new ChromeDriver();
	}

}
