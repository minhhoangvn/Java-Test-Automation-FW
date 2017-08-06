package com.mh.ta.keywords;

import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.interfaces.element.TAElement;

public class TAWebKeywords {

	@HighLightElement
	public void clickToElement(TAElement element) {
		element.clickElement();
	}

	@HighLightElement
	public void intputTextToElement(TAElement element, String textValue) {
		element.inputTextToElement(textValue);
	}
}
