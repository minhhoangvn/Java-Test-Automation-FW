package com.mh.ta.core.helper;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.mh.ta.keywords.WebKeywords;

public class InjectorInitializer {
	public static Injector injectWebDriver(Injector injector, WebDriver driver) {
		return injector.createChildInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(WebDriver.class).toInstance(driver);
				bind(WebKeywords.class);
			}
		});
	}

}
