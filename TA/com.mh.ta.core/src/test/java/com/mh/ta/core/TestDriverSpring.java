package com.mh.ta.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mh.ta.core.config.BrowserManagerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BrowserManagerConfig.class })
public class TestDriverSpring {
	@Autowired
	private WebDriver driver=null;


	@Test
	public void testInstaceDriverSpring() {
		assertNotNull(this.driver);
		driver.get("http://www.google.com");
		driver.get("http://dantri.com.vn");
		driver.quit();
	}
}
