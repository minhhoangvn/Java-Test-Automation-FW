
package com.mh.ta.core.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.annotation.RecordVideo;
import com.mh.ta.core.config.enums.TestStatus;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.config.settings.SUTConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.core.report.video.IVideoRecord;
import com.mh.ta.core.report.video.VideoRecord;

/**
 * @author minhhoang
 *
 */
public class ReportHelper {

	private static Logger log = LoggerFactory.instance().createClassLogger(ReportHelper.class);
	private static ThreadLocal<IVideoRecord> recordStorage = new ThreadLocal<>();

	private ReportHelper() {
		throw new IllegalStateException("Report Helper class");
	}

	public static void captureScreenShot(String imagePath, ExtentTest test) {
		createScreenShot(imagePath, test);
		embeddingImageToReport(imagePath, test);
	}

	public static void initReportFolder() {
		File mainReportFolder = new File(Constant.MAIN_REPORT_PATH);
		mainReportFolder.mkdirs();
		File suiteReportFolder = new File(Constant.REPORT_PATH);
		suiteReportFolder.mkdirs();
	}

	private static void createScreenShot(String filePath, ExtentTest... testReport) {
		try {
			DriverFactory.instance().getDriver().createScreenShot(filePath, testReport);
		} catch (Exception e) {
			testReport[0].info("Error in capture screenshot ");
			testReport[0].info(e);
		}
	}

	private static void embeddingImageToReport(String imagePath, ExtentTest test) {
		String encodedfile = "";
		File imageFile = new File(imagePath);
		try (FileInputStream fileInputStreamReader = new FileInputStream(imageFile)) {
			byte[] bytes = new byte[(int) imageFile.length()];
			while (fileInputStreamReader.read(bytes) > 0) {
				encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
				test.info("<img src='data:image/png;base64," + encodedfile + "'>");
			}
		} catch (IOException e) {
			test.warning("Error in embedding ScreenShot to report, open report folder for view screenshot");
			log.error("Error in embedding image in report. ", e);
		}
	}

	public static void createTestDetailInformation(ExtentTest test) {
		Set<String> testDetail = ReportInformation.getTestInformation();
		for (String detail : testDetail) {
			test.info(MarkupHelper.createLabel(detail, ExtentColor.INDIGO));
		}
	}

	public static void appendSuiteReportInformation(ITestContext ctx, ExtentTest suites) {
		List<String> suitesInfo = new ArrayList<>();
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

	public static void startRecordTest(ITestResult result, ExtentTest test) {
		ITestNGMethod testMethod = result.getMethod();
		RecordVideo record = getAnnotationTestMethod(result, RecordVideo.class);
		boolean isEnableRecordInGlobalSetting = isEnableRecordInGlobalSetting();
		boolean isRecordByAnnotation = record != null && record.enabled();
		boolean isStartRecordVideo = isEnableRecordInGlobalSetting || isRecordByAnnotation;
		if (isStartRecordVideo) {
			String videoNameInAnnotation = record != null ? record.videoName() : "";
			String videoName = videoNameInAnnotation.length() > 0 ? videoNameInAnnotation : testMethod.getMethodName();
			recordTest(videoName, test);
		}
	}

	private static void recordTest(String videoName, ExtentTest test) {
		try {
			VideoRecord record = new VideoRecord(Constant.REPORT_PATH);
			recordStorage.set(record);
			recordStorage.get().startRecord(videoName);
		} catch (TestContextException e) {
			test.warning("Error in create video");
			test.warning(e);
		}
	}

	public static void stopRecord(ITestResult result, ExtentTest test) {
		RecordVideo record = getAnnotationTestMethod(result, RecordVideo.class);
		boolean isEnableRecordInGlobalSetting = isEnableRecordInGlobalSetting();
		boolean isRecordByAnnotation = record != null && record.enabled();
		boolean isStopRecordVideo = isEnableRecordInGlobalSetting || isRecordByAnnotation;
		if (isStopRecordVideo) {
			saveVideo(result, test);
		}
	}

	private static void saveVideo(ITestResult result, ExtentTest test) {
		RecordVideo record = getAnnotationTestMethod(result, RecordVideo.class);
		TestStatus status = record != null ? record.saveOnTestStatus() : TestStatus.FAILED;
		boolean saveAll = status == TestStatus.ALL;
		boolean saveByCorrectStatus = TestStatus.getTestStatusCode(status) == result.getStatus();
		boolean isFailedTest = result.getStatus() == ITestResult.FAILURE;
		boolean saveVideoByGlobalConfig = isEnableRecordInGlobalSetting() && isFailedTest;
		boolean isStorageVideo = saveVideoByGlobalConfig || saveByCorrectStatus || saveAll;	
		storageVideoProcessing(isStorageVideo, test);
	}

	private static void storageVideoProcessing(boolean isStorageVideo, ExtentTest test) {
		String videoName = recordStorage.get().stopRecording();
		if (!isStorageVideo) {
			File file = new File(Constant.REPORT_PATH + File.separator + videoName + ".avi");
			if (!file.delete()) {				
				test.warning("Has error on delete video file");
			}
		}
	}

	private static boolean isEnableRecordInGlobalSetting() {
		MainConfig mainConfig = GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class,
				ApplicationConfig.class);
		SUTConfig videoConfig = mainConfig.getSutConfig();
		return videoConfig.getEnableRecord();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getAnnotationTestClass(ITestResult result, Class cls) {
		ITestNGMethod testMethod = result.getMethod();
		ConstructorOrMethod constructorMethod = testMethod.getConstructorOrMethod();
		Class invokeTestClass = constructorMethod.getDeclaringClass();
		return (T) invokeTestClass.getDeclaredAnnotation(cls);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T getAnnotationTestMethod(ITestResult result, Class cls) {
		ITestNGMethod testMethod = result.getMethod();
		ConstructorOrMethod constructorMethod = testMethod.getConstructorOrMethod();
		Method invokeMethod = constructorMethod.getMethod();
		return (T) invokeMethod.getDeclaredAnnotation(cls);
	}

}
