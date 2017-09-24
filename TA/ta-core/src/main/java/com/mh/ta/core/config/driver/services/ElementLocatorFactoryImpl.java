
package com.mh.ta.core.config.driver.services;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * @author minhhoang
 *
 */
public class ElementLocatorFactoryImpl implements ElementLocatorFactory {	

	public ElementLocatorFactoryImpl() {		
		/**
		 * Create public contructor for guice
		 */
	}

	@Override
	public ElementLocator createLocator(Field field) {
		return new ElementLocatorHandlerImpl(field);
	}

}
