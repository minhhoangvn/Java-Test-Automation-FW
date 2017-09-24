
package com.mh.ta.core.config.element.widget;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
@ImplementedBy(TableImpl.class)
public interface Table extends Element {

	int getRowCount();

	int getColumnCount();

	List<WebElement> getAllTableRowElement();

	List<WebElement> getAllCellValueInRow(int rowIndex);

	WebElement getCellAtIndex(int rowIndex, int columnIndex);
}
