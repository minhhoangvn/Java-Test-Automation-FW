package com.mh.ta.core.browsers;

import java.util.stream.IntStream;

import com.mh.ta.core.helper.Constants;

public enum BrowserType {

	CHROME(
			"CHROME"),
	FIREFOX(
			"FIREFOX"),
	IE(
		"IE"),
	EDGE(
			"EDGE"),
	SAFARI(
			"SAFARI");

	private String value;

	private BrowserType(String value) {
		this.value = value;
	}

	public static String getBrowser(String browser) {
		String browserName = IntStream
				.concat(IntStream.of(browser.codePointAt(0)).map(Character::toUpperCase), browser.codePoints().skip(1))
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		for (BrowserType type : BrowserType.values()) {
			System.out.println(type.value.toUpperCase());
			if (type.value.toLowerCase().equals(browser.toLowerCase()))
				return String.format("%s.%sBrowser", Constants.WEB_BROWSER_PACKAGE_NAME, browserName);
		}
		throw new IllegalArgumentException(String.format("Browser % is invalid", browser));
	}
}
