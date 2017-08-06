package com.mh.ta.base.appium;

import java.util.List;

import com.mh.ta.base.selenium.webelement.FindBy;
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
