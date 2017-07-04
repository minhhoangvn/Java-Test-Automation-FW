package com.mh.ta.core.webbrowser;

import static org.openqa.selenium.remote.DesiredCapabilities.chrome;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.core.driver.WebDrivers;
public class Chrome extends WebDrivers {

	private ChromeOptions options = new ChromeOptions();
	private DesiredCapabilities capabilities = chrome();

	@Override
	protected void createDriver() {
		this.capabilities.setCapability(ChromeOptions.CAPABILITY, this.options);
		drivers.set(this.configWebDriver.apply(new ChromeDriver(this.capabilities)));
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = (ChromeOptions) options;
	}

	@Override
	public void setDriverServices(Object services) {
		
	}

	@Override
	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}

}
