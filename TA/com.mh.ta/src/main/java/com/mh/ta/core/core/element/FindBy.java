package com.mh.ta.core.core.element;

public class FindBy {
	private LocatorType type;
	private String locatorValue;
	private Element parentElement;

	public LocatorType getType() {
		return type;
	}

	private void setType(LocatorType type) {
		this.type = type;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	private void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}

	public Element getParentElement() {
		return parentElement;
	}

	private void setParentElement(Element parentElement) {
		this.parentElement = parentElement;
	}

	public FindBy(LocatorType type, String locatorValue) {
		this.setType(type);
		this.setLocatorValue(locatorValue);
	}

	public FindBy(LocatorType type, String locatorValue, Element parrent) {
		this.setType(type);
		this.setLocatorValue(locatorValue);
		this.setParentElement(parrent);
	}

	public static FindBy id(String locatorValue) {
		return new FindBy(LocatorType.ID, locatorValue);
	}

	public static FindBy name(String locatorValue) {
		return new FindBy(LocatorType.NAME, locatorValue);
	}

	public static FindBy className(String locatorValue) {
		return new FindBy(LocatorType.CLASS, locatorValue);
	}

	public static FindBy xpath(String locatorValue) {
		return new FindBy(LocatorType.XPATH, locatorValue);
	}

	public static FindBy css(String locatorValue) {
		return new FindBy(LocatorType.CSS, locatorValue);
	}
	
	public static FindBy tag(String locatorValue) {
		return new FindBy(LocatorType.TAG, locatorValue);
	}
}
