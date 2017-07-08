package com.mh.ta.core.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriver;
import com.mh.ta.core.web.Chrome;
import com.mh.ta.core.web.Firefox;
import com.mh.ta.core.web.IE;

public class MainModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new SettingModule());
		MapBinder<Browser, BrowserDriver> mapBinder = MapBinder.newMapBinder(binder(), Browser.class,
				BrowserDriver.class);
		mapBinder.addBinding(Browser.CHROME).to(Chrome.class);
		mapBinder.addBinding(Browser.FIREFOX).to(Firefox.class);
		mapBinder.addBinding(Browser.IE).to(IE.class);
	}

}
