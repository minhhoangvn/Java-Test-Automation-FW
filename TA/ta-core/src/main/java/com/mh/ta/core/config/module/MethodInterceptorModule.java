
package com.mh.ta.core.config.module;

import static com.google.inject.matcher.Matchers.any;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;
import com.mh.ta.core.report.logger.MethodsInterceptor;

/**
 * @author minhhoang
 *
 */
class MethodInterceptorModule extends AbstractModule {
	@Override
	protected void configure() {
		MethodInterceptor interceptor = new MethodsInterceptor();
		requestInjection(interceptor);
		bindInterceptor(any(), any(), interceptor);
	}
}
