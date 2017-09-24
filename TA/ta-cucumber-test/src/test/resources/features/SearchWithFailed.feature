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
	Then I get search result page with contains "Failed Scenario"
	
@search 
Scenario Outline: Search information with list data: 
	When I search information with string "<searchData>"
	Then I get search result page with contains "Failed Scenario"
	Examples: 
		|searchData|
		|Selenium And Cucumber|
		|BBD vs ATDD vs TDD vs DDD |
		|github/minhhoangvn|
		|testNG and Cucumber and running in parallel|
	