package com.mh.ta.support;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mh.ta.base.selenium.SeleniumDriver;
import com.mh.ta.core.annotation.EnableOneTimeConfig;
import com.mh.ta.core.annotation.RecordVideo;
import com.mh.ta.core.helper.DateTimeUtility;
import com.mh.ta.enums.TestStatus;
import com.mh.ta.factory.DriverFactory;
import com.mh.ta.support.report.ReportExtend;
import com.mh.ta.support.video.VideoRecord;

/**
 * 
 * @author minhhoang
 *
 */
public class ReportListener implements ITestListener {
	private final static String MAIN_REPORT_PATH = System.getProperty("user.dir") + File.separator + "TestReport";
	private static final String REPORT_PATH = MAIN_REPORT_PATH + File.separator + "TestSuites" + "_"
			+ DateTimeUtility.getDateStringFormat("yyyy_MM_dd_HH_mm_ss");
	private static ExtentReports extent = ReportExtend.createInstance(REPORT_PATH + File.separator + "TestReport.html");
	private static ExtentTest suites;
	private static ThreadLocal<VideoRecord> recordStorage = new ThreadLocal<VideoRecord>();

	public ReportListener() {
		this.initReportFolder();
		if (suites == null)
			suites = extent.createTest("Test Suites");
	}

	@Override
	public synchronized void onStart(ITestContext ctx) {
		appendSuiteReportInformation(ctx, suites);

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		startRecordTest(result, suites);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		appendTestReportInformation(result, testResult);
		testResult.pass("TEST PASSED");
		stopRecord(result, testResult);
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		appendTestReportInformation(result, testResult);
		testResult.fail(result.getThrowable());
		captureScreenShotIfFailedOnGUI(result, testResult);
		stopRecord(result, testResult);
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		appendTestReportInformation(result, testResult);
		testResult.skip(result.getThrowable());
		stopRecord(result, testResult);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentTest testResult = suites.createNode(result.getMethod().getMethodName());
		appendTestReportInformation(result, testResult);
		testResult.fail(result.getThrowable());
		captureScreenShotIfFailedOnGUI(result, testResult);
		stopRecord(result, testResult);
	}

	private synchronized void initReportFolder() {
		createReportFolder();
		File suiteReportFolder = new File(REPORT_PATH);
		suiteReportFolder.mkdirs();
	}

	private void createReportFolder() {
		File mainReportFolder = new File(MAIN_REPORT_PATH);
		mainReportFolder.mkdirs();
	}

	private synchronized void appendSuiteReportInformation(ITestContext ctx, ExtentTest suites) {
		List<String> suitesInfo = new ArrayList<String>();
		suitesInfo.add(ctx.getSuite().getName());
		for (java.util.Map.Entry<String, String> param : ctx.getCurrentXmlTest().getAllParameters().entrySet()) {
			suitesInfo.add("Suites param: " + param.getKey() + " - " + param.getValue());
		}
		for (XmlTest testTag : ctx.getSuite().getXmlSuite().getTests()) {
			for (XmlClass classTag : testTag.getClasses()) {
				suitesInfo.add("Suites test class: " + classTag.getName());
			}
		}
		suites.info(MarkupHelper.createLabel(Arrays.toString(suitesInfo.toArray()), ExtentColor.GREEN));
	}

