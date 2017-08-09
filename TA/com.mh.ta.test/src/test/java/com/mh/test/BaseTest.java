package com.mh.test;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.inject.Inject;
import com.mh.ta.base.test.BaseWebTestNG;
import com.mh.ta.test.pages.LoginPage;

public class BaseTest extends BaseWebTestNG {
	/*
	 * user can put all data driven method here
	 */

	@Inject
	LoginPage login;

	@DataProvider(name = "DataA")
	public Object[][] sampleTestDataA(Method m) {
		List<List<String>> sheetData = getAllExcelSheetData(m);
		int rowCount = sheetData.size();
		Object[][] data = new Object[rowCount][];
		for (int count = 0; count < rowCount; count++)
			data[count] = sheetData.get(count).toArray();
		return data;
	}

	@DataProvider(name = "DataB")
	public Object[][] sampleTestDataB(Method m) {
		List<List<String>> sheetData = getAllExcelSheetData(m);
		int rowCount = sheetData.size();
		Object[][] data = new Object[rowCount][];
		for (int count = 0; count < rowCount; count++)
			data[count] = sheetData.get(count).toArray();
		return data;
	}

	@DataProvider(name = "DataC")
	public Object[] sampleTestDataC(Method m) {
		List<List<String>> sheetData = getAllExcelSheetData(m);
		return sheetData.toArray();
	}

	@DataProvider(name = "DataD")
	public Object[][] sampleTestDataD(Method m) {
		List<List<String>> sheetData = getAllExcelSheetData(m);
		int rowCount = sheetData.size();
		Object[][] data = new Object[rowCount][];
		for (int count = 0; count < rowCount; count++)
			data[count] = sheetData.get(count).toArray();
		return data;
	}

}
