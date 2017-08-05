package com.mh.ta.core.config;

public class DriverConfig {
	private String driversFolderName = "drivers", driverType = "web";
	private int implicitWait = 30, pageloadTimeout = 60, drivercommandTimeout = 60;
	private boolean hidden = false, maximize = false;

	public String getDriversFolderName() {
		return driversFolderName;
	}

	public void setDriversFolderName(String driversFolderName) {
		this.driversFolderName = driversFolderName;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isMaximize() {
		return maximize;
	}

	public void setMaximize(boolean maximize) {
		this.maximize = maximize;
	}
}
