package com.mh.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mh.ta.core.annotation.EnableOneTimeConfig;
import com.mh.ta.core.annotation.RecordVideo;
import com.mh.ta.enums.TestStatus;

@EnableOneTimeConfig(enable = false, enableAPITest = false)
public class GUITest extends BaseTest {
	@Test(threadPoolSize = 3, invocationCount = 3)
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

	@RecordVideo(enabled = true, videoName = "TestRecordVideo", saveOnTestStatus = TestStatus.PASSED)
	@Test
	public void TestLoginD() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
	}

	@RecordVideo(enabled = true, videoName = "TestRecordVideo", saveOnTestStatus = TestStatus.FAILED)
	@Test(invocationCount = 5, threadPoolSize = 5)
	public void TestLoginE() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
		Assert.assertTrue(false);
	}

	@RecordVideo(enabled = true, videoName = "TestRecordVideo", saveOnTestStatus = TestStatus.ALL)
	@Test
	public void TestLoginF() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
	}

	@Test(invocationCount = 2, threadPoolSize = 2)
	public void TestLoginG() {
		login.goToLoginPage();
		login.inputEmail();
		login.clickNext();
		login.inputPassword();
	}
}
