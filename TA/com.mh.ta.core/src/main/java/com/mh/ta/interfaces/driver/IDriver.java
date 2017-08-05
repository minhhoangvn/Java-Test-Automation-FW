package com.mh.ta.interfaces.driver;

public interface IDriver<Type>{
	public Type getDriver();

	public void createDriver();

	public void diposeDriver();

	public void setDriverOptions(Object options);

	public void setCapabilities(Object capabilities);
}
