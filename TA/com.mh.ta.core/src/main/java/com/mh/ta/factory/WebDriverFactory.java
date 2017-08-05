package com.mh.ta.factory;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.selenium.driver.SeleniumDriverManager;

public class WebDriverFactory {
	static ThreadLocal<SeleniumDriverManager> driverManager = new ThreadLocal<SeleniumDriverManager>();

	public static WebDriver getDriver() {

		if (driverManager.get() == null) {
			GuiceInjectFactory.instance().createInject(driverManagerModule());
			SeleniumDriverManager manager = GuiceInjectFactory.instance().getInject()
					.getInstance(SeleniumDriverManager.class);
			driverManager.set(manager);
		}
		return driverManager.get().getDriver();

	}

	public static void createDriverManager(String type) {
		if (driverManager.get() == null) {
			GuiceInjectFactory.instance().createInject(driverManagerModule());
			SeleniumDriverManager manager = GuiceInjectFactory.instance().getInject()
					.getInstance(SeleniumDriverManager.class);
			manager.createDriver(type);
			driverManager.set(manager);
		}
	}

	public static void createDriverManager(String type, MainConfig config) {
		if (driverManager.get() == null) {
			GuiceInjectFactory.instance().createInject(driverManagerModule(config));
			SeleniumDriverManager manager = GuiceInjectFactory.instance().getInject()
					.getInstance(SeleniumDriverManager.class);
			manager.createDriver(type);
			driverManager.set(manager);
		}
	}

	public static void diposeDriver() {
		if (driverManager.get() != null)
			driverManager.get().diposeDriver();
	}

	private static Module driverManagerModule(MainConfig config) {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(SeleniumDriverManager.class).toInstance(new SeleniumDriverManager(config));
			}
		};
	}

	private static Module driverManagerModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(SeleniumDriverManager.class).toInstance(new SeleniumDriverManager());
			}
		};
	}
}
