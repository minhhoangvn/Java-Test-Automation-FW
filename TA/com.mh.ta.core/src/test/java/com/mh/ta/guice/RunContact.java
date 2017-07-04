package com.mh.ta.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class RunContact {

	public static void main(String[] args) {
		final Injector inject = Guice.createInjector(new MainModule());
		final ContactRegister c = inject.getInstance(ContactRegister.class);
		System.out.println(c.getSettings().getDriverConfig().getBrowser());
		System.out.println(c.getContact().getName());
	}

}
