package com.mh.ta.test.pages;

import com.mh.ta.page.BaseValidations;

public class LoginValidations extends BaseValidations<LoginElements> {

	public Boolean shouldShowPassowdField() {
		return true;
	}
}
