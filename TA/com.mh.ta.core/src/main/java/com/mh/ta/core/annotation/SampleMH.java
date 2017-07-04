package com.mh.ta.core.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(CLASS)
@Target({ TYPE, METHOD, PARAMETER, CONSTRUCTOR })
public @interface SampleMH {
	public enum Priority {L,M,H}
	public enum Status {S,H,D,P}
	String configFileName() default "application.yml";
	Priority priority() default Priority.H;
	Status status() default Status.H;
	
}
