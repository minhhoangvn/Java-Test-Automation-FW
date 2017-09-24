
package com.mh.ta.core.config.driver;

import org.openqa.selenium.WebDriver;

/**
 * @author minhhoang
 *
 */
public interface WebDriverFactory {
	Driver create(WebDriver driver);
}
