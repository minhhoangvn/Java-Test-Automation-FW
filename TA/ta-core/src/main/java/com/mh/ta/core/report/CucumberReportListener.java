
package com.mh.ta.core.report;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.helper.Constant;

import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.DataTableRow;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.ExamplesTableRow;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;

/**
 * Using open source on https://github.com/email2vimalraj/CucumberExtentReporter
 * with modify for auto generate report folder base on time stamp
 * 
 * @author minhhoang
 *
 */

public class CucumberReportListener implements Reporter, Formatter {

	private static ExtentReports extentReports;
	private static ExtentHtmlReporter htmlReporter;
	private static ThreadLocal<ExtentTest> featureTestThreadLocal = new InheritableThreadLocal<>();
	private static ThreadLocal<ExtentTest> scenarioOutlineThreadLocal = new InheritableThreadLocal<>();
	static ThreadLocal<ExtentTest> scenarioThreadLocal = new InheritableThreadLocal<>();
	private static ThreadLocal<LinkedList<Step>> stepListThreadLocal = new InheritableThreadLocal<>();
	static ThreadLocal<ExtentTest> stepTestThreadLocal = new InheritableThreadLocal<>();
	private boolean scenarioOutlineFlag;

	public CucumberReportListener() {
		ReportHelper.initReportFolder();
		createExtentHtmlReport();
		setExtentReport();
		stepListThreadLocal.set(new LinkedList<>());
		scenarioOutlineFlag = false;
	}

	private static void createExtentHtmlReport() {
		File file = null;
		if (htmlReporter != null) {
			return;
		}
		file = new File(Constant.REPORT_PATH + File.separator + "TestReport.html");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		htmlReporter = new ExtentHtmlReporter(file);
	}

	static ExtentHtmlReporter getExtentHtmlReport() {
		return htmlReporter;
	}

	private static void setExtentReport() {
		if (extentReports != null) {
			return;
		}
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
	}

	static ExtentReports getExtentReport() {
		return extentReports;
	}

