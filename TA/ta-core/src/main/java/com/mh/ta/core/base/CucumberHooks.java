
package com.mh.ta.core.base;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.driver.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Using cucumber hook for init WebDriver
 * this will instance driver and dipose driver on each scenario
 * @author minhhoang
 *
 */
public class CucumberHooks {	

	@Before
	public void setUpScenario() {
		DriverFactory.instance().createDriver(BaseCucumberTestNG.browserNameStorage.get(),
				BaseCucumberTestNG.gridOptionsStorage.get());
	}

	@After
	public void cleanUpScenario(Scenario scenario) {

		if (scenario.isFailed()) {
			createScreenShot(scenario);
		}
		DriverFactory.instance().diposeDriver();
	}

	private void createScreenShot(Scenario scenario) {
		Driver driver = DriverFactory.instance().getDriver();
		byte[] screenshot = driver.createScreenShot();
		scenario.embed(screenshot, "image/png");
	}
}
