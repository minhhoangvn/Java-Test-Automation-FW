package com.mh.ta.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mh.ta.core.config.FrameworkSettings;
import com.mh.ta.core.config.TestRunningConfigModule;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestRunningConfigModule.class)

public class TestDriverSpring {

	@Autowired
	private FrameworkSettings setting;

	@Test
	public void testB() {
		System.err.println(setting.getDriverConfig());
		System.err.println(setting.getSutConfig());

	}

}
