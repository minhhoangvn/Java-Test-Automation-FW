
package com.mh.ta.core.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.module.MainModule;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.report.extent.ReportExtend;
import com.mh.ta.core.report.logger.LoggerListener;

import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;


/**
 * @author minhhoang
 *
 */
@Listeners({ LoggerListener.class })
public class BaseCucumberTestNG {
	protected static ThreadLocal<String> browserNameStorage = new ThreadLocal<>();
	protected static ThreadLocal<String> gridOptionsStorage = new ThreadLocal<>();
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass
	@Parameters({ "configFile" })
	protected void setUpTestClasses(@Optional("application.yaml") String configFile) {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		configTest(configFile);
	}

	@Parameters({ "configFile", "browser", "gridServerOptions" })
	@BeforeMethod
	protected void setUpTest(@Optional("application.yaml") String configFile, @Optional("chrome") String browserType,
			@Optional("null") String gridServerOptions) {
		browserNameStorage.set(browserType);
		gridOptionsStorage.set(gridServerOptions);
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features", threadPoolSize = 4)
	public void feature(CucumberFeatureWrapper cucumberFeature) {		
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@AfterMethod(alwaysRun = true)
	protected void cleanUpTest() {
		DriverFactory.instance().diposeDriver();
	}

	@AfterSuite(alwaysRun = true)
	public void cleanUpSuites() {
		DriverFactory.instance().diposeAllDriver();
		testNGCucumberRunner.finish();
		ReportExtend.createReport();
	}

	private void configTest(String configFile) {
		MainConfig config = GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class,
				ApplicationConfig.class);
		if (config == null) {
			GuiceInjectFactory.instance().createInject(new MainModule(configFile));
		}
	}

	@DataProvider(parallel = true)
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}
}
