package com.mh.ta.base.selenium.webdriver;

import static org.openqa.selenium.remote.DesiredCapabilities.internetExplorer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

class IE implements BrowserDriver<WebDriver> {
	private DesiredCapabilities capabilities;
	private InternetExplorerOptions options;

	private DesiredCapabilities getCapabilities() {
		if (capabilities == null) {
			capabilities = internetExplorer();
			capabilities.setAcceptInsecureCerts(true);
			capabilities.setJavascriptEnabled(true);
		}
		return capabilities;
	}

	private InternetExplorerOptions getOptions() {
		if (options == null) {
			options = new InternetExplorerOptions();
			options.enableNativeEvents();
			options.takeFullPageScreenshot();
			options.ignoreZoomSettings();
			options.destructivelyEnsureCleanSession();
			options.enablePersistentHovering();
			options.elementScrollTo(ElementScrollBehavior.TOP);
			options.requireWindowFocus();
			options.introduceFlakinessByIgnoringSecurityDomains();
		}
		return options;
	}

	@Override
	public WebDriver createDriver() {
		WebDriver driver = new InternetExplorerDriver(this.getOptions().merge(this.getCapabilities()));
		return driver;
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = (InternetExplorerOptions) options;
	}

	@Override
	public void setCapabilities(Object capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}
}
