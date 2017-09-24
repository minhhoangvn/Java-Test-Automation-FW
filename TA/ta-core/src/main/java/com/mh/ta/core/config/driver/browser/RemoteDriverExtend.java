
package com.mh.ta.core.config.driver.browser;

import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * Create remote extend for implement 
 * capture screen shot for remote driver
 * @author minhhoang
 *
 */

class RemoteDriverExtend extends RemoteWebDriver implements TakesScreenshot {
	public RemoteDriverExtend(URL url, DesiredCapabilities dc) {
		super(url, dc);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target){
		if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
			return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
		}
		return null;
	}
}
