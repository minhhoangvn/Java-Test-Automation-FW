
package com.mh.ta.core.config.element.widget;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
@ImplementedBy(TextInputImpl.class)
public interface TextInput extends Element {

	String getTextInputValue();

	void clearAndSetText(String text);

	void clearText();
}
