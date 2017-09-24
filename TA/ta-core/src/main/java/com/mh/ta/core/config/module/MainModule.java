
package com.mh.ta.core.config.module;

import com.google.inject.AbstractModule;

/**
 * @author minhhoang
 *
 */
public class MainModule extends AbstractModule {
	String configFileName = "application.yaml";

	public MainModule() {
	}

	public MainModule(String configFileName) {
		this.configFileName = configFileName;
	}

	@Override
	protected void configure() {
		install(new MainConfigModule(configFileName));	
		install(new WebDriverFactoryModule());
		install(new WebElementFactoryModule());
		install(new MethodInterceptorModule());
	}

}
