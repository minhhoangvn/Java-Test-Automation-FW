package com.mh.ta.factory;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceInjectFactory {
	private Injector inject = Guice.createInjector();
	private static ThreadLocal<GuiceInjectFactory> instance = new ThreadLocal<GuiceInjectFactory>();
	private static List<Module> listInjectModules = new ArrayList<Module>();
	private GuiceInjectFactory() {

	}

	public static GuiceInjectFactory instance() {
		if (instance.get() == null)
			synchronized (GuiceInjectFactory.class) {
				if (instance.get() == null) {
					instance.set(new GuiceInjectFactory());
					instance.get().inject = Guice.createInjector();
					listInjectModules.forEach(m->{
						Injector inject = instance.get().inject;
						inject = inject.createChildInjector(m);
						instance.get().inject = inject;
					});
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
		listInjectModules.add(module);
	}

	public <T> T getObjectInstance(Class<T> cls) {
		return instance.get().inject.getInstance(cls);
	}
}
