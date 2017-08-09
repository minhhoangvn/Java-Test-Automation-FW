package com.mh.ta.keywords.web;

import java.util.List;

import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.interfaces.element.TAElement;

class TableKeywords {
	public int countRowNumber(TAElement element) {
		if (!ElementResolvedServices.resolved(element).getTagName().equalsIgnoreCase("table"))
			throw new TestContextException("Element is not table element");
		return element.findListElement(FindBy.tagName("tr")).size();
	}
	

	public String getCellText(TAElement element, int rowIndex, int columnIndex) {
		List<TAElement> listRowElement = element.findListElement(FindBy.tagName("tr"));
		List<TAElement> cellValuesBody = listRowElement.get(rowIndex).findListElement(FindBy.tagName("td"));
		cellValuesBody.addAll(listRowElement.get(rowIndex).findListElement(FindBy.tagName("th")));
		TAElement cellElement = cellElement(cellValuesBody, columnIndex);
		return ElementResolvedServices.resolved(cellElement).getText();
	}
	

	public TAElement getCellElement(TAElement element, int rowIndex, int columnIndex) {
		List<TAElement> listRowElement = element.findListElement(FindBy.tagName("tr"));
		List<TAElement> cellValuesBody = listRowElement.get(rowIndex).findListElement(FindBy.tagName("td"));
		cellValuesBody.addAll(listRowElement.get(rowIndex).findListElement(FindBy.tagName("th")));
		TAElement cellElement = cellElement(cellValuesBody, columnIndex);
		return cellElement;
	}

	@HighLightElement
	private TAElement cellElement(List<TAElement> rowElement, int index) {
		return rowElement.get(index);
	}
}
