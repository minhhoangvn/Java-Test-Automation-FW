package com.mh.ta.login;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.page.BasePage;

public class LoginPage extends BasePage<LoginElements, LoginValidations> {
	
	@Inject
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage goToLoginPage() {
		this.driver.get("https://accounts.google.com/signin/v2");
		return this;
	}

	public LoginPage inputEmail() {
		this.Elements().txtEmail().sendKeys("minhquanvn2171990");
		return this;
	}

	public LoginPage clickNext() {
		this.Elements().btnNext().click();
		return this;
	}

	public LoginPage inputPassword() {
		this.Elements().txtPassword().sendKeys("test");
		return this;
	}

	public String testElementPage() {
		return this.Elements().txtSampleForm();
	}
}
