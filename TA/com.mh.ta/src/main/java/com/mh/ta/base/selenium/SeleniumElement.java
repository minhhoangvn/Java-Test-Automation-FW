package com.mh.ta.base.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mh.ta.core.core.element.Element;
import com.mh.ta.core.core.element.FindBy;

public class SeleniumElement implements Element {

	protected WebElement element;
	protected WebDriver driver;
	protected SearchContext contex;
	protected SeleniumFinderServices finderService;

	public SeleniumElement(WebElement element, SearchContext context, WebDriver driver) {
		this.element = element;
		this.contex = context;
		this.driver = driver;
		this.finderService = new SeleniumFinderServices(driver);
	}

	@Override
	public Element findElement(FindBy by) {
		return this.finderService.findElement(this.element, by).get(0);
	}

	@Override
	public List<Element> findAllElements(FindBy by) {
		return this.finderService.findElement(this.element, by);
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mosueClick() {
		System.err.println(this.driver.getCurrentUrl());

	}

	@Override
	public String getElementAttribute(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElementAttribute(String attributeName, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getElementText() {
		return this.element.getText();
	}

	@Override
	public void setText(CharSequence... value) {
		this.element.sendKeys(value);
	}

	@Override
	public String getInnerHTMLString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void waitForElementVisible(int timeout, TimeUnit unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void waitForElementPresent(int timeout, TimeUnit unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean isVisible() {
		// TODO Auto-generated method stub
		return null;
	}
}
