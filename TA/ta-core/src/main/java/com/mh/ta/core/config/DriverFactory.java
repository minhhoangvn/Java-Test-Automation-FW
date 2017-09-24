package com.mh.ta.core.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.inject.ConfigurationException;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.config.driver.WebDriverFactory;
import com.mh.ta.core.config.driver.browser.Browsers;
import com.mh.ta.core.config.driver.browser.SeleniumDriver;
import com.mh.ta.core.config.enums.DriverExecutable;
import com.mh.ta.core.config.settings.DriverConfig;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.core.helper.FileUtility;

/**
 * @author minhhoang
 *
 */
public class DriverFactory {
	private LinkedMap<Long, Driver> driverStorage = new LinkedMap<>();
	private ThreadLocal<Object> optionsStorage = new ThreadLocal<>();
	private ThreadLocal<DesiredCapabilities> capabilitiesStorage = new ThreadLocal<>();
	private static Logger log = LoggerFactory.instance().createClassLogger(DriverFactory.class);
	private static DriverFactory instance;

	public static synchronized DriverFactory instance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public void setDriverOptions(Object options) {
		logInfoWithThreadId(" set up driver option with " + options);
		optionsStorage.set(options);
	}

	public void setDesiredCapabilities(DesiredCapabilities capabilities) {
		logInfoWithThreadId(" set up driver capabilities with " + capabilities);		
		capabilitiesStorage.set(capabilities);
	}

	public void createDriver(String driverType, String gridServer) {
		if (!driverStorage.containsKey(Thread.currentThread().getId())) {
			setDriverProperty(driverType);
			instanceWebDriver(driverType, gridServer);
		}
	}
	
	private void setDriverProperty(String browserType) {
		switch (Browsers.getTypeByString(browserType)) {
		case CHROME:
		case FIREFOX:
		case IE:
			setLocalDriver(browserType);
			break;
		default:
			break;
		}
	}

	private void instanceWebDriver(String driverType, String gridServer) {
		switch (Browsers.getTypeByString(driverType)) {
		case FIREFOX:
		case CHROME:
		case IE:
			createLocalDriver(driverType);
			break;
		case REMOTE:
			createRemoteDriver(driverType, gridServer);
			break;
		}
	}

	private void createLocalDriver(String driverType) {
		SeleniumDriver driverCreator = getSeleniumInstance(driverType);
		driverCreator.setCapabilities(capabilitiesStorage.get());
		WebDriver driver = driverCreator.createDriver();
		configDriver(driver);
		Driver driverDecorate = getWebDriverFactory().create(driver);
		setDriverStorage(driverDecorate);
		logInfoWithThreadId(" create driver " + driverType);		
	}

	private void createRemoteDriver(String driverType, String gridServer) {
		SeleniumDriver driverCreator = getSeleniumInstance(driverType);
		driverCreator.setDriverOptions(gridServer);
		driverCreator.setCapabilities(capabilitiesStorage.get());
		WebDriver remoteDriver = driverCreator.createDriver();
		configDriver(remoteDriver);
		Driver driverDecorate = getWebDriverFactory().create(remoteDriver);
		setDriverStorage(driverDecorate);
		logInfoWithThreadId(" create driver " + driverType);
	}

	private WebDriverFactory getWebDriverFactory() {
		try {
			return GuiceInjectFactory.instance().createObjectInstance(WebDriverFactory.class);
		} catch (ConfigurationException e) {
			log.warn("Has error in create DriverFactory, please check has enable at least 1 test script", e.getCause());
			throw new TestContextException("Has error in create DriverFactory ", e);
		}
	}

	private SeleniumDriver getSeleniumInstance(String driverType) {
		Class<SeleniumDriver> browserClass = Browsers.getClassByString(driverType);
		return GuiceInjectFactory.instance().createObjectInstance(browserClass);
	}

