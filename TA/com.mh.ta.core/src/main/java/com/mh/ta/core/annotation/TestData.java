package com.mh.ta.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mh.ta.utils.DataSourceType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface TestData {
	public DataSourceType dataType() default DataSourceType.CSV;

	public String fileName();

	public String sheetName() default "";
}
