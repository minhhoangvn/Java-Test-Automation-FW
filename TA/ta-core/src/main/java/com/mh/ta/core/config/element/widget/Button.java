
package com.mh.ta.core.config.element.widget;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
@ImplementedBy(ButtonImpl.class)
public interface Button extends Element{

	void mouseClick();

	void javascriptClick();

	void clickToElement();
}
