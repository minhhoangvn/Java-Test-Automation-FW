package com.mh.ta.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.google.inject.Injector;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriver;
import com.mh.ta.core.driver.Driver;
import com.mh.ta.core.factory.BrowserDriverFactory;
import com.mh.ta.core.factory.DriverFactory;
import com.mh.ta.core.factory.PageObjectFactory;
import com.mh.ta.core.helper.InjectorInitializer;
import com.mh.ta.test.BaseTestNG;

public class TestLogin extends BaseTestNG {
	@Test
	public void testLogin3() {
		DriverFactory<BrowserDriver, WebDriver, Browser> f = new BrowserDriverFactory();
		BrowserDriver driverManager = f.getDriverManager(Browser.CHROME);
		System.err.println(driverManager);
		
	}

	@Test(enabled = false)
	public void testLogin1() throws InterruptedException {
		Injector inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.CHROME).getDriver());
		LoginPage login = PageObjectFactory.initPageObject(inject, LoginPage.class);

		login.goToLoginPage();
		login.inputEmail().clickNext();

		this.driverFactory.getDriver(Browser.CHROME).quit();
		System.err.println(((RemoteWebDriver) this.driverFactory.getDriver(Browser.CHROME)).getSessionId());
	}

	@Test(enabled = false)
	public void testLogin2() {
		Injector inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.CHROME).getDriver());
		LoginPage login = PageObjectFactory.initPageObject(inject, LoginPage.class);
		login.goToLoginPage();
		login.inputEmail().clickNext();
		this.driverFactory.getDriverManager(Browser.CHROME).diposeDriver();

	}
}
