package com.mh.ta.login;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.driver.BrowserDriverManager;
import com.mh.ta.core.helper.InjectorInitializer;
import com.mh.ta.test.BaseTestNG;

public class TestLogin extends BaseTestNG {
	@Inject
	private LoginPage login;
	private BrowserDriverManager driverManager;

	@Parameters({ "browser" })
	@BeforeMethod
	public void initPOM(String browser) {
		Injector inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.getTypeByString(browser)).getDriver());
		inject.injectMembers(this);
		driverManager = this.driverFactory.getDriverManager(Browser.getTypeByString(browser));
		login = inject.getInstance(LoginPage.class);
	}

	@Test(enabled = true)
	public void testLogin1() throws InterruptedException {
		login.goToLoginPage();
		login.inputEmail().clickNext();
	}

	@AfterMethod
	public void diposeDriver() {
		this.driverManager.diposeDriver();
	}
}
