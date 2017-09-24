
package com.mh.ta.core.config.driver.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.config.driver.services.internal.AnnotationsExtend;
import com.mh.ta.core.config.element.WebElementFactory;
import com.mh.ta.core.config.enums.WaitUntilType;
import com.mh.ta.core.helper.SystemUtility;

/**
 * @author minhhoang
 *
 */
public class ElementLocatorHandlerImpl implements ElementLocator {
	private WebElementFactory elementFactory = GuiceInjectFactory.instance()
			.createObjectInstance(WebElementFactory.class);
	private final boolean shouldCache;
	private final By by;
	private final boolean isFindByUntil;
	private final WaitUntilType waitType;
	private final int timeOut;
	private WebElement cachedElement;
	private List<WebElement> cachedElementList;

	/**
	 * Creates a new element locator.
	 *
	 * @param searchContext
	 *            The context to use when finding the element
	 * @param field
	 *            The field on the Page Object that will hold the located value
	 */
	public ElementLocatorHandlerImpl(Field field) {
		this(new AnnotationsExtend(field));
	}

	/**
	 * Use this constructor in order to process custom annotaions.
	 *
	 * @param searchContext
	 *            The context to use when finding the element
	 * @param annotations
	 *            AbstractAnnotations class implementation
	 */
	public ElementLocatorHandlerImpl(AbstractAnnotations annotations) {
		this.shouldCache = annotations.isLookupCached();
		this.by = annotations.buildBy();
		this.isFindByUntil = ((AnnotationsExtend) annotations).isFindByUntil();
		this.waitType = ((AnnotationsExtend) annotations).getWaitType();
		this.timeOut = ((AnnotationsExtend) annotations).getTimeOut();
	}

	/**
	 * Find the element.
	 */
	public WebElement findElement() {
		if (cachedElement != null && shouldCache) {
			return cachedElement;
		}
		runWaitWithFindByUntil();
		WebElement element = DriverFactory.instance().getDriver().findElement(by);
		if (shouldCache) {
			cachedElement = element;
		}

		return elementFactory.createElement(element);
	}

	/**
	 * Find the element list.
	 */
	public List<WebElement> findElements() {
		if (cachedElementList != null && shouldCache) {
			return cachedElementList;
		}
		runWaitWithFindByUntil();
		List<WebElement> elements = DriverFactory.instance().getDriver().findElements(by);
		if (shouldCache) {
			cachedElementList = elements;
		}
		return elements.stream().map(e -> elementFactory.createElement(e)).collect(Collectors.toList());
	}

	private void runWaitWithFindByUntil() {
		if (isFindByUntil)
			waitUntil();
	}

	private void waitUntil() {
		Driver driver = DriverFactory.instance().getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		switch (waitType) {
		case CLICKABLE:
			wait.until(ExpectedConditions.elementToBeClickable(by));
			break;
		case INVISIBLE:
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			break;
		case PRESENT:
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			break;
		case SELECTED:
			wait.until(ExpectedConditions.elementToBeSelected(by));
			break;
		case VISIBLE:
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			break;
		case SLEEP:
			SystemUtility.sleep(timeOut);
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " '" + by + "'";
	}
}
