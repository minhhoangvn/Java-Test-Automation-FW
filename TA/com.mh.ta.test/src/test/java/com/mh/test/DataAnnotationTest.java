package com.mh.test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mh.ta.base.test.TAAssert;
import com.mh.ta.core.annotation.DataSource;
import com.mh.ta.core.annotation.EnableOneTimeConfig;

@EnableOneTimeConfig(enable = false, enableAPITest = true)
public class DataAnnotationTest extends BaseTest {

	@DataSource(dataFileName = "SampleTestData.xlsx", sheetName = "DataSheet1")
	@Test(dataProvider = "DataA")
	public void sampleTestDataSheetA(String column1) {
		Assert.assertTrue(column1.equals("Test Data Sheet 1"));
	}

	@DataSource(dataFileName = "SampleTestData.xlsx", sheetName = "DataSheet2")
	@Test(dataProvider = "DataB")
	public void sampleTestDataSheetB(String column1, String column2) {
		Assert.assertTrue(column1.equals("Test Data Sheet 2 "));
		Assert.assertTrue(column2.equals("Another Column"));
	}

	@DataSource(dataFileName = "SampleTestData2.xlsx", sheetName = "DataSheetData")
	@Test(dataProvider = "DataC")
	public void sampleTestDataSheetC(List<Object> data) {
		TAAssert.listTextValueShouldEqual(Arrays.asList("a", "b", "c", "d"), data);
	}

	@DataSource(dataFileName = "SampleTestData2.xlsx", sheetName = "DataSheetData2")
	@Test(dataProvider = "DataD")
	public void sampleTestDataSheetD(String column1) {
		Assert.assertTrue(column1.equals(
				"Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text "));
	}
}
