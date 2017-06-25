package com.mh.ta.core.webdriver;

import com.mh.ta.core.driver.Driver;
import com.mh.ta.core.driver.DriverCapabilities;
import com.mh.ta.core.driver.DriverOption;

public abstract class Browser<Type, Capabilities, Option>
		implements Driver<Type>, DriverCapabilities<Capabilities>, DriverOption<Option> {

	protected Option options;
	protected Capabilities capabilities;
	
	public Browser() {		
	}

	public Browser(Capabilities capabilities, Option options) {		
		this.capabilities = capabilities;
		this.options = options;
	}

}
