package com.mh.ta.core.driver;

public interface Driver<Type> {
	public Type startDriver(Object capabilities, Object options);

}
