package com.mh.ta.support;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.support.report.ExtentReportManager;

public class TestReportListener implements ITestListener {
	private static ExtentReports extent = ExtentReportManager.createInstance("extent.html");
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		try {
			System.err.println("======================");
			System.err.println("Test start");
			System.err.println("======================");
			Injector injector = GuiceInjectFactory.instance().getInject();
			MainConfig config = injector.getInstance(Key.get(MainConfig.class, ApplicationConfig.class));
			System.err.println(config.getReportConfig().getReportFolder());
		} catch (Exception e) {
			throw new TestContextException(e);
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		System.err.println("On start " + Thread.currentThread().getId());
		System.err.println(context.getSuite().getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
