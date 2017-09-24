
package com.mh.ta.core.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mh.ta.core.config.enums.TestStatus;
import com.mh.ta.core.helper.Constant;

/**
 * @author minhhoang
 *
 */
public class ReportInformation {
	private static ThreadLocal<LinkedList<String>> testInformation = new ThreadLocal<>();
	private static List<String> emailInformation = new ArrayList<>();
	
	private ReportInformation(){
		throw new IllegalStateException("Report Information Utility class");
	}
	public static void appendTestInformation(String information) {
		if (testInformation.get() == null)
			testInformation.set(new LinkedList<String>());
		testInformation.get().add(information);
	}

	public static void appendTestReportInformation(ITestResult result, ExtentTest test) {
		List<String> testInfo = new ArrayList<>();
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

	public static void appendEmailInformation(ITestResult rs) {
		emailInformation.add(createEmailTestResultInformation(rs));
	}

	public static List<String> getEmailInformation() {
		return emailInformation;
	}

	public static Set<String> getTestInformation() {
		LinkedList<String> informations = testInformation.get();
		if (informations == null)
			return new LinkedHashSet<>(Arrays.asList("Test not has informations"));
		return new LinkedHashSet<>(informations);
	}

	private static String createEmailTestResultInformation(ITestResult rs) {
		String rowHtml = "";
		rowHtml += String.format(Constant.COLUMN_TABLE_TEMPLATE, rs.getName());
		rowHtml += String.format(Constant.COLUMN_TABLE_TEMPLATE, TestStatus.getTestStatus(rs.getStatus()).toString());
		rowHtml += String.format(Constant.COLUMN_TABLE_TEMPLATE, rs.getThrowable());
		return String.format(Constant.ROW_TABLE_TEMPLATE, rowHtml);

	}

}
