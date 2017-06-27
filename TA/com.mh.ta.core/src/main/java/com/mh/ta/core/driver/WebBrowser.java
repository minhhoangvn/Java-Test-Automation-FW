package com.mh.ta.core.driver;

import org.openqa.selenium.WebDriver;

import com.mh.ta.core.config.DriverConfig;

public abstract class WebBrowser implements Driver<WebDriver> {
	protected DriverConfig config = null;

	protected DriverConfig getConfig() {
		return config;
	}

	protected void setConfig(DriverConfig config) {
		this.config = config;
	}

}
