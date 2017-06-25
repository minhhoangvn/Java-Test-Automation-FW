package com.mh.ta.core.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeBrowser<Type> extends Browser<ChromeDriver, DesiredCapabilities, ChromeOptions> {

	public ChromeBrowser() {
		super();
	}

	public ChromeBrowser(DesiredCapabilities capabilities, ChromeOptions options) {
		super(capabilities, options);
	}

	@Override
	public ChromeOptions getDriverOption() {
		return null;
	}

	@Override
	public void setDriverOption(ChromeOptions options) {

	}

	@Override
	public ChromeDriver startDriver(Object capabilities, Object options) {
		DesiredCapabilities cap = (DesiredCapabilities) capabilities;
		cap.setCapability(ChromeOptions.CAPABILITY, (ChromeOptions) options);
		return new ChromeDriver((DesiredCapabilities)capabilities);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		return null;
	}

	@Override
	public void setDesiredCapabilities(DesiredCapabilities capabilities) {

	}

}
