package com.mh.ta.login;

import com.mh.ta.page.BaseValidations;

public class LoginValidations extends BaseValidations<LoginElements> {

	public LoginValidations(Class<LoginElements> element) {
		super(element);
		// TODO Auto-generated constructor stub
	}

	public Boolean shouldShowPassowdField() {
		return true;
	}
}
