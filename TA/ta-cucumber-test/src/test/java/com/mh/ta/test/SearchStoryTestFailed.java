
package com.mh.ta.test;

import com.mh.ta.core.base.BaseCucumberTestNG;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/SearchWithFailed.feature", glue = { "com.mh.ta.test.stepdefs",
		"com.mh.ta.core.base" }, plugin = { "com.mh.ta.core.report.CucumberReportListener",
				"json:target/cucumber-report.json", "html:target/cucumber-report/SearchStory" })
public class SearchStoryTestFailed extends BaseCucumberTestNG {
}
