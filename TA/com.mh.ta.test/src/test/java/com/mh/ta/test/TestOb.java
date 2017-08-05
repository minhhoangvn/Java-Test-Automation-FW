package com.mh.ta.test;

import org.testng.annotations.Test;

public class TestOb {
	@Test
	public void testPattern() {
		WeatherServices services = new WeatherServices();
		GalaxyS8 s8 = new GalaxyS8();
		Ipad ipad = new Ipad();
		iPhone iPhone = new iPhone();
		services.attachObject(s8);
		services.attachObject(iPhone);
		services.attachObject(ipad);
		services.updateWeatherTemp(36);
		services.updateWeatherTemp(25);
		services.detachObject(s8);
		services.updateWeatherTemp(12);
	}
}
