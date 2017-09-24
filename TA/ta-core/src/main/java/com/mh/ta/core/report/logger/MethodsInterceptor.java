
package com.mh.ta.core.report.logger;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.Logger;

import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.report.ReportInformation;

/**
 * @author minhhoang
 *
 */
public class MethodsInterceptor implements MethodInterceptor {
	Logger log = LoggerFactory.instance().createClassLogger(getClass());

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info(Thread.currentThread().getContextClassLoader() + " " + Thread.currentThread().getId()
				+ " Method running " + invocation.getMethod().getName() + " with Paramemter input "
				+ Arrays.toString(invocation.getArguments()));
		ReportInformation.appendTestInformation("Method running " + invocation.getMethod().getName()
				+ " with Paramemter input " + Arrays.toString(invocation.getArguments()));
		return invocation.proceed();
	}

}