	private synchronized void appendTestReportInformation(ITestResult result, ExtentTest test) {
		List<String> testInfo = new ArrayList<String>();
		Object[] testParameters = result.getParameters();
		testInfo.add("Test execution in thread: " + Thread.currentThread().getId());
		testInfo.add("Test description: " + result.getMethod().getDescription());
		testInfo.add("Parallel: " + result.getMethod().getThreadPoolSize() + " thread");
		testInfo.add("Execution test in: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
		for (Object object : testParameters) {
			testInfo.add("Parameter test: " + object.toString());
		}
		test.info(MarkupHelper.createLabel(Arrays.toString(testInfo.toArray()), ExtentColor.BLUE));
	}


	private synchronized void captureScreenShotIfFailedOnGUI(ITestResult result, ExtentTest test) {
		ITestNGMethod testMethod = result.getMethod();
		EnableOneTimeConfig oneTimeConfig = getAnnotationTestClass(result,EnableOneTimeConfig.class);
		if (oneTimeConfig != null)
			if (oneTimeConfig.enableAPITest() != true) {
				System.err.println("Run Capture screen shot");
				String testName = testMethod.getMethodName();
				String imagePath = REPORT_PATH + File.separator + testName + "_" + Thread.currentThread().getId() + "_"
						+ DateTimeUtility.getDateStringFormat("yyyy_MM_dd_HH_mm_ss") + ".png";
				captureScreenShot(imagePath, test);
			}

	}

	private synchronized void captureScreenShot(String imagePath, ExtentTest test) {
		SeleniumDriver driver = (SeleniumDriver) DriverFactory.getDriver();
		driver.captureScreenShot(imagePath, test);
		try {
			test.addScreenCaptureFromPath(imagePath, "ScreenShot for failed test ");
		} catch (IOException e) {
			test.warning("Error in append ScreenShot to report");
		}
	}

	private void startRecordTest(ITestResult result, ExtentTest test) {
		ITestNGMethod testMethod = result.getMethod();
		RecordVideo record = getAnnotationTestMethod(result, RecordVideo.class);
		if (record != null) {
			if (record.enabled()) {
				String videoName = record.videoName().length() > 0 ? record.videoName() : testMethod.getMethodName();
				recordTest(videoName, test);
			}
		}
	}

	private void recordTest(String videoName, ExtentTest test) {
		try {
			VideoRecord record = new VideoRecord(REPORT_PATH);
			recordStorage.set(record);
			recordStorage.get().startRecord(videoName);
		} catch (IOException | AWTException e) {
			test.warning("Error in create video");
			test.warning(e);
			e.printStackTrace();
		}
	}

	private void stopRecord(ITestResult result, ExtentTest test) {
		RecordVideo record = getAnnotationTestMethod(result, RecordVideo.class);
		if (record != null) {
			if (record.enabled()) {
				saveVideo(result, test);
			}
		}
	}

	private void saveVideo(ITestResult result, ExtentTest test) {
		RecordVideo record = getAnnotationTestMethod(result, RecordVideo.class);
		record.saveOnTestStatus();
		recordStorage.get().stopRecording();
		TestStatus status = record.saveOnTestStatus();
		boolean saveAll = status == TestStatus.ALL;
		boolean saveByCorrectStatus = TestStatus.getTestStatusCode(status) != result.getStatus();
		storageVideoProcessing(saveAll, saveByCorrectStatus, test);
	}

	private synchronized void storageVideoProcessing(boolean saveAll, boolean deleteVideo, ExtentTest test) {
		if (deleteVideo && !saveAll) {
			File file = new File(REPORT_PATH + File.separator + VideoRecord.getVideoPath() + ".avi");
			if (!file.delete())
				test.warning("Has error on delete video file");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> T getAnnotationTestMethod(ITestResult result, Class cls){
		ITestNGMethod testMethod = result.getMethod();
		ConstructorOrMethod constructorMethod = testMethod.getConstructorOrMethod();
		Method invokeMethod = constructorMethod.getMethod();
		T annotation = (T) invokeMethod.getDeclaredAnnotation(cls);
		return annotation;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> T getAnnotationTestClass(ITestResult result, Class cls){
		ITestNGMethod testMethod = result.getMethod();
		ConstructorOrMethod constructorMethod = testMethod.getConstructorOrMethod();
		Class invokeTestClass = constructorMethod.getDeclaringClass();
		T annotation = (T) invokeTestClass.getDeclaredAnnotation(cls);
		return annotation;
	}
}