package com.mh.ta.base.selenium.services;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class SeleniumElementModule implements Module{
	@Override
	public void configure(Binder binder) {
		binder.install(new FactoryModuleBuilder().build(SeleniumElementFactory.class));
	}
}
