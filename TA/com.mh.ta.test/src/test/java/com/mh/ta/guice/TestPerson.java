package com.mh.ta.guice;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.base.test.BaseWebTestNG;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.SeleniumDriverFactory;
import com.mh.ta.interfaces.element.TAElement;
import com.mh.ta.test.pages.LoginPage;

public class TestPerson extends BaseWebTestNG {

	@Inject
	LoginPage login;

	@Test
	public void testLoginPage() throws InterruptedException {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
		Thread.sleep(2000);
	}

	@Test(enabled = false, invocationCount = 1, threadPoolSize = 1)
	@Parameters("gridServerOptions")
	public void test(String valueInput) throws InterruptedException {
		GuiceInjectFactory.instance().injectToClass(this);
		SeleniumDriver drivers = SeleniumDriverFactory.getSeleniumDriver();
		// Driver<WebDriver> drivers = SeleniumDriverFactory.getDriver();
		// System.err.println(drivers.createDriver());
		// drivers.diposeDriver();

		drivers.navigateToUrl("http://www.google.com");
		// drivers.quit();
		// drivers.getDriver().get("http://www.google.com");

		TAElement element = drivers.findElement(FindBy.elementId("lst-ib"));
		element.inputTextToElement(valueInput);
		// drivers.diposeDriver();
		// element.sendKey("Test");
	}
}