	private void setLocalDriver(String browserType) {
		String os = System.getProperty("os.name").toLowerCase();
		Path driverExecutablePath = FileUtility
				.findFileOrFolderPath(getMainConfigInject().getDriverConfig().getDriversFolderName(), true);
		if (os.indexOf("win") >= 0)
			setWindowsOsDriverProps(driverExecutablePath, browserType);
		else
			setMacOsDriverProps(driverExecutablePath, browserType);
	}

	private void configDriver(WebDriver driver) {
		try {
			DriverConfig driverConfig = getMainConfigInject().getDriverConfig();
			configTimeout(driverConfig, driver);
			configBrowserWindows(driverConfig, driver);
		} catch (Exception e) {
			log.error("Thread id " + Thread.currentThread().getId() + " has error in configuration for WebDriver ",
					e.getCause());
			throw new TestContextException("Has error in configuration for WebDriver " + e);
		}
	}

	private void configTimeout(DriverConfig driverConfig, WebDriver driver) {
		Options option = driver.manage();
		Timeouts timeOut = option.timeouts();
		timeOut.implicitlyWait(driverConfig.getImplicitWait(), TimeUnit.SECONDS);
		timeOut.pageLoadTimeout(driverConfig.getPageloadTimeout(), TimeUnit.SECONDS);
	}

	private void configBrowserWindows(DriverConfig driverConfig, WebDriver driver) {
		Options option = driver.manage();
		Window window = option.window();
		if (driverConfig.isMaximize())
			window.maximize();
		if (driverConfig.isHidden())
			window.setPosition(new org.openqa.selenium.Point(0, -2000));
	}

	private void setWindowsOsDriverProps(Path driverFolder, String browserType) {
		Map<String, String> executableOptions = DriverExecutable.getDriverExecutableConfig(browserType);
		System.setProperty(executableOptions.get("key"), Paths
				.get(driverFolder.toAbsolutePath().toString(), executableOptions.get("driver") + ".exe").toString());
	}

	private void setMacOsDriverProps(Path driverFolder, String browserType) {
		Map<String, String> executableOptions = DriverExecutable.getDriverExecutableConfig(browserType);
		System.setProperty(executableOptions.get("key"),
				Paths.get(driverFolder.toAbsolutePath().toString(), executableOptions.get("driver")).toString());
	}

	private void setDriverStorage(Driver driverDecorate) {
		synchronized (driverStorage) {
			driverStorage.put(Thread.currentThread().getId(), driverDecorate);
		}
	}

	private MainConfig getMainConfigInject() {
		return GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class, ApplicationConfig.class);
	}
	
	/**
	 * return WebDriver base on thread id in case set up one time in
	 * configuration it return by first index of map
	 * 
	 * @return
	 */
	public Driver getDriver() {
		if (driverStorage.size() == 0) {
			log.error("Thread id has erorr in create driver" + Thread.currentThread().getId()
					+ "Call createDriver method before can get driver, or set enableAPITest annotation in @EnableOneTimeConfig to false");
			throw new TestContextException(
					"Call createDriver method before can get driver, or set enableAPITest annotation in @EnableOneTimeConfig to false ");
		}
		if (driverStorage.containsKey(Thread.currentThread().getId()))
			return driverStorage.get(Thread.currentThread().getId());
		return driverStorage.getValue(0);
	}

	
	public void diposeAllDriver() {
		log.info(Thread.currentThread().getId() + " Dipose all driver in driver storage");
		for (Entry<Long, Driver> driver : driverStorage.entrySet()) {
			Driver value = driver.getValue();
			value.quit();
		}
	}

	public void diposeDriver() {
		logInfoWithThreadId(" dipose driver");			
		if (driverStorage.containsKey(Thread.currentThread().getId()))
			driverStorage.get(Thread.currentThread().getId()).quit();
		driverStorage.remove(Thread.currentThread().getId());
	}

	private void logInfoWithThreadId(String msg) {
		log.info("Thread id " + Thread.currentThread().getId() + msg);
	}
}
