package com.mh.ta.factory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.base.selenium.webdriver.Browsers;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.config.DriverConfig;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.core.helper.FileUtility;
import com.mh.ta.enums.DriverExecutable;
import com.mh.ta.interfaces.driver.IDriver;

public class DriverFactory {
	static ThreadLocal<IDriver<?>> driverManagerStorage = new ThreadLocal<IDriver<?>>();

	public static void createDriver(String driverType, String gridServer) {
		if (driverManagerStorage.get() == null) {
			setDriverProperty(driverType);
			SeleniumDriver driverManager = GuiceInjectFactory.instance().getObjectInstance(SeleniumDriver.class);
			setUpDriver(driverType, gridServer, driverManager);
		}
	}

	public static IDriver<?> getDriver() {
		if (driverManagerStorage.get() == null) {
			throw new TestContextException(
					"Call createDriver method before can get driver, or set enableAPITest annotation in @EnableOneTimeConfig to false ");
		}
		return driverManagerStorage.get();
	}

	public static void diposeDriver() {
		if (driverManagerStorage.get() != null)
			driverManagerStorage.get().diposeDriver();
		driverManagerStorage.remove();
	}

	private static void configDriver(IDriver<?> driver) {
		try {
			SeleniumDriver seleniumDriver = (SeleniumDriver) driver;
			DriverConfig driverConfig = getMainConfigInject().getDriverConfig();
			seleniumDriver.setImplicitlyWait(driverConfig.getImplicitWait());
			seleniumDriver.setPageLoadTimeOut(driverConfig.getPageloadTimeout());
			configBrowserWindows(driverConfig, seleniumDriver);
		} catch (Exception e) {
			throw new TestContextException(e);
		}
	}

	private static void setUpDriver(String driverType, String gridServer, SeleniumDriver driverManager) {
		setUpRemoteDrvierOptions(driverType, gridServer, driverManager);
		driverManager.createDriver(driverType);
		driverManagerStorage.set(driverManager);
		configDriver(getDriver());
	}

	private static void setDriverProperty(String browserType) {
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

	private static void setLocalDriver(String browserType) {
		String os = System.getProperty("os.name").toLowerCase();
		Path driverExecutablePath = FileUtility
				.findFileOrFolderPath(getMainConfigInject().getDriverConfig().getDriversFolderName(), true);
		if (os.indexOf("win") >= 0)
			setWindowsOsDriverProps(driverExecutablePath, browserType);
		else
			setMacOsDriverProps(driverExecutablePath, browserType);
	}

	private static void setUpRemoteDrvierOptions(String driverType, String gridServer, SeleniumDriver driverManager) {
		if (Browsers.getTypeByString(driverType) == Browsers.REMOTE)
			driverManager.setDriverOptions(gridServer);
	}

	private static void setWindowsOsDriverProps(Path driverFolder, String browserType) {
		Map<String, String> executableOptions = DriverExecutable.getDriverExecutableConfig(browserType);
		System.setProperty(executableOptions.get("key"), Paths
				.get(driverFolder.toAbsolutePath().toString(), executableOptions.get("driver") + ".exe").toString());
	}

	private static void setMacOsDriverProps(Path driverFolder, String browserType) {
		Map<String, String> executableOptions = DriverExecutable.getDriverExecutableConfig(browserType);
		System.setProperty(executableOptions.get("key"),
				Paths.get(driverFolder.toAbsolutePath().toString(), executableOptions.get("driver")).toString());
	}

	private static MainConfig getMainConfigInject() {
		Injector injector = GuiceInjectFactory.instance().getInject();
		MainConfig config = injector.getInstance(Key.get(MainConfig.class, ApplicationConfig.class));
		return config;
	}

	private static void configBrowserWindows(DriverConfig driverConfig, SeleniumDriver driver) {
		if (driverConfig.isMaximize())
			((SeleniumDriver) driver).maximizeWindows();
		if (driverConfig.isHidden())
			((SeleniumDriver) driver).hiddenWindows();
	}
}
