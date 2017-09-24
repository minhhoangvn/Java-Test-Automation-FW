
package com.mh.ta.core.config.element.widget;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mh.ta.core.config.element.ElementImpl;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
class TableImpl extends ElementImpl implements Table {

	public TableImpl(WebElement element) {
		super(element);
	}

	@Override
	public int getRowCount() {
		return getRows().size();
	}

	@Override
	public int getColumnCount() {
		return findElements(By.cssSelector("tr:first-of-type > *")).size();
	}

	@Override
	public List<WebElement> getAllTableRowElement() {
		return getRows();
	}

	@Override
	public List<WebElement> getAllCellValueInRow(int rowIndex) {
		List<WebElement> bodyRow = findElements(By.cssSelector("tbody>tr"));
		WebElement rowElement = bodyRow.get(rowIndex);
		return rowElement.findElements(By.cssSelector("td"));
	}

	@Override
	public WebElement getCellAtIndex(int rowIndex, int columnIndex) {
		WebElement row = getRows().get(rowIndex);

		List<WebElement> cells;

		// Cells are most likely to be td tags
		if (!row.findElements(By.tagName("td")).isEmpty()) {
			cells = row.findElements(By.tagName("td"));
			return cells.get(columnIndex);
		}
		// Failing that try th tags
		else if (!row.findElements(By.tagName("th")).isEmpty()) {
			cells = row.findElements(By.tagName("th"));
			return cells.get(columnIndex);
		} else {
			final String error = String.format("Could not find cell at row: %s column: %s", rowIndex, columnIndex);
			throw new TestContextException(error);
		}
	}

	private List<WebElement> getRows() {
		List<WebElement> rows = new ArrayList<>();

		// Header rows
		rows.addAll(findElements(By.cssSelector("thead tr")));

		// Body rows
		rows.addAll(findElements(By.cssSelector("tbody tr")));

		// Footer rows
		rows.addAll(findElements(By.cssSelector("tfoot tr")));

		return rows;
	}

}
