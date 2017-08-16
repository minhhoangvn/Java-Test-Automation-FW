package com.mh.ta.base.test;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.testng.IObjectFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.mh.ta.base.selenium.services.KeywordModule;
import com.mh.ta.base.selenium.services.SeleniumElementModule;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.annotation.DataSource;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.config.MainConfigModule;
import com.mh.ta.core.helper.ExcelUtility;
import com.mh.ta.core.helper.FileUtility;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.TestClassFactory;
import com.mh.ta.support.ReportListener;

@Listeners({ ReportListener.class })
public class BaseWebTestNG {

	protected boolean configOneTime = false;
	protected boolean enableAPITest = false;

	@BeforeSuite
	@Parameters({ "configFile", "browser", "gridServerOptions" })
	protected void setUpSuite(@Optional("application.yaml") String configFile, @Optional("chrome") String browserType,
			@Optional("null") String gridServerOptions) {
		if (configOneTime) {
			configTest(configFile);
			initDriver(browserType, gridServerOptions);
		}

	}

	@Parameters({ "configFile", "browser", "gridServerOptions" })
	@BeforeMethod
	protected void setUpTest(@Optional("application.yaml") String configFile, @Optional("chrome") String browserType,
			@Optional("null") String gridServerOptions) {
		if (!configOneTime) {
			configTest(configFile);
			initDriver(browserType, gridServerOptions);
		}

	}

	@AfterMethod
	protected void cleanUpTest() {
		if (!configOneTime)
			diposeDriver();
	}

	@AfterSuite
	public void cleanUpSuites() {
		if (configOneTime)
			diposeDriver();
	}

	protected List<List<String>> getAllExcelSheetData(Method m) {
		ExcelUtility excel = new ExcelUtility();
		DataSource dataSource = m.getAnnotation(DataSource.class);
		if (dataSource != null) {
			Path dataFilePath = FileUtility.findFileOrFolderPath(dataSource.dataFileName(), false).toAbsolutePath();
			List<List<String>> sheetData = excel.readAllSheetValue(dataFilePath.toString(), dataSource.sheetName(),
					true);
			return sheetData;
		}
		return new ArrayList<List<String>>();
	}

	private void configTest(String configFile) {
		try {
			Injector injector = GuiceInjectFactory.instance().getInject();
			injector.getInstance(Key.get(MainConfig.class, ApplicationConfig.class));
			
		} catch (ConfigurationException e) {
			GuiceInjectFactory.instance().createInject(new MainConfigModule(configFile));
			GuiceInjectFactory.instance().createInject(new SeleniumElementModule());
			GuiceInjectFactory.instance().createInject(new KeywordModule());			
		}
		finally {
			GuiceInjectFactory.instance().injectToClass(this);
		}
	}

	private void initDriver(String browserType, String gridServerOptions) {
		if (!enableAPITest)
			DriverFactory.createDriver(browserType, gridServerOptions);
	}

	private void diposeDriver() {
		if (!enableAPITest)
			DriverFactory.diposeDriver();
	}

	@ObjectFactory
	public IObjectFactory initTestClassFactory() {
		IObjectFactory factory = new TestClassFactory();
		return factory;
	}
}
