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
	private SamplePageA samplePageA;

	@Autowired
	private SamplePageB samplePageB;

	@Autowired
	private FrameworkSettings setting;

	@Test
	public void testB() {
		System.err.println("========= Setting ==============");
		System.err.println(setting.getDriverConfig());
		System.err.println(setting.getSutConfig());
		System.err.println("================================");
		System.err.println("========== Page Object A=========");
		samplePageA.showSetting();
		System.err.println("Print number : " + samplePageA.getNumber());
		System.err.println("================================");

		System.err.println("========== Page Object B=========");
		samplePageB.showSetting();
		System.err.println("Print number : " + samplePageB.getNumber());
		System.err.println("================================");

	}

}
