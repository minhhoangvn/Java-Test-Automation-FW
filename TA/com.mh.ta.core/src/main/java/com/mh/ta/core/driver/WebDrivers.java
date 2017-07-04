package com.mh.ta.core.driver;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mh.ta.core.config.FrameworkSettings;

public abstract class WebDrivers implements Driver<WebDriver> {
	@Inject
	@Named("driver-config")
	private FrameworkSettings.DriverConfig config;

	protected ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();

	protected abstract void createDriver();

	public abstract void setDriverOptions(Object options);

	public abstract void setDriverServices(Object services);

	public abstract void setCapabilities(DesiredCapabilities capabilities);

	@Override
	public WebDriver getDriver() {
		WebDriver driver = drivers.get();
		if (driver == null)
			createDriver();
		return drivers.get();
	}

	@Override
	public void diposeDriver() {
		WebDriver driver = drivers.get();
		if (driver != null) {
			driver.quit();
		}
		drivers.remove();

	}

	Consumer<WebDriver> maximizeBrowser = (driver) -> {
		if (config.getMaximize())
			driver.manage().window().maximize();
	};

	Consumer<WebDriver> setImplicitWait = (driver) -> {
		driver.manage().timeouts().implicitlyWait(config.getImplicitWait(), TimeUnit.SECONDS);
	};

	Consumer<WebDriver> setPageLoadWait = (driver) -> {
		driver.manage().timeouts().pageLoadTimeout(config.getPageloadTimeout(),
				TimeUnit.SECONDS);
	};
	
	protected Function<WebDriver, WebDriver> configWebDriver = driver -> {
		if (this.config != null) {
			System.err.println("Config is not null");
			maximizeBrowser.accept(driver);
			setImplicitWait.accept(driver);
			setPageLoadWait.accept(driver);
		}
		return driver;
	};

}
