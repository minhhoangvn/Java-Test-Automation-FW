
package com.mh.ta.core.config.element.widget;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
@ImplementedBy(SelectImpl.class)
public interface Select extends Element {

	boolean isMultiple();

	void deselectByIndex(int index);

	void selectByValue(String value);

	WebElement getFirstSelectedOption();

	void selectByVisibleText(String text);

	void deselectByValue(String value);

	void deselectAll();

	List<WebElement> getAllSelectedOptions();

	List<WebElement> getOptions();

	void deselectByVisibleText(String text);

	void selectByIndex(int index);

	List<String> getListOptionsText();
}
