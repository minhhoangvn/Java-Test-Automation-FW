
package com.mh.ta.core.config.settings;

/**
 * @author minhhoang
 *
 */
public class DriverConfig {
	private String driversFolderName = "drivers";
	private String borderColor = "red";
	private String backGroundColor = "yellow";
	private int implicitWait = 30;
	private int pageloadTimeout = 60;
	private int drivercommandTimeout = 60;
	private int timeOutHighlight = 500;
	private boolean hidden = false;
	private boolean maximize = false;
	private boolean highLighElement = true;

	public String getDriversFolderName() {
		return driversFolderName;
	}

	public void setDriversFolderName(String driversFolderName) {
		this.driversFolderName = driversFolderName;
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

	public boolean isHighLighElement() {
		return highLighElement;
	}

	public void setHighLighElement(boolean highLighElement) {
		this.highLighElement = highLighElement;
	}

	public int getTimeOutHighlight() {
		return timeOutHighlight;
	}

	public void setTimeOutHighlight(int timeOutHighlight) {
		this.timeOutHighlight = timeOutHighlight;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(String backGroundColor) {
		this.backGroundColor = backGroundColor;
	}
}
