package com.mh.ta.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mh.ta.core.config.MainModule;
import com.mh.ta.core.factory.BrowserDriverManagerFactory;

public abstract class BaseTestNG {
	protected final Injector inject;
	protected final BrowserDriverManagerFactory driverFactory;

	public BaseTestNG() {
		this.inject = Guice.createInjector(new MainModule());
		this.driverFactory = inject.getInstance(BrowserDriverManagerFactory.class);
	}
}
