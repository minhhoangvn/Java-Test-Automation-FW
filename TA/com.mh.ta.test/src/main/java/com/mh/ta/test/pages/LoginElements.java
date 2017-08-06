package com.mh.ta.test.pages;


import java.util.List;

import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.interfaces.element.TAElement;
import com.mh.ta.page.BaseElements;

public class LoginElements extends BaseElements {

	public String txtSampleForm() {
		return "Test Sample";
	}

	public TAElement txtEmail() {
		return this.findElement(FindBy.elementId("identifierId"));
	}

	public TAElement btnNext() {
		return this.findElement(FindBy.xpath("//span[text()='Next']"));
	}

	public TAElement txtPassword() {
		return this.findElementUntilVisible(FindBy.elementName("password"), 5000, 100);
	}

	public TAElement btnSignIn() {
		return this.findElement(FindBy.elementId("signIn"));
	}
	
	public List<TAElement> sampleListElement(){
		return this.findListElement(FindBy.xpath("//*[@jsname='wQNmvb']"));
	}
}
