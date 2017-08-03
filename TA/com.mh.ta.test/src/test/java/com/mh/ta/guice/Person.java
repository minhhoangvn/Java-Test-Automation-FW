package com.mh.ta.guice;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Person implements IPerson {
	@Inject 
	@Named("Name")
	private String name;
	@Inject
	Information info;
	
	private String info() {
		return this.name + " " + this.info.getAge() + " " + this.info.getAddress();
	}

	@Override
	public void eat() {
		System.err.println(this.info() + " eat");
	}

	@Override
	public void walk() {
		System.err.println(this.info() + " walk");

	}

	@Override
	public void drink() {
		System.err.println(this.info() + " drink");

	}

	@Override
	public void study() {
		System.err.println(this.info() + " study");

	}

}
