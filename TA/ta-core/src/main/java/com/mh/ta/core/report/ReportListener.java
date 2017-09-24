
package com.mh.ta.core.report;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mh.ta.core.config.annotation.EnableOneTimeConfig;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.core.helper.DateTimeUtility;
import com.mh.ta.core.report.extent.ReportExtend;
import com.mh.ta.core.report.notification.MailManager;
import com.mh.ta.core.report.video.IVideoRecord;

/**
 * @author minhhoang
 *
 */
public class ReportListener implements ITestListener {
	private static ExtentReports extent = ReportExtend
			.createInstance(Constant.REPORT_PATH + File.separator + "TestReport.html");
	private ExtentTest suites;
	private static ThreadLocal<IVideoRecord> recordStorage = new ThreadLocal<>();

	public ReportListener() {
		ReportHelper.initReportFolder();
		if (suites == null)
			suites = extent.createTest("Test Suites");
	}

	@Override
	public synchronized void onStart(ITestContext ctx) {
		ReportHelper.appendSuiteReportInformation(ctx, suites);

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		ReportExtend.createReport();
		MailManager.sendMail();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		EnableOneTimeConfig oneTimeConfig = ReportHelper.getAnnotationTestClass(result, EnableOneTimeConfig.class);
		boolean isOneTimeConfigGUI = oneTimeConfig != null && !oneTimeConfig.enableAPITest();
		if (oneTimeConfig == null || isOneTimeConfigGUI) {
			ReportHelper.startRecordTest(result, suites);
		}

		ReportInformation.appendTestInformation("Start Test: " + result.getName());
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		ReportInformation.appendEmailInformation(result);
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		ReportInformation.appendTestReportInformation(result, testResult);
		ReportHelper.createTestDetailInformation(testResult);
		testResult.pass(String.format(Constant.TEST_INFO_FORMAT, "green", "TEST PASSED"));
		ReportHelper.stopRecord(result, testResult);
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		ReportInformation.appendEmailInformation(result);
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		ReportInformation.appendTestReportInformation(result, testResult);
		ReportHelper.createTestDetailInformation(testResult);
		testResult.fail(String.format(Constant.TEST_INFO_FORMAT, "red", result.getThrowable()));
		captureScreenShotIfFailedOnGUI(result, testResult);
		ReportHelper.stopRecord(result, testResult);
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		ReportInformation.appendEmailInformation(result);
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		ReportInformation.appendTestReportInformation(result, testResult);
		ReportHelper.createTestDetailInformation(testResult);
		testResult.skip(String.format(Constant.TEST_INFO_FORMAT, "red", result.getThrowable()));
		boolean isStartRecord = recordStorage.get() != null;
		if (isStartRecord)
			ReportHelper.stopRecord(result, testResult);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ReportInformation.appendEmailInformation(result);
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		ReportInformation.appendTestReportInformation(result, testResult);
		ReportHelper.createTestDetailInformation(testResult);
		testResult.fail(String.format(Constant.TEST_INFO_FORMAT, "red", result.getThrowable()));
		captureScreenShotIfFailedOnGUI(result, testResult);
		ReportHelper.stopRecord(result, testResult);
	}	

	private synchronized void captureScreenShotIfFailedOnGUI(ITestResult result, ExtentTest test) {
		ITestNGMethod testMethod = result.getMethod();
		EnableOneTimeConfig oneTimeConfig = ReportHelper.getAnnotationTestClass(result, EnableOneTimeConfig.class);
		String testName = testMethod.getMethodName();
		String imagePath = Constant.REPORT_PATH + File.separator + testName + "_" + Thread.currentThread().getId() + "_"
				+ DateTimeUtility.getDateStringFormat("yyyy_MM_dd_HH_mm_ss") + ".png";
		boolean isOneTimeConfigGUI = oneTimeConfig != null && !oneTimeConfig.enableAPITest();
		if (oneTimeConfig == null || isOneTimeConfigGUI)
			ReportHelper.captureScreenShot(imagePath, test);

	}

}
