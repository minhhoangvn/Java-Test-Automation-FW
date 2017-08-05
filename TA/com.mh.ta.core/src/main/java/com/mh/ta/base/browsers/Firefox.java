package com.mh.ta.base.browsers;

import static org.openqa.selenium.remote.DesiredCapabilities.firefox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.interfaces.driver.IDriver;

public class Firefox implements IDriver<WebDriver> {
	private FirefoxProfile profile;
	private DesiredCapabilities capabilities = firefox();
	private WebDriver driver;

	@Override
	public void createDriver() {
		if (driver == null) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxDriver.PROFILE, getProfile());
			driver = new FirefoxDriver(getCapabilities().merge(cap));
		}
	}

	private FirefoxProfile getProfile() {
		if (profile == null) {
			profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setAlwaysLoadNoFocusLib(true);
			profile.setAssumeUntrustedCertificateIssuer(true);
		}
		return profile;
	}

	private DesiredCapabilities getCapabilities() {
		if (capabilities == null)
			capabilities = firefox();
		return capabilities;
	}

	@Override
	public void setDriverOptions(Object options) {
		this.profile = (FirefoxProfile) profile;
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
