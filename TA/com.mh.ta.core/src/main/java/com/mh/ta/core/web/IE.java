package com.mh.ta.core.web;

import java.util.function.Supplier;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.core.driver.BrowserDriver;

public class IE extends BrowserDriver {
	private DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	private InternetExplorerOptions options = generateChromeOptions.get();
	private static Supplier<InternetExplorerOptions> generateChromeOptions = () -> {
		InternetExplorerOptions optiions = new InternetExplorerOptions();
		optiions.enableNativeEvents();
		optiions.takeFullPageScreenshot();
		optiions.ignoreZoomSettings();
		optiions.destructivelyEnsureCleanSession();
		optiions.enablePersistentHovering();
		optiions.elementScrollTo(ElementScrollBehavior.TOP);
		optiions.requireWindowFocus();
		optiions.introduceFlakinessByIgnoringSecurityDomains();
		return optiions;
	};

	@Override
	protected void createDriver() {
		drivers.set(this.configWebDriver.apply(new InternetExplorerDriver(this.options.merge(this.capabilities))));
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = (InternetExplorerOptions) options;
	}

	@Override
	public void setDriverServices(Object services) {

	}

	@Override
	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}
}
