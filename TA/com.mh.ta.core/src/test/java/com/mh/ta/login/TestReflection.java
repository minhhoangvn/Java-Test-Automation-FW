package com.mh.ta.login;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mh.ta.core.factory.DriverFactory;
import com.mh.ta.core.factory.PageObjectFactory;

import io.appium.java_client.AppiumDriver;

public class TestReflection {
	@BeforeMethod
	public void initDriver(){
		//DriverFactory.startDriver("");
		System.err.println("Current threead id of initdriver " + Thread.currentThread().getId());
	}
	@AfterMethod
	public void disposeDriver(){
		//DriverFactory.<WebDriver>getDriver();
		System.err.println("Current threead id of diposedriver " + Thread.currentThread().getId());
		//DriverFactory.diposeDriver();
	}
	@Test
	public void testPageFactory() {
		//DriverFactory.<AppiumDriver<WebElement>>getDriver();
		//DriverFactory.startDriver("");
		System.err.println("Current threead id of testPageFactory " + Thread.currentThread().getId());
		LoginPage login = PageObjectFactory.getPageObject(LoginPage.class, LoginElements.class, LoginValidations.class);
		login.goToLoginPage().inputEmail().clickNext().inputPassword();
		System.err.println(login.Validations().shouldShowPassowdField());
		//DriverFactory.diposeDriver();
	}
	 
	@Test
	public void testGoToGooleAndNews1(){
		//DriverFactory.startDriver("");
		System.err.println("Current threead id of testGoToGooleAndNews1 " + Thread.currentThread().getId());
		//DriverFactory.<WebDriver>getDriver().get("http://www.google.com");
		//DriverFactory.<WebDriver>getDriver().get("http://dantri.com.vn");	
		//DriverFactory.diposeDriver();
	}
	
	@Test
	public void testGoToGooleAndNews2(){
		//DriverFactory.startDriver("");
		System.err.println("Current threead id of testGoToGooleAndNews2 " + Thread.currentThread().getId());
		//DriverFactory.<WebDriver>getDriver().get("http://www.google.com");
		//DriverFactory.<WebDriver>getDriver().get("http://dantri.com.vn");
		//DriverFactory.<WebDriver>getDriver().get("http://vtc.vn");
		//DriverFactory.diposeDriver();
		FirefoxBinary services = new FirefoxBinary();
		FirefoxOptions op = new FirefoxOptions();
		FirefoxProfile pro = new FirefoxProfile();
		DesiredCapabilities cap = DesiredCapabilities.firefox();
	}
}
