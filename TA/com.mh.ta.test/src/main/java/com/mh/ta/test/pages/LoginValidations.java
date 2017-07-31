package com.mh.ta.test.pages;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import com.mh.ta.page.BaseValidations;

public class LoginValidations extends BaseValidations<LoginElements> {
	
	@Inject
	public LoginValidations(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public Boolean shouldShowPassowdField() {
		return true;
	}
	
	public String shouldReturnSample(){
		return this.Elements().txtSampleForm()+ " in validation class";
	}
}
