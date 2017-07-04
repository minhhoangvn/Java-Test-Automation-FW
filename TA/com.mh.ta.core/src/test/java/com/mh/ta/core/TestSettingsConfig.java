package com.mh.ta.core;

import java.io.IOException;

import org.testng.annotations.Test;

import com.mh.ta.test.BaseTestNG;

public class TestSettingsConfig extends BaseTestNG{

	



	@Test
	public void testCanParseYaml() throws IOException {
		System.err.println(this.driverConfig.getBrowser());
		System.err.println(this.sutConfig.getBaseUri());
	}
}
