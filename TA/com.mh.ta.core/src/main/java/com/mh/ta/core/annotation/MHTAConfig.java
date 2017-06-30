package com.mh.ta.core.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.mh.ta.core.browsers.BrowserType;

@Retention(CLASS)
@Target({ TYPE, METHOD, PARAMETER, CONSTRUCTOR })
public @interface MHTAConfig {
	String baseUri() default "";
	BrowserType browser() default BrowserType.CHROME;
	
}
