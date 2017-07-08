package com.mh.ta.core.factory;

public interface DriverFactory<DriverManager, Driver, EnumType> {

	Driver getDriver(EnumType type);

	DriverManager getDriverManager(EnumType type);
	
}
