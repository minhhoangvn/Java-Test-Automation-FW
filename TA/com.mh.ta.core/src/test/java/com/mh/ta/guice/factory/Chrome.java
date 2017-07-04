package com.mh.ta.guice.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome extends WebDrivers {

	@Override
	protected void createDriver() {
		WebDriver driver = new ChromeDriver();
		drivers.set(driver);
	}

	@Override
	protected void stopDriver() {
		WebDriver driver = drivers.get();
		driver.close();
		driver.quit();
		drivers.remove();
	}

}
