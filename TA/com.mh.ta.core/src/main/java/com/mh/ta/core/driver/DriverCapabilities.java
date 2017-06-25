package com.mh.ta.core.driver;

public interface DriverCapabilities<DesiredCapabilities> {
	public DesiredCapabilities getDesiredCapabilities();
	public void setDesiredCapabilities(DesiredCapabilities capabilities);
}
