package com.mh.ta.test;

public class Ipad implements Observers {
	
	@Override
	public void update(Object value) {
		System.err.println("Ipad get weather service update temp is " + value.toString());

	}

}
