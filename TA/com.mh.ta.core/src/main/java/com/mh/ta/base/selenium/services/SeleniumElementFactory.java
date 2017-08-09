package com.mh.ta.base.selenium.services;

import org.openqa.selenium.WebElement;

import com.mh.ta.base.selenium.SeleniumElement;

public interface SeleniumElementFactory {
	SeleniumElement create(WebElement element);
}
