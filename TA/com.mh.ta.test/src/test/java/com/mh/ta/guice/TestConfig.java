package com.mh.ta.guice;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.mh.ta.core.config.MainConfigModule;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.keywords.KeywordModule;
import com.mh.ta.test.pages.LoginPage;


public class TestConfig {
	
	@Inject
	LoginPage login;


	@BeforeSuite
	public void init() {
		GuiceInjectFactory.instance().createInject(new KeywordModule());
		GuiceInjectFactory.instance().createInject(new MainConfigModule());
		GuiceInjectFactory.instance().injectToClass(this);
	}

	@BeforeMethod
	@Parameters({ "browser" })
	public void createDriver(@Optional("chrome")String browser) {
		System.err.println(browser);
		
		DriverFactory.createDriverManager(browser);
	}

	@AfterMethod
	public void close() {
		DriverFactory.diposeDriver();
	}

	@Test(threadPoolSize = 1, invocationCount = 1)
	public void testConfig() throws InterruptedException {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
	}
}
