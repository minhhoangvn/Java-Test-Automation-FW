package com.mh.ta.factory;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.interfaces.driver.IDriver;

public class DriverFactory {
	static ThreadLocal<IDriver<?>> driverManagerStorage = new ThreadLocal<IDriver<?>>();
	private static MainConfig config;

	public static void createDriver(String driverType) {
		if (driverManagerStorage.get() == null) {
			SeleniumDriver driverManager = GuiceInjectFactory.instance().getObjectInstance(SeleniumDriver.class);
			driverManager.createDriver(driverType);
			configDriver();
			driverManagerStorage.set(driverManager);
		}
	}

	public static IDriver<?> getDriver() {
		if (driverManagerStorage.get() == null) {
			throw new TestContextException("Call createSeleniumDriver before can get driver");
		}
		return driverManagerStorage.get();
	}

	public static void diposeDriver() {
		if (driverManagerStorage.get() != null)
			driverManagerStorage.get().diposeDriver();
		driverManagerStorage.remove();
	}

	private static void configDriver() {
		try {
			Injector injector = GuiceInjectFactory.instance().getInject();
			config = injector.getInstance(Key.get(MainConfig.class, ApplicationConfig.class));
		} catch (Exception e) {
			throw new TestContextException(e);
		}
	}
}
