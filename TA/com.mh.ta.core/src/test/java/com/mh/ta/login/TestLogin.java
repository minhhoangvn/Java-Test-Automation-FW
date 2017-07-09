package com.mh.ta.login;

import org.testng.annotations.Test;

import com.google.inject.Injector;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.factory.PageObjectFactory;
import com.mh.ta.core.helper.InjectorInitializer;
import com.mh.ta.test.BaseTestNG;

public class TestLogin extends BaseTestNG {

	@Test(enabled = true)
	public void testLogin1() throws InterruptedException {
		Injector inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.CHROME).getDriver());
		LoginPage login = PageObjectFactory.initPageObject(inject, LoginPage.class);
		login.goToLoginPage();
		login.inputEmail().clickNext();
		this.driverFactory.getDriver(Browser.CHROME).quit();
	}

	@Test(enabled = true)
	public void testLogin2() {
		Injector inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.FIREFOX).getDriver());
		LoginPage login = PageObjectFactory.initPageObject(inject, LoginPage.class);
		login.goToLoginPage();
		login.inputEmail().clickNext();
		this.driverFactory.getDriverManager(Browser.FIREFOX).diposeDriver();

	}
}
