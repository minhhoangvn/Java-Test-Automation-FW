
package com.mh.ta.core.config.driver.browser;

import org.openqa.selenium.WebDriver;
/**
 * Interface for selenium webdriver creator
 * 
 * @author minhhoang
 *
 */

public interface SeleniumDriver {
	WebDriver createDriver();

	public void setDriverOptions(Object options);

	public void setCapabilities(Object capabilities);
}
