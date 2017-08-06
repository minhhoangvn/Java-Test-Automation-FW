package com.mh.ta.interfaces.driver;

public interface IDriver<Type> extends IFinder {

	public void createDriver(String driverType);

	public void diposeDriver();

	public void setDriverOptions(Object options);

	public void setCapabilities(Object capabilities);

	public <T> T getWindowsAction();

	public Type getCoreDriver();

}
