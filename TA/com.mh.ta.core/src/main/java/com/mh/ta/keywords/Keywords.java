package com.mh.ta.keywords;

public abstract class Keywords<DriverType, WebElementType> {
	protected DriverType driver;

	public Keywords(DriverType type) {
		this.driver = type;
	}
	
	public abstract void printTitle();
	public abstract void moveMosueClick(WebElementType element);
}
