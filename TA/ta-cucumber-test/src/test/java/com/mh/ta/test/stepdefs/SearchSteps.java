/**
 * @author minhhoang
 *
 *
 */
package com.mh.ta.test.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * SearchSteps
 *
 */
public class SearchSteps extends BaseSteps{
	
	@Given("^User is at Search Page$")
	public void userOpenSearchPageGoogle() {
		searchPage.openSearchPage();
	}
	
	@When("^I search information with string \"([^\"]*)\"$")
	public void userInputSearch(String data) {
		searchPage.inputToSearchField(data);
		searchPage.clickSearchButton();
	}
	
	@Then("I get search result page with contains \"([^\"]*)\"$")
	public void shouldDisplayedPageTitleIncludeSearchString(String expected) {
		searchPage.validation().shouldContainsSearchDataInPageTitle(expected);
	}
}
