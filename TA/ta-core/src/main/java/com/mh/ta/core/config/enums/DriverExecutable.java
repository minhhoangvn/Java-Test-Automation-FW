
package com.mh.ta.core.config.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author minhhoang
 *
 */
public enum DriverExecutable {
	CHROME("CHROME", "chromedriver", "webdriver.chrome.driver"), FIREFOX("FIREFOX", "geckodriver",
			"webdriver.gecko.driver"), IE("IE", "IEDriverServer", "webdriver.ie.driver");
	private String executableFileName;
	private String systemPropertyKey;
	private String browserName;

	private DriverExecutable(String browserName, String executableFileName, String systemPropertyKey) {
		this.browserName = browserName;
		this.executableFileName = executableFileName;
		this.systemPropertyKey = systemPropertyKey;
	}

	public static Map<String, String> getDriverExecutableConfig(String driverType) {
		Map<String, String> driverExecutableConfig = new HashMap<>();
		for (DriverExecutable type : DriverExecutable.values()) {
			if (type.browserName.equalsIgnoreCase(driverType)) {
				driverExecutableConfig.put("driver", type.executableFileName);
				driverExecutableConfig.put("key", type.systemPropertyKey);
				return driverExecutableConfig;
			}
		}
		return driverExecutableConfig;
	}
}
