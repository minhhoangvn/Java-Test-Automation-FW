/**
 * @author minhhoang
 *
 *
 */
package com.mh.ta.pages.search;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import com.mh.ta.core.base.BasePage;
import com.mh.ta.core.config.annotation.FindByUntil;
import com.mh.ta.core.config.element.Element;
import com.mh.ta.core.config.element.widget.TextInput;
import com.mh.ta.core.config.enums.WaitUntilType;



public class SearchPage extends BasePage<SearchValidation> {
	private static final String PATH = "/";

	public void openSearchPage() {
		this.openPage(PATH);
	}

	@FindBy(id = "lst-ib")
	TextInput txtSearchBox;
	
	@FindByUntil(id = "_fZl", waitType=WaitUntilType.CLICKABLE)
	Element btnSearch;

	
	public void inputToSearchField(String data) {
		txtSearchBox.clearAndSetText(data);
		txtSearchBox.sendKeys(Keys.ENTER);
	}
	
	public void clickSearchButton() {
		btnSearch.click();
	}
	
	public String getSearchPageTitle() {
		return driver.get().getTitle();
	}
}
