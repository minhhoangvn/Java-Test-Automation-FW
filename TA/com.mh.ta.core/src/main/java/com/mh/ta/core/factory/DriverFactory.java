package com.mh.ta.core.factory;

public interface DriverFactory<DriverManager, Driver, EnumType> {
	void createDriverManager(EnumType type);

	Driver getDriver(EnumType type);

	DriverManager getDriverManager(EnumType type);
	
	void removeDriverManager(EnumType type);
}
