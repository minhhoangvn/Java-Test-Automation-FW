package com.mh.ta.selenium.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.mh.ta.base.browsers.Chrome;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.browsers.BrowserType;
import com.mh.ta.core.config.DriverConfig;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.interfaces.driver.IDriver;

@Singleton
public class SeleniumDriverManager {
	private IDriver<WebDriver> drivers;
	private MainConfig config;
	private Object options;
	private DesiredCapabilities capabilities;

	public SeleniumDriverManager() {
		initMainConfig();
	}

	public SeleniumDriverManager(MainConfig config) {
		this.config = config;
	}

	public void createDriver(String type) {
		Class<IDriver<WebDriver>> cls = BrowserType.getBrowserClass(type);
		IDriver<WebDriver> driverManager = GuiceInjectFactory.instance().getObjectInstance(cls);
		driverManager.setCapabilities(this.capabilities);
		driverManager.setDriverOptions(this.options);
		drivers = driverManager;
		setUpDriverConfig();
	}

	public WebDriver getDriver() {
		if (drivers == null) {
			drivers = (GuiceInjectFactory.instance().getObjectInstance(Chrome.class));
			setUpDriverConfig();
		}
		return drivers.getDriver();
	}

	public void diposeDriver() {
		if (drivers != null)
			drivers.diposeDriver();
		drivers = null;
	}

	public void setDriverOption(Object options) {
		this.options = options;
	}

	public void setDesiredCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}

	private void setUpDriverConfig() {
		WebDriver driver = drivers.getDriver();
		DriverConfig driverConfig = config.getDriverConfig();
		setUpWindows(driverConfig, driver);
		setUpDriverTimeOut(driverConfig, driver);
	}

	private void setUpWindows(DriverConfig driverConfig, WebDriver driver) {
		if (driverConfig.isMaximize()) {
			driver.manage().window().maximize();
		}
		if (driverConfig.isHidden()) {
			driver.manage().window().setPosition(new Point(0, -2000));
		}
	}

	private void setUpDriverTimeOut(DriverConfig driverConfig, WebDriver driver) {
		Options options = driver.manage();
		options.timeouts().pageLoadTimeout(driverConfig.getPageloadTimeout(), TimeUnit.SECONDS);
		options.timeouts().implicitlyWait(driverConfig.getImplicitWait(), TimeUnit.SECONDS);
	}

	private void initMainConfig() {
		try {
			Injector injector = GuiceInjectFactory.instance().getInject();
			config = injector.getInstance(Key.get(MainConfig.class, ApplicationConfig.class));
		} catch (ConfigurationException e) {
			config = new MainConfig();
		}
	}
}
