
package com.mh.ta.core.config.driver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.aventstack.extentreports.ExtentTest;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.mh.ta.core.config.LoggerFactory;

/**
 * @author minhhoang
 *
 */
public class Driver implements WebDriver {
	Logger log = LoggerFactory.instance().createClassLogger(getClass());
	private WebDriver webDriver;

	@Inject
	public Driver(@Assisted WebDriver driver) {
		this.webDriver = driver;
	}

	@Override
	public void close() {
		webDriver.close();
	}

	@Override
	public void get(String url) {
		webDriver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return webDriver.getPageSource();
	}

	@Override
	public String getTitle() {
		return webDriver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return webDriver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return webDriver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return webDriver.manage();
	}

	@Override
	public Navigation navigate() {
		return webDriver.navigate();
	}

	@Override
	public void quit() {
		webDriver.quit();
		webDriver = null;
	}

	@Override
	public TargetLocator switchTo() {
		return webDriver.switchTo();
	}

	public void createScreenShot(String filePath, ExtentTest... testReport) {
		try {
			File imgFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(imgFile, new File(filePath));
		} catch (IOException e) {
			log.error("Has error in capture screen shot ", e);
			if (testReport.length > 0) {
				for (int testReportIndex = 0; testReportIndex < testReport.length; testReportIndex++) {
					testReport[testReportIndex].error("Error in capture screenshot ");
					testReport[testReportIndex].error(e);
				}
			}
		}
	}

	public byte[] createScreenShot() {
		return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);		
	}
	
	public String createScreenShotAsString() {
		return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);		
	}
	
	public String executeJavaScript(String sourceScript, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return (String) js.executeScript(sourceScript, args);
	}

	@Override
	public WebElement findElement(By by) {
		return webDriver.findElement(by);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return webDriver.findElements(by);
	}

	public SessionId getSessionId() {
		return ((RemoteWebDriver) webDriver).getSessionId();
	}

	public Actions createWindowAction() {
		return new Actions(webDriver);
	}
	
}
