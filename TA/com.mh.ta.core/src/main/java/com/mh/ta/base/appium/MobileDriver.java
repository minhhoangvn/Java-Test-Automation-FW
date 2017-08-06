package com.mh.ta.base.appium;

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
import com.mh.ta.interfaces.driver.IDriver;
import com.mh.ta.interfaces.element.TAElement;

import io.appium.java_client.AppiumDriver;

@SuppressWarnings("rawtypes")
public class MobileDriver implements IDriver<AppiumDriver> {

	@Override
	public TAElement findElement(FindBy by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TAElement> findListElement(FindBy by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String executeJavaScript(String sourceScript, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAllCookie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cookie getCookieValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cookie> getAllCookieValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCookieValue(String key, String value) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getListWindowsHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWindowsSize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maximizeWindows() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hiddenWindows() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateToUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createDriver(String driverType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void diposeDriver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDriverOptions(Object options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCapabilities(Object capabilities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T getWindowsAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppiumDriver getCoreDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
