package com.mh.ta.core.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mh.ta.core.webdriver.ChromeBrowser;

@Configuration
@EnableConfigurationProperties(FrameworkSettings.class)
public class TestRunningConfig {

	@Bean
	public WebDriver webDriver(@Autowired FrameworkSettings setting) {
		System.err.println(setting);
		System.err.println(setting.getDriverConfig());
		System.err.println(setting.getSutConfig());
		return new ChromeBrowser().startDriver();
	}
}
