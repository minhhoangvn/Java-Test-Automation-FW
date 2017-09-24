
package com.mh.ta.core.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author minhhoang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE ,ElementType.FIELD})
public @interface HighLightElement {
	public String borderColor() default "red";

	public String backgroundColor() default "yellow";

	public int timeoutInMs() default (200);
}
