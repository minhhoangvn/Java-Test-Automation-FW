
package com.mh.ta.core.helper;

import java.io.File;

/**
 * This using for demo framework Should using class for email template builder
 * 
 * @author minhhoang
 *
 */
/**
 * @author minhhoang
 *
 */
public class Constant {
	private Constant() {
		throw new IllegalStateException("Constant class");
	}
	
	public static final String MAIN_CONFIG_MODULE_NAME = "MainConfigModule";
	public static final String BASE_VALIDATION_CLASS_NAME = "BaseValidation";
	public static final String TEST_INFO_FORMAT = "<h5 style='color:%s; font-weight:900'>%s</h5>";
	public static final String MAIN_REPORT_PATH = System.getProperty("user.dir") + File.separator + "TestReport";
	public static final String REPORT_PATH = MAIN_REPORT_PATH + File.separator + "TestSuites" + "_"
			+ DateTimeUtility.getDateStringFormat("yyyy_MM_dd_HH_mm_ss");
	public static final String EMAIL_TEMPLATE = "<html><h4>Dear all,</h4><p><h4>Below is test suites summary:</h4><br/><p>%s</p></p><p><h4>Thanks,</h4><p/></html>";
	public static final String ROW_TABLE_TEMPLATE = "<tr>%s</tr>";
	public static final String COLUMN_TABLE_TEMPLATE = "<td>%s</td>";
	public static final String BASE_TESTNG_WEB_TEST = "BaseWebTestNG";
	public static final String BASE_TESTNG_CUCUMBER_TEST = "BaseCucumberTestNG";
	public static final String HTML_SYTLE_SOURCE_STRING = "<head> <style> table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%; font-size:16px;} td, th { border: 1px solid #dddddd; text-align: left; padding: 8px; } tr:nth-child(even) { background-color: #dddddd; } </style> </head>";
}
