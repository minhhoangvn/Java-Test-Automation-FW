
package com.mh.ta.core.base;

import java.util.function.Supplier;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.TestClassFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.driver.Driver;
import com.mh.ta.core.config.driver.services.internal.FieldDecoratorImpl;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.config.settings.SUTConfig;
import com.mh.ta.core.helper.ClassInitializer;
import com.mh.ta.core.helper.Constant;

/**
 * @author minhhoang
 *
 *
 */
public abstract class BasePage<V extends BaseValidation> {
	Logger log = LoggerFactory.instance().createClassLogger(getClass());
	private Class<?> valdidations;
	
	/*
	 * Using supplier function 
	 * avoid GuiceFactory inject with create driver without startdriver
	 */
	protected Supplier<Driver> driver = () -> DriverFactory.instance().getDriver();

	public BasePage() {
		initPageClass();
		if (!TestClassFactory.isEnableAPITest())
			PageFactory.initElements(GuiceInjectFactory.instance().createObjectInstance(FieldDecoratorImpl.class),
					this);
		log.info("Init Page Object " + this.getClass());
	}

	@SuppressWarnings("unchecked")
	public V validation() {
		return (V) GuiceInjectFactory.instance().createObjectInstance(this.valdidations);
	}

	protected void openPage(String path) {
		String fullBaseUri = this.getBaseUrl();
		String pageUrl = path.startsWith("/") ? fullBaseUri + path : fullBaseUri + "/" + path;
		Driver webDriver =  DriverFactory.instance().getDriver();
		webDriver.get(pageUrl);
	}

	protected String getBaseUrl() {
		MainConfig config = GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class,
				ApplicationConfig.class);
		SUTConfig sutConfig = config.getSutConfig();
		String baseUri = sutConfig.getBaseUri();
		boolean isCorrectUrl = baseUri.startsWith("http://") || baseUri.startsWith("https://");		
		return isCorrectUrl ? baseUri : "http://" + baseUri;
	}

	private void initPageClass() {
		Class<?> currentClass = getClass();
		this.valdidations = ClassInitializer.initClass().classInitializer(currentClass,
				Constant.BASE_VALIDATION_CLASS_NAME);
	}
}
