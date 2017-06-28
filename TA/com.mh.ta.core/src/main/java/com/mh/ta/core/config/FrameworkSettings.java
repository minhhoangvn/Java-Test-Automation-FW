package com.mh.ta.core.config;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class FrameworkSettings {

	private HashMap<String, String> driverConfig;
	private HashMap<String, String> sutConfig;

	public HashMap<String, String> getDriverConfig() {
		return driverConfig;
	}

	public void setDriverConfig(HashMap<String, String> driverConfig) {
		this.driverConfig = driverConfig;
	}

	public HashMap<String, String> getSutConfig() {
		return sutConfig;
	}

	public void setSutConfig(HashMap<String, String> sutConfig) {
		this.sutConfig = sutConfig;
	}

}
