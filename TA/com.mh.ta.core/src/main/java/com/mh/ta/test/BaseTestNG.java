package com.mh.ta.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mh.ta.core.config.FrameworkSettings;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.guice.MainModule;

public abstract class BaseTestNG {
	protected FrameworkSettings.DriverConfig driverConfig;
	protected FrameworkSettings.SUTConfig sutConfig;

	public BaseTestNG() {
		this.initConfig();
	}

	private void initConfig() {
		Injector injector = Guice.createInjector(new MainModule());
		MainConfig config = injector.getInstance(MainConfig.class);
		this.sutConfig = config.getSUTConfig();
		this.driverConfig = config.getDriverConfig();
	}
}
