package com.mh.ta.guice;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.mh.ta.base.browsers.Chrome;
import com.mh.ta.core.config.SettingModule;
import com.mh.ta.core.factory.SeleniumDriverFactory;
import com.mh.ta.interfaces.IDriver;
import com.mh.ta.core.factory.GuiceInjectFactory;
public class TestPerson {
	
	@Before(value = "Test")
	public void check(){
		System.err.println("Where are you print");
	}
	@Parameters({ "configFileName" })
	@BeforeMethod
	public void init(@Optional("application")String config) {
		System.err.println(Thread.currentThread().getId() + " " + config);
		GuiceInjectFactory.instance().createInject(new SettingModule(config));
		GuiceInjectFactory.instance().injectToClass(this);
		GuiceInjectFactory.instance().createInject(new AbstractModule() {
			@Override
			protected void configure() {
				bind(new TypeLiteral<IDriver<WebDriver>>() {
				}).to(Chrome.class);
				//bind(SeleniumWebDriver.class).to(Chrome.class);
			}
		});
		System.err.println(Thread.currentThread().getId());
		SeleniumDriverFactory.instance().createDriver();
		SeleniumDriverFactory.instance().getWebDriver();
		// GuiceInjectFactory.instance().createInject(new
		// SettingModule("application1"));
	}

	@Test(invocationCount=10, threadPoolSize=10)
	public void test() throws InterruptedException {
		SeleniumDriverFactory.instance().diposeDriver();
	}
}
