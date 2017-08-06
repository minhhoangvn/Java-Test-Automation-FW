package com.mh.ta.interfaces.element;

import java.util.List;

public interface SelectElement {
	public void selectByText(String text);

	public void selectByIndex(int index);

	public List<String> getListSelectOptions();

	public TAElement getSelectedElement();

	public void deselectAllOptions();

	public void selectAllOptions();
}
