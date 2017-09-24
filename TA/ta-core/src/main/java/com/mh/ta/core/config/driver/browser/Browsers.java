
package com.mh.ta.core.config.driver.browser;

/**
 * @author minhhoang
 *
 */
public enum Browsers {
	CHROME("CHROME", Chrome.class), FIREFOX("FIREFOX", Firefox.class), IE("IE", IE.class), REMOTE("REMOTE",
			Remote.class);

	private String value;
	@SuppressWarnings("rawtypes")
	private Class cls;

	@SuppressWarnings("rawtypes")
	private Browsers(String value, Class cls) {
		this.value = value;
		this.cls = cls;
	}

	public static Browsers getTypeByString(String browser) {
		for (Browsers type : Browsers.values()) {
			if (type.value.equalsIgnoreCase(browser.toLowerCase())) {
				return type;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}


	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassByString(String browser) {
		for (Browsers type : Browsers.values()) {
			if (type.value.equalsIgnoreCase(browser.toLowerCase())) {
				return type.cls;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
}
