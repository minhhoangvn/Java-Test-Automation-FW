package com.mh.ta.base.selenium;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentTest;
import com.mh.ta.base.selenium.services.SeleniumElementFinder;
import com.mh.ta.base.selenium.webdriver.BrowserDriver;
import com.mh.ta.base.selenium.webdriver.Browsers;
import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.interfaces.driver.IBrowser;
import com.mh.ta.interfaces.driver.IDriver;
import com.mh.ta.interfaces.driver.INavigation;
import com.mh.ta.interfaces.driver.IWebStorage;
import com.mh.ta.interfaces.driver.IWindows;
import com.mh.ta.interfaces.element.TAElement;

public class SeleniumDriver implements IDriver<WebDriver>, IBrowser, IWebStorage, IWindows, INavigation {

	private Object options;
	private DesiredCapabilities capabilities;
	private WebDriver webDriver;
	private SeleniumElementFinder finder = GuiceInjectFactory.instance().getObjectInstance(SeleniumElementFinder.class);

	private Object getOptions() {
		return options;
	}

	private DesiredCapabilities getCapabilities() {
		return capabilities;
	}

	@Override
	public void setDriverOptions(Object options) {
		this.options = options;
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
		return webDriver.getTitle();
	}

	@Override
	public String getPageSource() {
		return webDriver.getPageSource();
	}

	@Override
	public String executeJavaScript(String sourceScript, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return (String) js.executeScript(sourceScript, args);
	}

	@Override
	public void clearAllCookie() {
		Options windows = webDriver.manage();
		windows.deleteAllCookies();
	}

	@Override
	public Cookie getCookieValue(String key) {
		Options windows = webDriver.manage();
		return windows.getCookieNamed(key);
	}

	@Override
	public Set<Cookie> getAllCookieValue() {
		Options windows = webDriver.manage();
		return windows.getCookies();
	}

	@Override
	public void setCookieValue(String key, String value) {
		String domain = this.executeJavaScript("return document.domain");
		Cookie cookie = new Cookie.Builder(key, value).domain(domain).expiresOn(DateUtils.addYears(new Date(), 1))
				.isHttpOnly(true).isSecure(false).build();
		Options windows = webDriver.manage();
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
	public void setImplicitlyWait(int timeOut) {
		Options windows = webDriver.manage();
		windows.timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	@Override
	public void setPageLoadTimeOut(int timeOut) {
		Options windows = webDriver.manage();
		windows.timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}

	@Override
	public String getCurrentWindowsHandle() {
		return webDriver.getWindowHandle();
	}

	@Override
	public Set<String> getListWindowsHandle() {
		return webDriver.getWindowHandles();
	}

	@Override
	public void setWindowsSize(int width, int height) {
		Options windows = webDriver.manage();
		windows.window().setSize(new Dimension(width, height));
	}

	@Override
	public void maximizeWindows() {
		Options windows = webDriver.manage();
		windows.window().maximize();
	}

	@Override
	public void hiddenWindows() {
		Options windows = webDriver.manage();
		windows.window().setPosition(new org.openqa.selenium.Point(0, -2000));
	}

	@Override
	public void navigateToUrl(String url) {
		Navigation navigate = webDriver.navigate();
		url = url.startsWith("http://") || url.startsWith("https://") ? url : "http://" + url;
		navigate.to(url);

	}

	@Override
	public void navigateBack() {
		Navigation navigate = webDriver.navigate();
		navigate.back();
	}

	@Override
	public void navigateForward() {
		Navigation navigate = webDriver.navigate();
		navigate.forward();
	}

	@Override
	public void createDriver(String driverType) {
		Class<BrowserDriver<WebDriver>> browserClass = Browsers.getClassByString(driverType);
		BrowserDriver<WebDriver> driverCreator = GuiceInjectFactory.instance().getObjectInstance(browserClass);
		driverCreator.setCapabilities(this.getCapabilities());
		driverCreator.setDriverOptions(this.getOptions());
		webDriver = driverCreator.createDriver();
	}

	@Override
	public void diposeDriver() {
		if (webDriver != null)
			webDriver.quit();
		webDriver = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getWindowsAction() {
		Actions action = new Actions(webDriver);
		return (T) action;
	}

	@Override
	public WebDriver getCoreDriver() {
		return webDriver;
	}

	@Override
	public TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime) {
		return finder.findElementUntilVisible(by, timeOut, pollingTime);
	}

	public void captureScreenShot(String filePath, ExtentTest... testReport) {
		try {
			File imgFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(imgFile, new File(filePath));
		} catch (IOException e) {
			if (testReport.length > 0) {
				for (ExtentTest extentTest : testReport) {
					extentTest.info("Error in capture screenshot ");
					extentTest.info(e);
				}
			}
		}
	}
}
