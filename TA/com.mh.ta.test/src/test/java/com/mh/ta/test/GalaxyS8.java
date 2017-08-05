package com.mh.ta.test;

public class GalaxyS8 implements Observers {
	@Override
	public void update(Object value) {
		System.err.println("Galaxy S8 get weather service update temp is " + value.toString());

	}
}
