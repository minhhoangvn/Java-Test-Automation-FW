package com.mh.ta.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mh.ta.page.BaseElements;

public class LoginElements extends BaseElements {

	public String txtSampleForm() {
		return "Test Sample";
	}

	public WebElement txtEmail() {
		return this.findElement(By.id("identifierId"));
	}

	public WebElement btnNext() {
		return this.findElement(By.xpath("//span[text()='Next']"));
	}

	public WebElement txtPassword() {
		return this.findElementUntilVisible(By.name("password"), 30, 500);
	}

	public WebElement btnSignIn() {
		return this.findElement(By.id("signIn"));
	}
}
