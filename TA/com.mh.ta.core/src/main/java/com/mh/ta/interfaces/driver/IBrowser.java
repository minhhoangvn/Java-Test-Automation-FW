package com.mh.ta.interfaces.driver;

public interface IBrowser {

	public String getPageTitle();

	public String getPageSource();

	public String executeJavaScript(String sourceScript,Object... args);
}
