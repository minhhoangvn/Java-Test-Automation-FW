package com.mh.ta.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PersonModule extends AbstractModule {
	private String name;

	public PersonModule(String name) {
		this.name = name;
	}

	@Override
	protected void configure() {
		bind(IPerson.class).to(Person.class);
		bind(String.class).annotatedWith(Names.named("Name")).toInstance(this.name);
	}

}
