package com.mh.ta.base.appium;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.interfaces.element.TAElement;

class MobileElementFinder {
}
