package com.mh.ta.core;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mh.ta.core.config.FrameworkSettings;
import com.mh.ta.core.config.TestRunningConfig;

@ContextConfiguration(classes = TestRunningConfig.class)
@SpringBootTest(classes = { TestSettingsConfig.class, FrameworkSettings.class })
public class TestSettingsConfig extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebDriver webDriver;

	private WebDriver driver = null;

	@BeforeMethod
	public void setUp() {
		this.driver = webDriver;
	}

	@Test
	public void testA() {
		this.driver.get("http://www.google.com");

	}

	@AfterMethod
	public void clean() {
		this.driver.close();
		
	}
}
