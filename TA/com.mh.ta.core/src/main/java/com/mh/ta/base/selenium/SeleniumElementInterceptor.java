package com.mh.ta.base.selenium;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.mh.ta.base.selenium.SeleniumElement;
import com.mh.ta.core.annotation.HighLightElement;

public class SeleniumElementInterceptor implements MethodInterceptor {
	private final String webElementClassName = "SeleniumElement";

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.err.println("======== Element Interceptor ============");
		System.err.println(invocation.getMethod().getName());
		System.err.println("======== Element Interceptor ============");
		return invocation.proceed();

	}
}
