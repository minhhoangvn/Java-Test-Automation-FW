package com.mh.ta.core.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.core.driver.WebBrowser;

public class ChromeBrowser extends WebBrowser {

	public ChromeBrowser() {
		super();
	}

	public ChromeBrowser(DesiredCapabilities capabilities, ChromeOptions options) {
		super.setCapabilities(capabilities);
		super.setDriverOptions(options);
	}

	@Override
	public WebDriver startDriver(Object capabilities, Object options) {
		
		return new ChromeDriver();
	}
}
