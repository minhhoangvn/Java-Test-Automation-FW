package com.mh.ta.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mh.ta.core.browsers.Browser;
import com.mh.ta.core.helper.InjectorInitializer;
import com.mh.ta.keywords.Keywords;
import com.mh.ta.keywords.WebKeywords;
import com.mh.ta.test.BaseTestNG;

public class TestLogin extends BaseTestNG {
	@Inject
	private LoginPage login;

	@Parameters({ "browser" })
	@BeforeMethod
	public void initPOM(String browser) {
		Injector inject = InjectorInitializer.injectWebDriver(this.inject,
				this.driverFactory.getDriverManager(Browser.getTypeByString(browser)).getDriver());
		inject.injectMembers(this);
		this.driverManager = this.driverFactory.getDriverManager(Browser.getTypeByString(browser));
		inject.getInstance(WebKeywords.class);
		inject.getInstance(LoginPage.class);
		Keywords<WebDriver, WebElement> kw = new WebKeywords(this.driverManager.getDriver());
		System.err.println(kw);
		
	}

	@Test(enabled = true)
	public void testLogin1() throws InterruptedException {
		login.goToLoginPage();
		login.inputEmail().clickNext();
		login.printTitle();
	}

	@AfterMethod
	public void diposeDriver() {
		this.driverManager.diposeDriver();
	}
}
