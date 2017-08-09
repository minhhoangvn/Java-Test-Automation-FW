package com.mh.ta.base.selenium.webdriver;

import static org.openqa.selenium.remote.DesiredCapabilities.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

class Chrome implements BrowserDriver<WebDriver> {

	private ChromeOptions options;
	private DesiredCapabilities capabilities;

	private ChromeOptions getOptions() {
		return options;
	}

	private DesiredCapabilities getCapabilities() {
		if (capabilities == null) {
			this.capabilities = chrome();
			this.capabilities.setAcceptInsecureCerts(true);
			this.capabilities.setJavascriptEnabled(true);
		}
		return capabilities;
	}

	@Override
	public WebDriver createDriver() {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, getOptions());
		WebDriver driver = new ChromeDriver(getCapabilities().merge(cap));
		return driver;
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = (ChromeOptions) options;
	}

	@Override
	public void setCapabilities(Object capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}
}
