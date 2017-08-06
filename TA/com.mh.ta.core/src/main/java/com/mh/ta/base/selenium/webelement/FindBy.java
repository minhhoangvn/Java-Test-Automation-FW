package com.mh.ta.base.selenium.webelement;

public class FindBy {
	private String locatorValue;
	private ByType locatorType;

	public String getLocatorValue() {
		return locatorValue;
	}

	public ByType getLocatorType() {
		return locatorType;
	}

	private FindBy(ByType type, String value) {
		this.locatorType = type;
		this.locatorValue = value;
	}

	public static FindBy elementId(String value) {
		return new FindBy(ByType.ID, value);
	}

	public static FindBy elementName(String value) {
		return new FindBy(ByType.NAME, value);
	}

	public static FindBy cssValue(String value) {
		return new FindBy(ByType.CSS, value);
	}

	public static FindBy xpath(String value) {
		return new FindBy(ByType.XPATH, value);
	}

	public static FindBy hyperLink(String value) {
		return new FindBy(ByType.LINK, value);
	}

	public static FindBy tagName(String value) {
		return new FindBy(ByType.TAG, value);
	}

	public static FindBy className(String value) {
		return new FindBy(ByType.CLASS, value);
	}
}
