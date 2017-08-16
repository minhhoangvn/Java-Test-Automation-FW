package com.mh.test;

import org.testng.annotations.Test;

import com.mh.ta.core.annotation.EnableOneTimeConfig;

@EnableOneTimeConfig(enable = false, enableAPITest = false)
public class GUITest extends BaseTest {
	@Test(threadPoolSize = 2, invocationCount = 2)
	public void testLoginA() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
	}

	@Test
	public void TestLoginB() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
	}

	@Test
	public void TestLoginC() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
	}

	
}
