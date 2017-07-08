package com.mh.ta.core.browsers;

import com.mh.ta.core.web.Chrome;
import com.mh.ta.core.web.Firefox;
import com.mh.ta.core.web.IE;

public enum Browser {
	CHROME(
			"CHROME", Chrome.class),
	FIREFOX(
			"FIREFOX", Firefox.class),
	IE(
		"IE", IE.class);

	private String value;
	private Class<?> cls;

	private Browser(String value, Class<?> browserClass) {
		this.value = value;
		this.cls = browserClass;
	}

	public static Class<?> getBrowserClass(String browser) {

		for (Browser type : Browser.values()) {
			if (type.value.toLowerCase().equals(browser.toLowerCase())) {
				return type.cls;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
	
	public static Browser getTypeByString(String browser){
		for (Browser type : Browser.values()) {
			if (type.value.toLowerCase().equals(browser.toLowerCase())) {
				return type;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
}
