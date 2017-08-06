package com.mh.ta.base.selenium;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mh.ta.base.selenium.webdriver.BrowserDriver;
import com.mh.ta.base.selenium.webdriver.Browsers;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.interfaces.driver.Driver;
import com.mh.ta.interfaces.element.TAElement;

public class SeleniumDriver implements Driver<WebDriver> {

	private ChromeOptions options;
	private DesiredCapabilities capabilities;
	private static ThreadLocal<WebDriver> driverStorage = new ThreadLocal<WebDriver>();
	private WebElementFinder finder = GuiceInjectFactory.instance().getObjectInstance(WebElementFinder.class);

	private ChromeOptions getOptions() {
		return options;
	}

	private DesiredCapabilities getCapabilities() {
		return capabilities;
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = (ChromeOptions) options;
	}

	@Override
	public void setCapabilities(Object capabilities) {
		this.capabilities = (DesiredCapabilities) capabilities;
	}

	@Override
	public TAElement findElement(FindBy by) {
		return finder.findElement(by);
	}

	@Override
	public List<TAElement> findListElement(FindBy by) {
		return finder.findListElement(by);
	}

	@Override
	public String getPageTitle() {
		return driverStorage.get().getTitle();
	}

	@Override
	public String getPageSource() {
		return driverStorage.get().getPageSource();
	}

	@Override
	public String executeJavaScript(String sourceScript, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) driverStorage.get();
		return (String) js.executeScript(sourceScript, args);
	}

	@Override
	public void clearAllCookie() {
		Options windows = driverStorage.get().manage();
		windows.deleteAllCookies();
	}

	@Override
	public Cookie getCookieValue(String key) {
		Options windows = driverStorage.get().manage();
		return windows.getCookieNamed(key);
	}

	@Override
	public Set<Cookie> getAllCookieValue() {
		Options windows = driverStorage.get().manage();
		return windows.getCookies();
	}

	@Override
	public void setCookieValue(String key, String value) {
		String domain = this.executeJavaScript("return document.domain");
		Cookie cookie = new Cookie.Builder(key, value).domain(domain).expiresOn(DateUtils.addYears(new Date(), 1))
				.isHttpOnly(true).isSecure(false).build();
		Options windows = driverStorage.get().manage();
		windows.addCookie(cookie);

	}

	@Override
	public String getLocalStorage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocalStorage(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCurrentWindowsHandle() {
		return driverStorage.get().getWindowHandle();
	}

	@Override
	public Set<String> getListWindowsHandle() {
		return driverStorage.get().getWindowHandles();
	}

	@Override
	public void setWindowsSize(int width, int height) {
		Options windows = driverStorage.get().manage();
		windows.window().setSize(new Dimension(width, height));
	}

	@Override
	public void maximizeWindows() {
		Options windows = driverStorage.get().manage();
		windows.window().maximize();
	}

	@Override
	public void hiddenWindows() {
		Options windows = driverStorage.get().manage();
		windows.window().setPosition(new org.openqa.selenium.Point(0, -2000));
	}

	@Override
	public void navigateToUrl(String url) {
		Navigation navigate = driverStorage.get().navigate();
		navigate.to(url);

	}

	@Override
	public void navigateBack() {
		Navigation navigate = driverStorage.get().navigate();
		navigate.back();
	}

	@Override
	public void navigateForward() {
		Navigation navigate = driverStorage.get().navigate();
		navigate.forward();
	}

	@Override
	public void createDriver(String driverType) {
		switch (Browsers.getTypeByString(driverType)) {
		case REMOTE:
			this.createRemoteDriver();
			break;
		default:
			this.createLocalDriver(driverType);
			break;
		}
	}

	@Override
	public void diposeDriver() {
		if (driverStorage.get() != null)
			driverStorage.get().quit();
		driverStorage.remove();
	}

	private void createLocalDriver(String driverType) {
		Class<BrowserDriver<WebDriver>> browserClass = Browsers.getClassByString(driverType);
		BrowserDriver<WebDriver> driverCreator = GuiceInjectFactory.instance().getObjectInstance(browserClass);
		driverCreator.setCapabilities(this.getCapabilities());
		driverCreator.setDriverOptions(this.getOptions());
		WebDriver webDriver = driverCreator.createDriver();
		driverStorage.set(webDriver);
	}

	private void createRemoteDriver() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getWindowsAction() {
		Actions action = new Actions(driverStorage.get());
		return (T) action;
	}

	@Override
	public WebDriver getDriver() {
		return driverStorage.get();
	}

	@Override
	public TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime) {
		return finder.findElementUntilVisible(by, timeOut, pollingTime);
	}
}
