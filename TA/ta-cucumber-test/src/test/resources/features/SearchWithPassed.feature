@search 
Feature: Search 
	As a QA Engineer
	I need to be able to search for information in google
	So that I could resolve my issue
	
Background: 
	Given User is at Search Page
	
@search 
Scenario: User Can Search information successfully 
	When I search information with string "Sample Text For Search"
	Then I get search result page with contains "Sample Text For Search"
	
@search 
Scenario Outline: Search information with list data: 
	When I search information with string "<searchData>"
	Then I get search result page with contains "<searchData>"
	Examples: 
		|searchData|
		|Selenium|
		|Test Automation|
		|github/minhhoangvn|
		|testNG and Cucumber|
	