
package com.mh.ta.core.report.logger;

import org.apache.logging.log4j.Logger;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.mh.ta.core.config.LoggerFactory;

/**
 * @author minhhoang
 *
 */
public class LoggerListener implements ITestListener {
	Logger log = LoggerFactory.instance().createClassLogger(getClass());

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		logInfoWithThreadId(" Start run test: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// not using this listener method
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// not using this listener method

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// not using this listener method

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not using this listener method

	}

	@Override
	public void onStart(ITestContext context) {
		ITestNGMethod[] listTestMethod = context.getAllTestMethods();
		ISuite suite = context.getSuite();
		String suiteName = suite.getName();
		log.info("Thread " + Thread.currentThread().getId() + " Start run suite: " + suiteName);
		for (ITestNGMethod iTestNGMethod : listTestMethod) {
			logInfoWithThreadId(" Suite contains test: " + iTestNGMethod);
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		logIResultMap(context.getPassedConfigurations());
		logIResultMap(context.getFailedConfigurations());
		logIResultMap(context.getSkippedConfigurations());
		logIResultMap(context.getPassedTests());
		logIResultMap(context.getFailedTests());
		logIResultMap(context.getSkippedTests());

	}

	private void logIResultMap(IResultMap map) {
		for (ITestResult rs : map.getAllResults()) {
			logInfoWithThreadId(" Suite result staus: " + rs);
		}
	}

	private void logInfoWithThreadId(String message) {
		log.info("Thread " + Thread.currentThread().getId() + message);
	}
}
