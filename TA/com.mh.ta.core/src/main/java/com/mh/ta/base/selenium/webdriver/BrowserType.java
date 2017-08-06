package com.mh.ta.base.selenium.webdriver;
import com.mh.ta.interfaces.driver.IDriver;

public enum BrowserType {
	CHROME("CHROME", Chrome.class), FIREFOX("FIREFOX", Firefox.class), IE("IE", IE.class), REMOTE("REMOTE",
			Remote.class);

	private String value;
	private Class<?> cls;

	private BrowserType(String value, Class<?> browserClass) {
		this.value = value;
		this.cls = browserClass;
	}

	@SuppressWarnings("unchecked")
	public static <E extends IDriver<?>> Class<E> getBrowserClass(String browser) {

		for (BrowserType type : BrowserType.values()) {
			if (type.value.equalsIgnoreCase(browser.toLowerCase())) {
				return (Class<E>) type.cls;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}

	public static BrowserType getTypeByString(String browser) {
		for (BrowserType type : BrowserType.values()) {
			if (type.value.equalsIgnoreCase(browser.toLowerCase())) {
				return type;
			}
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
}
