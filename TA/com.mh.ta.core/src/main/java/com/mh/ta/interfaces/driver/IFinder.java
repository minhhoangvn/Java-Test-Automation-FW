package com.mh.ta.interfaces.driver;

import java.util.List;

import com.mh.ta.base.selenium.webelement.FindBy;
import com.mh.ta.interfaces.element.TAElement;

public interface IFinder {
	TAElement findElement(FindBy by);

	List<TAElement> findListElement(FindBy by);

	TAElement findElementUntilVisible(FindBy by, int timeOut, int pollingTime);
}
