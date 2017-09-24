
package com.mh.ta.core.config.driver.browser;

import static org.openqa.selenium.remote.CapabilityType.HAS_NATIVE_EVENTS;
import static org.openqa.selenium.remote.CapabilityType.PAGE_LOAD_STRATEGY;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
class Remote implements SeleniumDriver {
	private DesiredCapabilities capabilities;
	private String gridServer;

	@Override
	public RemoteDriverExtend createDriver() {
		try {
			URL gridUrl = new URL(this.gridServer);
			return new RemoteDriverExtend(gridUrl, this.capabilities);			
		} catch (MalformedURLException e) {
			throw new TestContextException("Checking parameter config for remote grid server " + e);
		}
	}

	@Override
	public void setDriverOptions(Object options) {
		String gridOptions = (String) options;
		if (this.hasBrowserConfigParameter(gridOptions)) {
			this.initGridServerWithBrowserConfig(gridOptions);
		} else {
			this.initGridServerWithDefaultBrowser(gridOptions);
		}

	}

	@Override
	public void setCapabilities(Object capabilities) {
		this.capabilities = this.capabilities.merge((DesiredCapabilities) capabilities);
	}

	private boolean hasBrowserConfigParameter(String gridOptions) {
		return gridOptions.split("#").length == 2;
	}

	private void initGridServerWithBrowserConfig(String gridOptions) {
		String gridServerUrl = gridOptions.split("#")[0];
		String gridBrowser = gridOptions.split("#")[1];
		this.gridServer = gridServerUrl;
		this.initDefaultRemoteCapabilities(gridBrowser);
	}

	private void initGridServerWithDefaultBrowser(String gridOptions) {
		this.gridServer = gridOptions.split("#")[0];
		this.initDefaultRemoteCapabilities("firefox");
	}

	private void initDefaultRemoteCapabilities(String type) {
		switch (Browsers.getTypeByString(type)) {
		case CHROME:
			this.capabilities = DesiredCapabilities.chrome();
			this.capabilities.setAcceptInsecureCerts(true);
			this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			break;
		case FIREFOX:
			this.capabilities = DesiredCapabilities.firefox();
			this.capabilities.setCapability(FirefoxDriver.PROFILE, this.createDefaultProfile());
			this.capabilities.setCapability(PAGE_LOAD_STRATEGY, "eager");
			this.capabilities.setCapability(HAS_NATIVE_EVENTS, true);
			this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			break;
		case IE:
			this.capabilities = DesiredCapabilities.internetExplorer();
			this.capabilities.setAcceptInsecureCerts(true);
			this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			break;
		default:
			this.capabilities = DesiredCapabilities.firefox();
			this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			break;
		}
	}

	private FirefoxProfile createDefaultProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAlwaysLoadNoFocusLib(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		return profile;
	}
}
