
package com.mh.ta.core.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openqa.selenium.support.How;

import com.mh.ta.core.config.enums.WaitUntilType;
/**
 * Using for find element with explicit wait or sleep delay
 * @author minhhoang
 * timeOut in second
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface FindByUntil {
	How how() default How.UNSET;

	int timeOut() default 30;

	WaitUntilType waitType();

	String using() default "";

	String id() default "";

	String name() default "";

	String className() default "";

	String css() default "";

	String tagName() default "";

	String linkText() default "";

	String partialLinkText() default "";

	String xpath() default "";
}
