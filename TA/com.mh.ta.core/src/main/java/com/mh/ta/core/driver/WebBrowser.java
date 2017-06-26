package com.mh.ta.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class WebBrowser implements Driver<WebDriver> {
	protected DesiredCapabilities capabilities;
	protected Object services;
	protected Object options;

	protected void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}

	protected void setDriverSerices(Object services) {
		this.services = services;
	}

	protected void setDriverOptions(Object options) {
		this.options = options;
	}
}
