package com.mh.ta.base.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.openqa.selenium.remote.DesiredCapabilities.chrome;
import com.mh.ta.interfaces.IDriver;

public class Chrome implements IDriver<WebDriver> {

	private ChromeOptions options;
	private DesiredCapabilities capabilities;
	private WebDriver driver;

	private ChromeOptions getOptions() {
		return options;
	}

	private DesiredCapabilities getCapabilities() {
		if (capabilities == null) {
			this.capabilities = chrome();
			this.capabilities.setAcceptInsecureCerts(true);
			this.capabilities.setJavascriptEnabled(true);
		}
		return capabilities;
	}

	@Override
	public void createDriver() {
		if (driver == null) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, getOptions());
			driver = new ChromeDriver(getCapabilities().merge(cap));
		}
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = (ChromeOptions) options;
	}

	@Override
	public void setDriverServices(Object services) {
		
	}

	@Override
	public void setCapabilities(Object capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}

	@Override
	public void diposeDriver() {
		driver.quit();
		driver = null;
	}

	@Override
	public WebDriver getDriver() {
		if (driver == null)
			this.createDriver();
		return driver;
	}

}
