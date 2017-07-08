package com.mh.ta.core.core.element;

import java.util.List;

public interface ElementFinder<E extends Element> {
	Element findElement(FindBy by);

	List<Element> findAllElements(FindBy by);
}
