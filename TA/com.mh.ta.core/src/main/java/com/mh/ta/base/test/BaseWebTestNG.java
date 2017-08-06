package com.mh.ta.base.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mh.ta.core.config.MainConfigModule;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.keywords.KeywordModule;

public class BaseWebTestNG {

	@Parameters({ "configFile", "browser", "gridServerOptions" })
	@BeforeMethod
	protected void setUpTest(@Optional("application.yaml") String configFile, @Optional("chrome") String browserType,
			@Optional("null") String gridServerOptions) {
		GuiceInjectFactory.instance().createInject(new MainConfigModule(configFile));
		GuiceInjectFactory.instance().createInject(new KeywordModule());
		GuiceInjectFactory.instance().injectToClass(this);
		DriverFactory.createDriver(browserType);
	}

	@AfterMethod
	protected void cleanUpTest() {
		DriverFactory.diposeDriver();
	}

}
