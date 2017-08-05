package com.mh.ta.keywords;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.mh.ta.core.annotation.HighLightElement;

public class KeywordModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(WebKeywords.class);
		MethodInterceptor interceptor = new KeywordInterceptor();
		requestInjection(interceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(HighLightElement.class), interceptor);
	}

}
