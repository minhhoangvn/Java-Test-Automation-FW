package com.mh.ta.core.config;

import com.google.inject.Inject;

public class MainConfig {
	@Inject
	private FrameworkSettings settings;

	public FrameworkSettings.DriverConfig getDriverConfig() {
		return settings.getDriverConfig();
	}

	public FrameworkSettings.SUTConfig getSUTConfig() {
		return settings.getSutConfig();
	}

}
