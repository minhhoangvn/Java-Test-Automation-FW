package com.mh.ta.core.base;

import org.testng.IObjectFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.TestClassFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.module.MainModule;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.report.ReportListener;
import com.mh.ta.core.report.logger.LoggerListener;

/**
 * @author minhhoang
 *
 */
@Listeners({ ReportListener.class, LoggerListener.class })
public class BaseWebTestNG {

	protected boolean configOneTime = false;
	protected boolean enableAPITest = false;

	@BeforeClass
	@Parameters({ "configFile", "browser", "gridServerOptions" })
	protected void setUpTestClasses(@Optional("application.yaml") String configFile,
			@Optional("chrome") String browserType, @Optional("null") String gridServerOptions) {
		configTest(configFile);
		if (configOneTime) {			
			initDriver(browserType, gridServerOptions);
			GuiceInjectFactory.instance().injectToClass(this);
		}
	}

	@Parameters({ "configFile", "browser", "gridServerOptions" })
	@BeforeMethod
	protected void setUpTest(@Optional("application.yaml") String configFile, @Optional("chrome") String browserType,
			@Optional("null") String gridServerOptions) {
		if (!configOneTime) {		
			initDriver(browserType, gridServerOptions);
			GuiceInjectFactory.instance().injectToClass(this);
		}
	}

	@AfterMethod(alwaysRun = true)
	protected void cleanUpTest() {
		if (!configOneTime)
			diposeDriver();
	}

	@AfterClass(alwaysRun = true)
	public void cleanUpTestClasses() {
		if (configOneTime)
			diposeDriver();
	}

	@AfterSuite(alwaysRun = true)
	public void cleanUpSuites() {
		DriverFactory.instance().diposeAllDriver();
	}

	private void configTest(String configFile) {
		MainConfig config = GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class,
				ApplicationConfig.class);
		if (config == null) {
			GuiceInjectFactory.instance().createInject(new MainModule(configFile));
		}
	}

	private void initDriver(String browserType, String gridServerOptions) {
		if (!enableAPITest)
			DriverFactory.instance().createDriver(browserType, gridServerOptions);
	}

	private void diposeDriver() {
		if (!enableAPITest)
			DriverFactory.instance().diposeDriver();
	}

	@ObjectFactory
	public IObjectFactory initTestClassFactory() {		
		return  GuiceInjectFactory.instance().createObjectInstance(TestClassFactory.class);
	}
}
