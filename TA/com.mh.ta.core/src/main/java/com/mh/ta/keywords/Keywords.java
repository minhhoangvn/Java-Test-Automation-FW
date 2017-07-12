package com.mh.ta.keywords;

public abstract class Keywords<DriverType, WebElementType> {
	public DriverType driver;

	public Keywords(DriverType type) {
		this.driver = type;
	}

	public abstract void moveMosueClick(WebElementType element);
}
