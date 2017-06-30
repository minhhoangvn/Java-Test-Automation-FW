package com.mh.ta.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mh.ta.core.config.FrameworkSettings;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { AppConfig.class })
public class TestPage {
	@Autowired
	private com.mh.ta.annotation.SamplePageD samplePageD1;


	@Autowired
	private com.mh.ta.home.SamplePageD samplePageD2;
	
	@Autowired
	private FrameworkSettings setting;

	@Test
	public void testB() {
		System.err.println("========= Setting ==============");
		System.err.println(setting.getDriverConfig());
		System.err.println(setting.getSutConfig());
		System.err.println("================================");
		System.err.println("========== Page Object annotation =========");
		samplePageD1.showSetting();
		System.err.println("Print number : " + samplePageD1.getNumber());
		System.err.println("================================");

		System.err.println("========== Page Object home =========");
		samplePageD2.showSetting();
		System.err.println("Print number : " + samplePageD2.getNumber());
		System.err.println("================================");

	}

}
