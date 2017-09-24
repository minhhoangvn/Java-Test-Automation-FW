
package com.mh.ta.core.config.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.mh.ta.core.config.element.WebElementFactory;

/**
 * @author minhhoang
 *
 */
class WebElementFactoryModule implements Module{
	@Override
	public void configure(Binder binder) {
		binder.install(new FactoryModuleBuilder().build(WebElementFactory.class));
	}
}
