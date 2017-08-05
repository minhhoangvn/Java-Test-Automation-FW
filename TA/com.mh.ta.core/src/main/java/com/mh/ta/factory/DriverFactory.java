package com.mh.ta.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.driver.DriverManager;

public class DriverFactory {
	static ThreadLocal<DriverManager<?>> driverManager = new ThreadLocal<DriverManager<?>>();

	@SuppressWarnings("unchecked")
	public static <T> T getDriver() {

		if (driverManager.get() == null) {
			GuiceInjectFactory.instance().createInject(driverManagerModule());
			driverManager.set(GuiceInjectFactory.instance().getInject().getInstance(DriverManager.class));
		}
		return (T) driverManager.get().getDriver();

	}

	public static void createDriverManager(String type) {
		System.err.println("Create Driver Manager " + type);
		if (driverManager.get() == null) {
			GuiceInjectFactory.instance().createInject(driverManagerModule());
			DriverManager<?> manager = GuiceInjectFactory.instance().getInject().getInstance(DriverManager.class);
			manager.createDriver(type);
			driverManager.set(manager);
		}
	}

	public static void createDriverManager(String type, MainConfig config) {
		if (driverManager.get() == null) {
			GuiceInjectFactory.instance().createInject(driverManagerModule(config));
			DriverManager<?> manager = GuiceInjectFactory.instance().getInject().getInstance(DriverManager.class);
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
				bind(DriverManager.class).toInstance(new DriverManager<Object>(config));
			}
		};
	}

	private static Module driverManagerModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(DriverManager.class).toInstance(new DriverManager<Object>());
			}
		};
	}
}
