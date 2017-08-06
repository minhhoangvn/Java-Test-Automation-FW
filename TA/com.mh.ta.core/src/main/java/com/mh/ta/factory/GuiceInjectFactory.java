package com.mh.ta.factory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceInjectFactory {
	private static Injector inject = Guice.createInjector();
	private static GuiceInjectFactory insatnce;

	private GuiceInjectFactory() {

	}

	public static GuiceInjectFactory instance() {
		if (insatnce == null)
			synchronized (GuiceInjectFactory.class) {
				if (insatnce == null)
					insatnce = new GuiceInjectFactory();
			}
		return insatnce;
	}

	public Injector getInject() {
		return inject;
	}

	public void injectToClass(Object object) {
		inject.injectMembers(object);
	}

	public void createInject(Module module) {
		inject = inject.createChildInjector(module);
	}

	public <T> T getObjectInstance(Class<T> cls) {
		return inject.getInstance(cls);
	}
}
