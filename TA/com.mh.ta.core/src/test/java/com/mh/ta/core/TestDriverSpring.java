package com.mh.ta.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mh.ta.core.config.TestRunningConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDriverSpring.class)
@ContextConfiguration(classes = TestRunningConfig.class)

public class TestDriverSpring {

	@Autowired
	private WebDriver webDriver;

	@Test
	public void testInstaceDriverSpring() {
		webDriver.get("http://www.google.com");

	}
}
