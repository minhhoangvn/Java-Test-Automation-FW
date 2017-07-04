package com.mh.ta.core.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mh.ta.core.driver.WebDrivers;
import com.mh.ta.core.webbrowser.Chrome;
import com.mh.ta.core.webbrowser.Firefox;

public class MainConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new TestRunningConfigModule());
		bind(MainConfig.class);
		//bind(FrameworkSettings.DriverConfig.class).annotatedWith(Names.named("driver-config"));
		//bind(FrameworkSettings.SUTConfig.class).annotatedWith(Names.named("sut-config"));
		bind(WebDrivers.class).annotatedWith(Names.named("Chrome")).to(Chrome.class);
		bind(WebDrivers.class).annotatedWith(Names.named("Firefox")).to(Firefox.class);
	}

}
