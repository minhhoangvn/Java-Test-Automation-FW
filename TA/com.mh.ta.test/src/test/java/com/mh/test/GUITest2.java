package com.mh.test;

import org.testng.annotations.Test;

import com.mh.ta.core.annotation.EnableOneTimeConfig;

@EnableOneTimeConfig(enable = true, enableAPITest = false)
public class GUITest2 extends BaseTest {


	@Test(groups="login")
	public void test() {
	
		login.goToLoginPage();
	}
	
	@Test(dependsOnGroups="login")
	public void testa(){
		login.inputEmail();
	}

}
