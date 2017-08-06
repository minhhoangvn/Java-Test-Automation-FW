package com.mh.ta.test;

import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

public class TestProvider {
	@Test
	public void testProvider() {
		Injector inject = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder binder) {
				binder.install(new FactoryModuleBuilder().build(PersonFactory.class));

			}

		});
		PersonFactory factory = inject.getInstance(PersonFactory.class);
		Person p1 = factory.craeate(new Information("Test 1 "), "Minh");
		Person p2 = factory.craeate(new Information("Test"), "Minh Hoang");
		
		System.err.println(p1.toString());
		System.err.println(p2.toString());
	}
}
