package com.mh.ta.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Inject;
import com.mh.ta.page.BaseElements;

public class LoginElements extends BaseElements {

	@Inject
	public LoginElements(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String txtSampleForm() {
		return "Test Sample";
	}

	public WebElement txtEmail() {
		return this.driver.findElement(By.id("identifierId"));
	}

	public WebElement btnNext() {
		return this.driver.findElement(By.xpath("//span[text()='Next']"));
	}

	public WebElement txtPassword() {
		return super.findElementUntilVisible(By.name("password"), 30, 500);
	}

	public WebElement btnSignIn() {
		return this.driver.findElement(By.id("signIn"));
	}
}
