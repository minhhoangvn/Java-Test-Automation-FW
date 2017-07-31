package com.mh.ta.core.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriverManager;
import com.mh.ta.core.web.Chrome;
import com.mh.ta.core.web.Firefox;
import com.mh.ta.core.web.IE;

public class MainModule extends AbstractModule {
	private final String settingFileName;
	public MainModule(String settingPath) {
		this.settingFileName = settingPath == null ? "application" : settingPath;
	}

	@Override
	protected void configure() {
		install(new SettingModule(this.settingFileName));
		MapBinder<Browser, BrowserDriverManager> mapBinder = MapBinder.newMapBinder(binder(), Browser.class,
				BrowserDriverManager.class);
		mapBinder.addBinding(Browser.CHROME).to(Chrome.class);
		mapBinder.addBinding(Browser.FIREFOX).to(Firefox.class);
		mapBinder.addBinding(Browser.IE).to(IE.class);
	}

}
