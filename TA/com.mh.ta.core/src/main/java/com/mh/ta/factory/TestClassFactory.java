package com.mh.ta.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.testng.IObjectFactory;
import org.testng.internal.ObjectFactoryImpl;

import com.mh.ta.core.annotation.EnableOneTimeConfig;
import com.mh.ta.core.exception.TestContextException;

public class TestClassFactory implements IObjectFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectFactoryImpl creator = new ObjectFactoryImpl();

	@SuppressWarnings("rawtypes")
	@Override
	public Object newInstance(Constructor constructor, Object... params) {
		try {
			Object testClass = creator.newInstance(constructor, params);
			Class baseTestClass = getBaseClass(testClass.getClass());
			setUpBaseTestConfig(testClass, baseTestClass);
			return testClass;
		} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
			throw new TestContextException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private Class getBaseClass(Class cls) {
		if (cls.getSuperclass().getSimpleName().equals("BaseWebTestNG"))
			return cls.getSuperclass();
		else
			return getBaseClass(cls.getSuperclass());
	}

	@SuppressWarnings("rawtypes")
	private void setUpBaseTestConfig(Object currentTestClass, Class baseTestClass)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		EnableOneTimeConfig testConfig = currentTestClass.getClass().getAnnotation(EnableOneTimeConfig.class);
		if (testConfig != null) {
			Field configOneTime = baseTestClass.getDeclaredField("configOneTime");
			Field enableAPITest = baseTestClass.getDeclaredField("enableAPITest");
			configOneTime.setAccessible(true);
			enableAPITest.setAccessible(true);
			configOneTime.set(currentTestClass, testConfig.enable());
			enableAPITest.set(currentTestClass, testConfig.enableAPITest());
		}
	}
}
