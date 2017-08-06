package com.mh.ta.test;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class Person {
	Information info;
	String name;

	@Inject
	public Person(@Assisted Information info, @Assisted String name) {
		this.info = info;
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name +  " " + this.info.info;
	}
}
