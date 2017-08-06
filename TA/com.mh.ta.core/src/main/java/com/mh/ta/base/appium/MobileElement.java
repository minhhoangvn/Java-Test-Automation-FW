package com.mh.ta.base.appium;

import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.interfaces.element.TAElement;

public class MobileElement implements TAElement {

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
	public void inputTextToElement(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInputValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectByText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectByIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getListSelectOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TAElement getSelectedElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deselectAllOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectAllOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClickElement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementInnerText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isElementDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setElementAttribute(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isElementEnable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void highlightElement(String borderColor, String backgroundColor, int timeOut) {
		// TODO Auto-generated method stub
		
	}
	
}
