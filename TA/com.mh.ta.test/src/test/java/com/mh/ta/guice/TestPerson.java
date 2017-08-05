package com.mh.ta.guice;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.mh.ta.selenium.driver.SeleniumDriverManager;

public class TestPerson {
	@Inject
	SeleniumDriverManager factory;

	@Parameters({ "configFileName" })
	@BeforeMethod
	public void init(@Optional("application") String config) {

	}

	@Test(invocationCount = 10, threadPoolSize = 10)
	public void test() throws InterruptedException {

	}
}
