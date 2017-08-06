package com.mh.ta.base.selenium.webdriver;

public interface BrowserDriver<Type> {
	Type createDriver();

	public void setDriverOptions(Object options);

	public void setCapabilities(Object capabilities);
}
