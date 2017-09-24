package com.mh.ta.core.base;

import java.util.function.Supplier;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.TestClassFactory;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.config.driver.services.internal.FieldDecoratorImpl;
import com.mh.ta.core.config.element.Element;

/**
 * @author minhhoang
 *
 */
public abstract class BaseComponent {
	Logger log = LoggerFactory.instance().createClassLogger(getClass());

	public BaseComponent() {
		if (!TestClassFactory.isEnableAPITest())
			PageFactory.initElements(GuiceInjectFactory.instance().createObjectInstance(FieldDecoratorImpl.class),
					this);
		log.info("Init Component Object " + this.getClass());
	}
	
	/*
	 * Using supplier function 
	 * avoid GuiceFactory inject with create driver without startdriver
	 */
	protected Supplier<Driver> driver = () -> DriverFactory.instance().getDriver();

	public abstract boolean isComponentEnable();

	public abstract Element getParentElement();

}
