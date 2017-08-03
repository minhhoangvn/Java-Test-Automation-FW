package com.mh.ta.core.factory;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.base.browsers.Chrome;
import com.mh.ta.core.config.FrameworkSettings;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.interfaces.IDriver;

public class SeleniumDriverFactory {
	private static SeleniumDriverFactory instance;
	@Inject
	FrameworkSettings setting;
	private static ThreadLocal<IDriver<WebDriver>> drivers = new ThreadLocal<>();

	private SeleniumDriverFactory() {
		GuiceInjectFactory.instance().injectToClass(this);
	}

	public static SeleniumDriverFactory instance() {
		if (instance == null)
			synchronized (SeleniumDriverFactory.class) {
				if (instance == null)
					instance = new SeleniumDriverFactory();
			}
		return instance;
	}

	public void createDriver() {
		System.err.println(Thread.currentThread().getId());
		drivers.set(GuiceInjectFactory.instance().getObjectInstance(Chrome.class));
	}

	public WebDriver getWebDriver() {
		if (drivers.get() == null)
			throw new TestContextException("Call createDriver method before can get driver");
		return drivers.get().getDriver();
	}

	public void diposeDriver() {
		drivers.get().diposeDriver();
	}
}
