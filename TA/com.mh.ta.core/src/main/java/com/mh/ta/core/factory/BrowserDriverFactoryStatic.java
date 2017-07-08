package com.mh.ta.core.factory;

import org.openqa.selenium.WebDriver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.config.MainModule;
import com.mh.ta.core.driver.BrowserDriver;

public class BrowserDriverFactoryStatic {
	private static BrowserDriverFactoryStatic instance;
	private Injector inject;
	private ThreadLocal<BrowserDriver> browserManager = new ThreadLocal<BrowserDriver>();

	private BrowserDriverFactoryStatic() {
	};

	public static BrowserDriverFactoryStatic instance() {
		if (instance == null) {
			synchronized (BrowserDriverFactoryStatic.class) {
				if (instance == null) {
					instance = new BrowserDriverFactoryStatic();
					instance.inject = Guice.createInjector(new MainModule());
				}
			}
		}
		return instance;
	}

	public BrowserDriver getDriverManager(String type) {
		return initDriverManager(type);
	}

	public WebDriver getDriver() {
		return instance.browserManager.get().getDriver();
	}

	public void removeDriverManager(String type) {
		instance.browserManager.remove();
	}

	private BrowserDriver initDriverManager(String type) {
		if (browserManager.get() == null) {
			Class<?> browserClass = Browser.getBrowserClass(type);
			browserManager.set((BrowserDriver) instance.inject.getInstance(browserClass));
		}
		return browserManager.get();
	}
}
