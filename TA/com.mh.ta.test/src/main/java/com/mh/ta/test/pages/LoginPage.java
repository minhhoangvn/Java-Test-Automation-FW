package com.mh.ta.test.pages;

import com.mh.ta.interfaces.element.TAElement;
import com.mh.ta.page.BasePage;

public class LoginPage extends BasePage<LoginElements, LoginValidations> {

	public LoginPage goToLoginPage() {
		
		this.driver.get().navigateToUrl("https://accounts.google.com/signin/v2");
		return this;
	}

	public LoginPage inputEmail() {
		this.keywords.intputTextToElement(this.Elements().txtEmail(), "minhquanvn2171990");
		return this;
	}

	public LoginPage clickNext() {
		this.keywords.clickToElement(this.Elements().btnNext());
		return this;
	}

	public LoginPage inputPassword() {
		this.keywords.intputTextToElement(this.Elements().txtPassword(), "minhquanvn2171990");
		return this;
	}

	public String testElementPage() {
		return this.Elements().txtSampleForm();
	}
	
	public void printInnerTextListElement(){
		for(TAElement element : this.Elements().sampleListElement()){
			System.err.println(element.getElementInnerText());
		}
	}
}
