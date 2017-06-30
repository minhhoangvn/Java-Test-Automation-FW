package com.mh.ta.home;

import org.springframework.beans.factory.annotation.Autowired;

import com.mh.ta.core.annotation.PageObject;
import com.mh.ta.core.config.FrameworkSettings;

@PageObject
public class SamplePageD {
	@Autowired
	private FrameworkSettings setting;

	public void showSetting() {
		System.err.println("========= POM ==============");
		System.err.println(setting.getSutConfig());
		System.err.println("============================");
	}

	public int getNumber() {
		return 69696969;
	}
}
