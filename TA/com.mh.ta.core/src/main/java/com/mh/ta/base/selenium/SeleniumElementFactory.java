package com.mh.ta.base.selenium;

import org.openqa.selenium.WebElement;

interface SeleniumElementFactory {
	SeleniumElement create(WebElement element);
}
