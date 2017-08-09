package com.mh.ta.base.selenium.services;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.mh.ta.base.selenium.SeleniumElement;
import com.mh.ta.core.annotation.HighLightElement;

public class KeywordInterceptor implements MethodInterceptor {
	private final String webElementClassName = "SeleniumElement";

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
			HighLightElement highlight = invocation.getMethod().getAnnotation(HighLightElement.class);
			for (Object arg : invocation.getArguments()) {
				if (arg.getClass().getSimpleName().contains(webElementClassName)) {
					SeleniumElement element = (SeleniumElement) arg;
					element.highlightElement(highlight.borderColor(), highlight.backgroundColor(),
							highlight.timeoutInMs());
				}
			}
			return invocation.proceed();
	}
}
