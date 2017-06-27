package com.mh.ta.core.config;

public interface DriverConfig {
	public Object getServices();

	public Object getOptions();

	public Object getDesiredCapabilities();

	public void setServices();

	public void setOptions();

	public void setDesiredCapabilities();
}
