package com.mh.ta.base.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mh.ta.core.config.MainConfigModule;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.SeleniumDriverFactory;
import com.mh.ta.keywords.KeywordModule;

public class BaseWebTestNG {
	@Parameters("configFile")
	@BeforeSuite
	protected void setUpSuites(@Optional("application.yaml") String configFile) {
		GuiceInjectFactory.instance().createInject(new MainConfigModule(configFile));
		GuiceInjectFactory.instance().createInject(new KeywordModule());
	}

	@BeforeClass
	public void injectDependency() {
		GuiceInjectFactory.instance().injectToClass(this);
	}

	@Parameters({ "browser", "gridServerOptions" })
	@BeforeMethod
	protected void setUpTest(@Optional("chrome") String browserType, @Optional("null") String gridServerOptions) {
		SeleniumDriverFactory.createSeleniumWebDriver(browserType);
	}

	@AfterMethod
	protected void cleanUpTest() {
		SeleniumDriverFactory.diposeSeleniumDriver();
	}

	@AfterSuite
	protected void cleanUpSuite() {

	}
}
