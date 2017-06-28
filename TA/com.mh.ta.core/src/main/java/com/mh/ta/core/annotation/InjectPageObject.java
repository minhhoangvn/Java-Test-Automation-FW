package com.mh.ta.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.mh.ta.core.config.TestRunningConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ TestRunningConfig.class })
public @interface InjectPageObject {
	String[] basePackages() default "";
}
