package com.mh.ta.person;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ITModule extends AbstractModule{
	@Override
	protected void configure() {
		//bind(ServicesFactory.class).annotatedWith(Names.named("ITTest")).to(IT.class);

	}
	
	@Provides
	@Named("ITServices")
	public ServicesFactory getITServices(){
		return new IT();
	}
}
