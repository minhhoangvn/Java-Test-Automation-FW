package com.mh.ta.login;



import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mh.ta.core.factory.DriverFactory;
import com.mh.ta.core.factory.PageObjectFactory;

public class TestReflection {
	@BeforeTest
	public void initDriver() {
		DriverFactory.startDriver("");
	}

	@Test
	public void testPageFactory() {

		LoginPage login = PageObjectFactory.getPageObject(LoginPage.class, LoginElements.class, LoginValidations.class);
		login.goToLoginPage().inputEmail().clickNext().inputPassword();
		System.err.println(login.Validations().shouldShowPassowdField());

	}

	@AfterTest
	public void diposeDriver() {
		DriverFactory.diposeDriver();
	}
}
