package com.mh.ta.guice;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.config.MainConfigModule;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.WebDriverFactory;
import com.mh.ta.keywords.KeywordModule;
import com.mh.ta.keywords.WebKeywords;
import com.mh.ta.test.pages.LoginPage;

@HighLightElement
public class TestConfig {
	@Inject
	@ApplicationConfig
	MainConfig config;
	@Inject
	LoginPage login;

	@Inject
	WebKeywords kw;

	@BeforeMethod
	public void init() {
		GuiceInjectFactory.instance().createInject(new KeywordModule());
		GuiceInjectFactory.instance().createInject(new MainConfigModule());
		GuiceInjectFactory.instance().injectToClass(this);
	}

	@AfterMethod
	public void close() {
		WebDriverFactory.diposeDriver();
	}

	@Test(threadPoolSize = 1, invocationCount = 1)
	public void testConfig() throws InterruptedException {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
	}
}