	public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
		/**
		 * Not using this listener
		 */
	}

	public void uri(String uri) {
		/**
		 * Not using this listener
		 */

	}

	public void feature(Feature feature) {
		featureTestThreadLocal.set(getExtentReport()
				.createTest(com.aventstack.extentreports.gherkin.model.Feature.class, feature.getName()));
		ExtentTest test = featureTestThreadLocal.get();

		for (Tag tag : feature.getTags()) {
			test.assignCategory(tag.getName());
		}
	}

	public void scenarioOutline(ScenarioOutline scenarioOutline) {
		scenarioOutlineFlag = true;
		ExtentTest node = featureTestThreadLocal.get().createNode(
				com.aventstack.extentreports.gherkin.model.ScenarioOutline.class, scenarioOutline.getName());
		scenarioOutlineThreadLocal.set(node);
	}

	public void examples(Examples examples) {
		ExtentTest test = scenarioOutlineThreadLocal.get();

		String[][] data = null;
		List<ExamplesTableRow> rows = examples.getRows();
		int rowSize = rows.size();
		for (int i = 0; i < rowSize; i++) {
			ExamplesTableRow examplesTableRow = rows.get(i);
			List<String> cells = examplesTableRow.getCells();
			int cellSize = cells.size();
			if (data == null) {
				data = new String[rowSize][cellSize];
			}
			for (int j = 0; j < cellSize; j++) {
				data[i][j] = cells.get(j);
			}
		}
		test.info(MarkupHelper.createTable(data));
	}

	public void startOfScenarioLifeCycle(Scenario scenario) {
		if (scenarioOutlineFlag) {
			scenarioOutlineFlag = false;
		}

		ExtentTest scenarioNode;
		if (scenarioOutlineThreadLocal.get() != null
				&& scenario.getKeyword().trim().equalsIgnoreCase("Scenario Outline")) {
			scenarioNode = scenarioOutlineThreadLocal.get()
					.createNode(com.aventstack.extentreports.gherkin.model.Scenario.class, scenario.getName());
		} else {
			scenarioNode = featureTestThreadLocal.get()
					.createNode(com.aventstack.extentreports.gherkin.model.Scenario.class, scenario.getName());
		}

		for (Tag tag : scenario.getTags()) {
			scenarioNode.assignCategory(tag.getName());
		}
		scenarioThreadLocal.set(scenarioNode);
	}

	public void background(Background background) {
		/**
		 * Not using this listener
		 */

	}

	public void scenario(Scenario scenario) {
		/**
		 * Not using this listener
		 */
	}

	public void step(Step step) {
		if (scenarioOutlineFlag) {

			return;
		}
		stepListThreadLocal.get().add(step);
	}

	public void endOfScenarioLifeCycle(Scenario scenario) {
		/**
		 * Not using this listener
		 */
	}

	public void done() {

		getExtentReport().flush();
	}

	public void close() {
		/**
		 * Not using this listener in cucumber
		 */

	}

	public void eof() {
		/**
		 * Not using this listener in cucumber
		 */
	}

	public void before(Match match, Result result) {
		/**
		 * Not using this listener in cucumber
		 */
	}

	public void result(Result result) {
		if (scenarioOutlineFlag) {
			return;
		}
		stepTestThreadLocal.get().info("Execution finished in: " + getExecutionTiming(result));
		if (Result.PASSED.equals(result.getStatus())) {
			stepTestThreadLocal.get().pass(Result.PASSED.toUpperCase());
		} else if (Result.FAILED.equals(result.getStatus())) {
			addedScreenShotOnFailed();
			stepTestThreadLocal.get().fail(result.getError());
		} else if (Result.SKIPPED.equals(result)) {
			stepTestThreadLocal.get().skip(Result.SKIPPED.getStatus().toUpperCase());
		} else if (Result.UNDEFINED.equals(result)) {
			stepTestThreadLocal.get().skip(Result.UNDEFINED.getStatus().toUpperCase());
		}
	}

	public void after(Match match, Result result) {
		/**
		 * Not using this listener
		 */
	}

	public void match(Match match) {
		Step step = stepListThreadLocal.get().poll();
		String[][] data = getDataTableString(step);
		ExtentTest stepTest = createTestStepNode(step);
		if (stepTest != null) {
			appendDataTableToStepNode(data, stepTest);
			stepTestThreadLocal.set(stepTest);
		}
	}

	private String[][] getDataTableString(Step step) {
		String[][] data = null;
		if (step.getRows() != null) {
			List<DataTableRow> rows = step.getRows();
			int rowSize = rows.size();
			for (int i = 0; i < rowSize; i++) {
				DataTableRow dataTableRow = rows.get(i);
				List<String> cells = dataTableRow.getCells();
				int cellSize = cells.size();
				if (data == null) {
					data = new String[rowSize][cellSize];
				}
				for (int j = 0; j < cellSize; j++) {
					data[i][j] = cells.get(j);
				}
			}
		}
		return data;
	}

	private ExtentTest createTestStepNode(Step step) {
		ExtentTest stepTest = null;
		ExtentTest scenarioTest = scenarioThreadLocal.get();
		try {
			stepTest = scenarioTest.createNode(new GherkinKeyword(step.getKeyword()), step.getName());
			stepTest.info("Step in future file line: " + step.getLine());

		} catch (ClassNotFoundException ignore) {
			//
		}
		return stepTest;
	}

	private void appendDataTableToStepNode(String[][] data, ExtentTest stepTest) {
		if (data != null) {
			Markup table = MarkupHelper.createTable(data);
			stepTest.info(table);
		}
	}

	public void embedding(String mimeType, byte[] data) {
		/**
		 * Not using this listener in cucumber
		 */
	}

	public void write(String text) {
		/**
		 * Not using this listener in cucumber
		 */
	}

	private void addedScreenShotOnFailed() {
		Driver driver = DriverFactory.instance().getDriver();
		String screenShotString = driver.createScreenShotAsString();
		stepTestThreadLocal.get().info("<img src='data:image/png;base64," + screenShotString + "'>");
	}

	/**
	 * Using try catch because cucumber not handle in case test failed and
	 * method getDuration will throw null pointer
	 * 
	 * @param result
	 * @return
	 */
	private String getExecutionTiming(Result result) {
		try {
			long executionTime = result.getDuration();
			return result.getDuration() != null ? " : " + (Math.round(executionTime / 1000000000f * 100f) / 100f) + "s"
					: "";
		} catch (NullPointerException e) {
			return "Has error in run sceanrio.";
		}
	}
}
