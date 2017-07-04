package com.mh.ta.person;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class OfficeModule extends AbstractModule{
	@Override
	protected void configure() {
		//bind(ServicesFactory.class).annotatedWith(Names.named("OfficeTest")).to(Office.class);

	}
	
	@Provides
	@Named("OfficeServices")
	public ServicesFactory getITServices(){
		return new Office();
	}
}
