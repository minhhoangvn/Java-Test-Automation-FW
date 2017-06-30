package com.mh.ta.page;

import org.openqa.selenium.WebDriver;

import com.mh.ta.core.factory.DriverFactory;

public class BaseElements {
	protected WebDriver driver;

	public BaseElements() {
		this.driver = DriverFactory.getDriver();
	}
}
