package com.mh.ta.guice.factory;

import org.openqa.selenium.WebDriver;

public abstract class WebDrivers implements Drivers<WebDriver> {
	protected ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();
	protected WebDriver driver;

	protected abstract void createDriver();

	protected abstract void stopDriver();

	@Override
	public WebDriver getDriver() {
		if (driver == null)
			createDriver();
		return drivers.get();
	}

	@Override
	public void diposeDriver() {
		if (drivers.get() != null) {
			drivers.get().quit();;
			drivers.remove();
		}
	}

}
