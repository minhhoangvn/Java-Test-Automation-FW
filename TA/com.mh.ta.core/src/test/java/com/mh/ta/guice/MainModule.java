package com.mh.ta.guice;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule{

	@Override
	protected void configure() {
		install(new ContactModule());
		bind(ContactRegister.class);
	}

}
