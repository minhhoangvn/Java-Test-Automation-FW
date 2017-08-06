package com.mh.ta.factory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceInjectFactory {
	private Injector inject = Guice.createInjector();
	private static ThreadLocal<GuiceInjectFactory> instance = new ThreadLocal<GuiceInjectFactory>();

	private GuiceInjectFactory() {

	}

	public static GuiceInjectFactory instance() {
		if (instance.get() == null)
			synchronized (GuiceInjectFactory.class) {
				if (instance.get() == null) {
					instance.set(new GuiceInjectFactory());
					instance.get().inject = Guice.createInjector();
				}
			}

		return instance.get();
	}

	public Injector getInject() {
		return instance.get().inject;
	}

	public void injectToClass(Object object) {
		instance.get().inject.injectMembers(object);
	}

	public void createInject(Module module) {
		Injector inject = instance.get().inject;
		inject = inject.createChildInjector(module);
		instance.get().inject = inject;

	}

	public <T> T getObjectInstance(Class<T> cls) {
		return instance.get().inject.getInstance(cls);
	}
}
