package com.mh.ta.core.web;

import static org.openqa.selenium.remote.CapabilityType.HAS_NATIVE_EVENTS;
import static org.openqa.selenium.remote.CapabilityType.PAGE_LOAD_STRATEGY;
import static org.openqa.selenium.remote.DesiredCapabilities.firefox;

import java.util.function.Supplier;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.core.driver.BrowserDriver;

public class Firefox extends BrowserDriver {

	private DesiredCapabilities capabilities = firefox();
	private FirefoxProfile profile = generateFirefoxProfile.get();
	private static Supplier<FirefoxProfile> generateFirefoxProfile = () -> {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
		firefoxProfile.setAlwaysLoadNoFocusLib(true);
		return firefoxProfile;
	};

	@Override
	protected void createDriver() {
		this.capabilities.setCapability(FirefoxDriver.PROFILE, this.profile);
		this.capabilities.setCapability(PAGE_LOAD_STRATEGY, "eager");
		this.capabilities.setCapability(HAS_NATIVE_EVENTS, true);
		drivers.set(this.configWebDriver.apply(new FirefoxDriver(this.capabilities)));
	}

	@Override
	public void setDriverOptions(Object options) {
		this.profile = (FirefoxProfile) options;

	}

	@Override
	public void setDriverServices(Object services) {
	}

	@Override
	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;

	}

}
