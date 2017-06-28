package com.mh.ta.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import com.mh.ta.core.annotation.PageObject;
import com.mh.ta.core.config.FrameworkSettings;

@PageObject
public class SamplePageB {
	@Autowired
	private FrameworkSettings setting;

	public void showSetting() {
		System.err.println("========= POM ==============");
		System.err.println(setting.getSutConfig());
		System.err.println("============================");
	}

	public int getNumber() {
		return 100000;
	}
}
