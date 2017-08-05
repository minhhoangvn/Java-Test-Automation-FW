package com.mh.ta.core.config;

public class DataSourceConfig {
	String databaseDriver, databaseConnectionString, databaseUserName, databasePassword, testDataFolder = "TestData";

	public String getDatabaseDriver() {
		return databaseDriver;
	}

	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}

	public String getDatabaseConnectionString() {
		return databaseConnectionString;
	}

	public void setDatabaseConnectionString(String databaseConnectionString) {
		this.databaseConnectionString = databaseConnectionString;
	}

	public String getDatabaseUserName() {
		return databaseUserName;
	}

	public void setDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getTestDataFolder() {
		return testDataFolder;
	}

	public void setTestDataFolder(String testDataFolder) {
		this.testDataFolder = testDataFolder;
	}
}
