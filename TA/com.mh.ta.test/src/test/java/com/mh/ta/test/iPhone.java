package com.mh.ta.test;

public class iPhone implements Observers {
	@Override
	public void update(Object value) {
		System.err.println("Iphone get weather service update temp is " + value.toString());

	}
}
