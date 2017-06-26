package com.mh.ta.core.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;

import com.mh.ta.core.webdriver.ChromeBrowser;

public class BrowserManagerConfig {
	@Bean
	public WebDriver webDriver() {
		return new ChromeBrowser().startDriver(DesiredCapabilities.chrome(), new ChromeOptions());
	}
}
