package com.mh.ta.core.browsers;

import com.mh.ta.base.browsers.Chrome;

public enum BrowserType {
	CHROME(
			"CHROME", Chrome.class);

	private String value;
	private Class<?> cls;

	private BrowserType(String value, Class<?> browserClass) {
		this.value = value;
		this.cls = browserClass;
	}

	public static Class<?> getBrowserClass(String browser) {

		for (BrowserType type : BrowserType.values()) {
			if (type.value.toLowerCase().equals(browser.toLowerCase())) {
				return type.cls;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
	
	public static BrowserType getTypeByString(String browser){
		for (BrowserType type : BrowserType.values()) {
			if (type.value.toLowerCase().equals(browser.toLowerCase())) {
				return type;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
}
