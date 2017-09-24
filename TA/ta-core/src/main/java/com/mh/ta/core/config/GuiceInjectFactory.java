
package com.mh.ta.core.config;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.mh.ta.core.helper.Constant;

/**
 * create Thread-safe for GuiceInjectFactory for running test with multiple
 * application configuration file with difference value
 * 
 * @author minhhoang
 *
 */
public class GuiceInjectFactory {
	private Injector inject;
	private static ThreadLocal<GuiceInjectFactory> instance = new ThreadLocal<>();
	private static Map<String, Module> listInjectedModules = new HashMap<>();

	private GuiceInjectFactory() {
		inject = Guice.createInjector();
	}

	public static GuiceInjectFactory instance() {
		if (instance.get() == null)
			synchronized (GuiceInjectFactory.class) {
				if (instance.get() == null) {
					instance.set(new GuiceInjectFactory());					
					instance.get().reInjectModules();
				}
			}

		return instance.get();
	}

	public Injector getInject() {
		return instance.get().inject;
	}

	public void injectToClass(Object object) {
		Injector injectInstance = instance.get().inject;
		injectInstance.injectMembers(object);
		instance.get().inject = injectInstance;
	}

	public void createInject(Module module) {
		Injector injectInstance = instance.get().inject;
		injectInstance = injectInstance.createChildInjector(module);
		instance.get().inject = injectInstance;
		storeInjectModule(module);
	}

	/**
	 * Return null if object not instance in Container
	 * 
	 * @param cls
	 * @return
	 */
	public <T> T getInstanceObjectInject(Class<T> cls, Class<? extends Annotation> annotation) {
		try {
			Injector injectInstance = instance.get().inject;
			return injectInstance.getInstance(Key.get(cls, annotation));
		} catch (ConfigurationException e) {
			return null;
		}
	}

	public <T> T createObjectInstance(Class<T> cls) {
		Injector injectInstance = instance.get().inject;
		return injectInstance.getInstance(cls);
	}

	private void storeInjectModule(Module module) {
		String moduleName = module.getClass().getSimpleName();
		String mainConfigModuleName = Constant.MAIN_CONFIG_MODULE_NAME;
		boolean isMainConfigModule = moduleName.equals(mainConfigModuleName);
		boolean isAlreadyStore = listInjectedModules.containsKey(moduleName);
		boolean isCachedModule = !(isMainConfigModule || isAlreadyStore);
		if (isCachedModule)
			listInjectedModules.put(module.getClass().getSimpleName(), module);
	}

	private void reInjectModules() {
		listInjectedModules.forEach((k, v) -> {
			Injector reInject = instance.get().inject;
			reInject = reInject.createChildInjector(v);
			instance.get().inject = reInject;
		});
	}
}
