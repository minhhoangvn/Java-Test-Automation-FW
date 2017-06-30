package com.mh.ta.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mh.ta.core.factory.DriverFactory;
import com.mh.ta.core.factory.PageObjectFactory;

public class TestReflection {
	@Before
	public void initDriver() {
		DriverFactory.startDriver("");
	}

	@Test
	public void testPageFactory() {

		LoginPage login = PageObjectFactory.getPageObject(LoginPage.class, LoginElements.class, LoginValidations.class);
		login.goToLoginPage().inputEmail().clickNext().inputPassword();
		System.err.println(login.Validations().shouldShowPassowdField());

	}

	@After
	public void diposeDriver() {
		DriverFactory.diposeDriver();
	}
}
