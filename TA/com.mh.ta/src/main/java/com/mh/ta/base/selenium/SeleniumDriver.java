package com.mh.ta.base.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mh.ta.core.core.element.Element;
import com.mh.ta.core.core.element.FindBy;
import com.mh.ta.core.drivers.Drivers;

public class SeleniumDriver implements Drivers {
	@Inject
	@Named("SeleniumDriver")
	WebDriver driver;
	@Inject
	@Named("SeleniumFinderService")
	SeleniumFinderServices service;

	@Override
	public Element findElement(FindBy by) {
		return this.service.findElement(driver, by).get(0);
	}

	@Override
	public List<Element> findAllElements(FindBy by) {
		return this.service.findElement(driver, by);
	}

}
