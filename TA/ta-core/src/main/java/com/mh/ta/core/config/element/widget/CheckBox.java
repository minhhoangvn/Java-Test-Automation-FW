
package com.mh.ta.core.config.element.widget;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * Interface that wraps a WebElement in CheckBox functionality.
 */

/**
 * @author minhhoang
 *
 */
@ImplementedBy(CheckBoxImpl.class)
public interface CheckBox extends Element {

	void toggle();

	void check();

	void uncheck();

	boolean isChecked();
}
