package com.mh.ta.person;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {
		Person m = new Person("Minh Hoang", "90 D1", 27, false);
		Person c = new Person("Minh Quan", "274 NVG", 27, false);
		bind(Person.class).annotatedWith(Names.named("MinhHoang")).toInstance(m);
		bind(Person.class).annotatedWith(Names.named("MinhQuan")).toInstance(c);
		bind(ServicesFactory.class).annotatedWith(Names.named("ITServices")).to(IT.class);
		bind(ServicesFactory.class).annotatedWith(Names.named("OfficeServices")).to(Office.class);

	}
/*
	@Provides
	@ITServices
	IServices getITServices() {
		System.err.println("Init IT object ");
		return new IT();

	}
	
	@Provides
	@OfficeServices
	IServices getOfficeServices() {
		System.err.println("Init Office object ");
		return new Office();
	}
*/
}
