package com.mh.ta.login;

import org.testng.annotations.Test;

import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.factory.PageObjectFactory;
import com.mh.ta.core.helper.InjectorInitializer;
import com.mh.ta.test.BaseTestNG;

public class TestLogin extends BaseTestNG {
	@Test
	public void testLogin() {
		this.inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.CHROME).getDriver());
		LoginPage login = PageObjectFactory.initPageObject(this.inject, LoginPage.class);
		login.goToLoginPage();
		login.inputEmail().clickNext();
		this.driverFactory.getDriver(Browser.CHROME).quit();

	}
}
