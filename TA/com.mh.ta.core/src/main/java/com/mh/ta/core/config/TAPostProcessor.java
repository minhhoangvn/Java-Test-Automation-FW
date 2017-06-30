package com.mh.ta.core.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

public class TAPostProcessor implements BeanPostProcessor {
	private final ApplicationContext ctx;

	public TAPostProcessor(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
		System.err.println("Bean Before Init " + name);

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
		System.err.println("Bean After Init");
		return bean;
	}

}
