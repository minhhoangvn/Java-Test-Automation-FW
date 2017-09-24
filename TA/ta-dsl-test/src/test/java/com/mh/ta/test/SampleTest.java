/**
 * @author minhhoang
 *
 *
 */
package com.mh.ta.test;

import org.testng.annotations.Test;

import com.mh.ta.core.config.annotation.DataSource;
import com.mh.ta.dataprovider.TestDataProvider;

/**
 * SampleTest
 *
 */
public class SampleTest extends BaseTest{

	

	@Test
	public void shouldAssertPassed() {
		// Act
		searchPage.openSearchPage();
		searchPage.inputToSearchField("Hard Code Data");
		searchPage.clickSearchButton();
		
		// Assert
		searchPage.validation().shouldContainsSearchDataInPageTitle("Hard Code Data");
	}
	
	@Test
	public void shouldAssertFailed() {
		// Act
		searchPage.openSearchPage();
		searchPage.inputToSearchField("Hard Code Data");
		searchPage.clickSearchButton();
		
		// Assert
		searchPage.validation().shouldContainsSearchDataInPageTitle("Another data for assert failed");
	}
	
	@DataSource(dataFileName="TestData.xlsx",sheetName="SearchData")
	@Test(dataProvider="search", dataProviderClass = TestDataProvider.class, threadPoolSize=4)
	public void shouldAssertPassedWithDataProvider(String searchData) {
		// Act
		searchPage.openSearchPage();
		searchPage.inputToSearchField(searchData);
		searchPage.clickSearchButton();
		
		// Assert
		searchPage.validation().shouldContainsSearchDataInPageTitle(searchData);
	}
}
