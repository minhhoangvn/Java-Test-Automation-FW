package com.mh.ta.base.selenium.webdriver;

import static org.openqa.selenium.remote.DesiredCapabilities.firefox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

class Firefox implements BrowserDriver<WebDriver> {
	private FirefoxProfile profile;
	private DesiredCapabilities capabilities = firefox();

	public WebDriver createDriver() {
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability(FirefoxDriver.PROFILE, getProfile());
		WebDriver driver = new FirefoxDriver(getCapabilities().merge(cap));
		return driver;
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

	public void setDriverOptions(Object options) {
		this.profile = (FirefoxProfile) profile;
	}

	public void setCapabilities(Object capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}
}
