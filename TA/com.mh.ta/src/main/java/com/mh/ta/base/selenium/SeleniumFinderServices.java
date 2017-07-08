package com.mh.ta.base.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.name.Named;
import com.mh.ta.core.core.element.Element;
import com.mh.ta.core.core.element.FindBy;

public class SeleniumFinderServices {

	private WebDriver driver;

	public SeleniumFinderServices(@Named("SeleniumDriver") WebDriver driver) {
		this.driver = driver;
	}

	private org.openqa.selenium.By convertToSeleniumBy(FindBy by) {
		switch (by.getType()) {
		case ID:
			return By.id(by.getLocatorValue());
		case NAME:
			return By.name(by.getLocatorValue());
		case CLASS:
			return By.className(by.getLocatorValue());
		case CSS:
			return By.cssSelector(by.getLocatorValue());
		case XPATH:
			return By.xpath(by.getLocatorValue());
		case TAG:
			return By.tagName(by.getLocatorValue());
		default:
			throw new RuntimeException();
		}
	}

	public List<Element> findElement(SearchContext contex, FindBy by) {
		try {
			List<WebElement> listElement = contex.findElements(this.convertToSeleniumBy(by));
			List<Element> TAElement = new ArrayList<Element>();
			listElement.forEach(element -> TAElement.add(new SeleniumElement(element, contex, driver)));
			return TAElement;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
