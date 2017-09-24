
package com.mh.ta.core.config.driver.services.internal;

import org.openqa.selenium.WebElement;

import com.google.inject.ImplementedBy;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
public class ImplementedByProcessor {
	private ImplementedByProcessor() {
	}

	public static <T> Class<?> getWrapperClass(Class<T> interfaceCls) {
		if (interfaceCls.isAnnotationPresent(ImplementedBy.class)) {
			ImplementedBy annotation = interfaceCls.getAnnotation(ImplementedBy.class);
			Class<?> clazz = annotation.value();
			if (Element.class.isAssignableFrom(clazz)) {
				return annotation.value();
			}
		}
		if (Element.class.isAssignableFrom(interfaceCls) || WebElement.class.isAssignableFrom(interfaceCls))
			return interfaceCls;
		throw new UnsupportedOperationException("Apply @ImplementedBy interface to your Interface "
				+ interfaceCls.getCanonicalName() + " if you want to extend ");
	}
}
