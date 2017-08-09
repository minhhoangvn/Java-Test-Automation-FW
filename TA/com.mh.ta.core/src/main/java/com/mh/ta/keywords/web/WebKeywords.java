package com.mh.ta.keywords.web;

import java.util.List;

import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.factory.GuiceInjectFactory;
import com.mh.ta.interfaces.element.TAElement;

public class WebKeywords {
	private InputKeywords input = GuiceInjectFactory.instance().getObjectInstance(InputKeywords.class);
	private ButtonKeywords button = GuiceInjectFactory.instance().getObjectInstance(ButtonKeywords.class);
	private ComboboxKeywords combobox = GuiceInjectFactory.instance().getObjectInstance(ComboboxKeywords.class);
	private CheckBoxKeywords checkbox = GuiceInjectFactory.instance().getObjectInstance(CheckBoxKeywords.class);
	private TableKeywords table = GuiceInjectFactory.instance().getObjectInstance(TableKeywords.class);

	@HighLightElement
	public void clickToElement(TAElement element) {
		button.clickToElement(element);
	}

	@HighLightElement
	public void intputTextToElement(TAElement element, String textValue) {
		input.inputText(element, textValue);
	}

	@HighLightElement
	public void selectByText(TAElement element, String option) {
		combobox.selectOptionByText(element, option);
	}

	@HighLightElement
	public void selectByIndex(TAElement element, int index) {
		combobox.selectOptionByIndex(element, index);
	}

	@HighLightElement
	public void hoverMouseToElement(TAElement element) {
		button.hoverMouseToElement(element);
	}

	@HighLightElement
	public void checkOption(TAElement element) {
		checkbox.checkOption(element);
	}

	@HighLightElement
	public void uncheckOption(TAElement element) {
		checkbox.uncheckOption(element);
	}

	public TAElement getSelectedElement(TAElement element) {
		return combobox.getSelectedElement(element);
	}

	public int countTableRow(TAElement element) {
		return table.countRowNumber(element);
	}

	@HighLightElement
	public String getTableCellElementText(TAElement element, int rowIndex, int columnIndex) {
		return table.getCellText(element, rowIndex, columnIndex);
	}

	@HighLightElement
	public TAElement getTableCellElement(TAElement element, int rowIndex, int columnIndex) {
		return table.getCellElement(element, rowIndex, columnIndex);
	}

	@HighLightElement
	public List<String> getListOptionsText(TAElement element) {
		return combobox.getListOptionsText(element);
	}
}
