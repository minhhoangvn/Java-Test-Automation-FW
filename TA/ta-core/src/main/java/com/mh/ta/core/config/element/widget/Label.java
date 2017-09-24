
package com.mh.ta.core.config.element.widget;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
@ImplementedBy(LabelImpl.class)
public interface Label extends Element {

	String getFor();

	String getStyle();
	
	String getInnerText();

}
