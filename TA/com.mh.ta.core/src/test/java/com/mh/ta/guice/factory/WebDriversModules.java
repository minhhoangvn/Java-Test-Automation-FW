package com.mh.ta.guice.factory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class WebDriversModules extends AbstractModule {

	@Override
	protected void configure() {
		bind(WebDrivers.class).toInstance(new Chrome());
		bind(Integer.class).annotatedWith(Names.named("Port1")).toInstance(123);
		bind(Integer.class).annotatedWith(Names.named("Port2")).toInstance(123345);
		bind(WebDrivers.class).annotatedWith(Names.named("Firefox")).to(Firefox.class);
		bind(WebDrivers.class).annotatedWith(Names.named("Chrome")).to(Chrome.class);

	}
}
