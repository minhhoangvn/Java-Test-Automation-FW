package com.mh.ta.core.config;

import com.mh.ta.core.annotation.Settings;

@Settings(configFileName="application")
public class FrameworkSettings {

	private SUTConfig sutConfig;
	private DriverConfig driverConfig;

	public SUTConfig getSutConfig() {
		return sutConfig;
	}

	public void setSutConfig(SUTConfig sutConfig) {
		this.sutConfig = sutConfig;
	}

	public DriverConfig getDriverConfig() {
		return driverConfig;
	}

	public void setDriverConfig(DriverConfig driverConfig) {
		this.driverConfig = driverConfig;
	}

	public static class DriverConfig {
		private String type, browser, driversFolderName;
		private int implicitWait = 60, pageloadTimeout = 60, drivercommandTimeout = 60;

		public String getDriversFolderName() {
			return driversFolderName;
		}

		public void setDriversFolderName(String driverFolderName) {
			this.driversFolderName = driverFolderName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getBrowser() {
			return browser;
		}

		public void setBrowser(String browser) {
			this.browser = browser;
		}

		public int getImplicitWait() {
			return implicitWait;
		}

		public void setImplicitWait(int implicitWait) {
			this.implicitWait = implicitWait;
		}

		public int getPageloadTimeout() {
			return pageloadTimeout;
		}

		public void setPageloadTimeout(int pageloadTimeout) {
			this.pageloadTimeout = pageloadTimeout;
		}

		public int getDrivercommandTimeout() {
			return drivercommandTimeout;
		}

		public void setDrivercommandTimeout(int drivercommandTimeout) {
			this.drivercommandTimeout = drivercommandTimeout;
		}

		public Boolean getHidden() {
			return hidden;
		}

		public void setHidden(Boolean hidden) {
			this.hidden = hidden;
		}

		public Boolean getMaximize() {
			return maximize;
		}

		public void setMaximize(Boolean maximize) {
			this.maximize = maximize;
		}

		private Boolean hidden, maximize;
	}

	public static class SUTConfig {
		private String baseUri, username, password, databaseConnectionString, databaseUserName, databasePassword;

		public String getBaseUri() {
			return baseUri;
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

		protected String getDatabasePassword() {
			return databasePassword;
		}

		public void setDatabasePassword(String databasePassword) {
			this.databasePassword = databasePassword;
		}

		public void setBaseUri(String baseUri) {
			this.baseUri = baseUri;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
