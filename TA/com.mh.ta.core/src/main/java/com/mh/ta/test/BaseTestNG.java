package com.mh.ta.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mh.ta.core.config.MainModule;
import com.mh.ta.core.driver.BrowserDriverManager;
import com.mh.ta.core.factory.BrowserDriverManagerFactory;

public abstract class BaseTestNG {
	protected final Injector inject;
	protected final BrowserDriverManagerFactory driverFactory;
	protected BrowserDriverManager driverManager;
	
	
	public BaseTestNG(String configPath) {
		this.inject = Guice.createInjector(new MainModule(configPath));
		this.driverFactory = inject.getInstance(BrowserDriverManagerFactory.class);
	}
}
