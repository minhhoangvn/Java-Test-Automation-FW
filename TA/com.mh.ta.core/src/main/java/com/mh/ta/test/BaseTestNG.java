package com.mh.ta.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mh.ta.core.config.MainModule;
import com.mh.ta.core.factory.WebDriverFactory;

public abstract class BaseTestNG {
	protected Injector inject;
	protected WebDriverFactory driverFactory;

	public BaseTestNG() {
		this.initConfig();
	}

	private void initConfig() {
		this.inject = Guice.createInjector(new MainModule());
		this.driverFactory = inject.getInstance(WebDriverFactory.class);
	}
}
