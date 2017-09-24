
package com.mh.ta.dataprovider;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.mh.ta.core.config.annotation.DataSource;
import com.mh.ta.core.helper.ExcelUtility;
import com.mh.ta.core.helper.FileUtility;

public class TestDataProvider {
	private ExcelUtility excel = new ExcelUtility();
	

	@DataProvider(name = "search", parallel = true)
	public Object[][] apiTestData(Method m) {
		List<List<String>> providerFbData = getSheetData(m);
		int testDataSize = providerFbData.size();
		Object[][] data = new Object[testDataSize][];
		for (int count = 0; count < testDataSize; count++) {
			List<String> currentRowData = providerFbData.get(count);
			data[count] = new Object[] { currentRowData.get(0) };
		}
		return data;
	}	

	private List<List<String>> getSheetData(Method m) {
		DataSource dataSource = m.getAnnotation(DataSource.class);
		if (dataSource != null) {
			Path dataFilePath = FileUtility.findFileOrFolderPath(dataSource.dataFileName(), false).toAbsolutePath();
			List<List<String>> sheetData = excel.readAllSheetValue(dataFilePath.toString(), dataSource.sheetName(),
					true);
			return sheetData;
		}
		return new ArrayList<List<String>>();
	}
}
