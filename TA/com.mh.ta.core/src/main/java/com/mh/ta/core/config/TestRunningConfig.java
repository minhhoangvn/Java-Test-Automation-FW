package com.mh.ta.core.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.mh.ta.core.webdriver.ChromeBrowser;

@Configuration
@EnableConfigurationProperties(FrameworkSettings.class)
public class TestRunningConfig {

	@Autowired
	public FrameworkSettings setting;

	@Bean
	public WebDriver webDriver() {
		System.err.println(this.setting);
		System.err.println(this.setting.getDriverConfig());
		System.err.println(this.setting.getSutConfig());
		return new ChromeBrowser().startDriver();
	}
}
