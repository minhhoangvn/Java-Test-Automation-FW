
package com.mh.ta.core.base;

import java.util.function.Supplier;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.driver.Driver;

/**
 * @author minhhoang
 *
 */
public abstract class BaseValidation {
	/*
	 * Using supplier function 
	 * avoid GuiceFactory inject with create driver without startdriver
	 */
	protected Supplier<Driver> driver = () ->  DriverFactory.instance().getDriver();
}
