package com.mh.ta.page;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasePage {
	@Autowired
	WebDriver webDriver;

	public BasePage() {

	}
}
