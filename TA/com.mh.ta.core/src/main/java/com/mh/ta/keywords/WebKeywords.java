package com.mh.ta.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Inject;

public class WebKeywords extends Keywords<WebDriver, WebElement> {

	@Inject
	public WebKeywords(WebDriver type) {
		super(type);
	}

	public void printTitle() {
		System.err.println(this.driver.getTitle());
	}

	@Override
	public void moveMosueClick(WebElement element) {
		// TODO Auto-generated method stub
		
	}
}
