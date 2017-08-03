package com.mh.ta.interfaces;

public interface IDriver<Type> {
	public Type getDriver();

	public void createDriver();

	public void diposeDriver();

	public void setDriverOptions(Object options);

	public void setDriverServices(Object services);

	public void setCapabilities(Object capabilities);
}
