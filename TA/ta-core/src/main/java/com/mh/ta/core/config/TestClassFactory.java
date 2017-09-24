
package com.mh.ta.core.config;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.IObjectFactory;
import org.testng.TestNGException;
import org.testng.internal.ObjectFactoryImpl;

import com.mh.ta.core.config.annotation.EnableOneTimeConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.core.helper.Constant;

/**
 * 
 * @author minhhoang
 *
 */

public class TestClassFactory implements IObjectFactory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectFactoryImpl creator = new ObjectFactoryImpl();
	private static boolean enableAPITest;

	private boolean initConfigOneTime;
	private boolean initConfigAPI;
	private boolean initConfig;
	private List<String> testClassConfigInformation;
	private Logger log = LoggerFactory.instance().createClassLogger(getClass());

	public TestClassFactory() {		
		testClassConfigInformation = new ArrayList<>();
		initConfigOneTime = false;
		initConfigAPI = false;
		initConfig = false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object newInstance(Constructor constructor, Object... params) {
		try {
			Object testClass = creator.newInstance(constructor, params);
			Class baseTestClass = getBaseClass(testClass.getClass());
			setUpBaseTestConfig(testClass, baseTestClass);
			log.info("Create test class " + testClass.getClass().getName());
			return testClass;
		} catch (TestNGException | SecurityException | IllegalArgumentException | NoSuchFieldException
				| IllegalAccessException e) {
			log.error("Error in create test class. ", e.getMessage(), e.getCause());
			throw new TestContextException("Error in create test class. Open log file for details");
		}
	}

	@SuppressWarnings("rawtypes")
	private Class getBaseClass(Class cls) {
		try {
			Class superClass = cls.getSuperclass();
			String superClassName = superClass.getSimpleName();
			boolean isBaseWebTestNG = superClassName.equals(Constant.BASE_TESTNG_WEB_TEST);
			boolean isBaseCucumberTest = superClassName.equals(Constant.BASE_TESTNG_CUCUMBER_TEST);
			if (isBaseWebTestNG || isBaseCucumberTest)
				return cls.getSuperclass();
			else
				return getBaseClass(cls.getSuperclass());
		} catch (NullPointerException e) {
			log.error("Has error in get base test class. ", e.getMessage(), e.getCause());
			throw new TestContextException("Has error in get base test class.");
		}
	}

	@SuppressWarnings("rawtypes")
	private void setUpBaseTestConfig(Object currentTestClass, Class baseTestClass)
			throws IllegalAccessException, NoSuchFieldException {
		EnableOneTimeConfig testConfig = currentTestClass.getClass().getAnnotation(EnableOneTimeConfig.class);
		if (testConfig != null) {
			Field configOneTime = baseTestClass.getDeclaredField("configOneTime");
			Field enableAPITestField = baseTestClass.getDeclaredField("enableAPITest");
			configOneTime.setAccessible(true);
			enableAPITestField.setAccessible(true);
			configOneTime.set(currentTestClass, testConfig.enable());
			enableAPITestField.set(currentTestClass, testConfig.enableAPITest());
		}
		validateTestSuitesXML(currentTestClass, testConfig);
	}

	private void validateTestSuitesXML(Object currentTestClass, EnableOneTimeConfig testConfig) {
		EnableOneTimeConfig configAnnotation = testConfig == null ? initDefaultAnnotation() : testConfig;
		configAnnotation.annotationType();
		setInitConfig(currentTestClass, configAnnotation);
		boolean isDifferenceConfigOneTime = initConfigOneTime != configAnnotation.enable();
		boolean isDifferenceConfigAPI = initConfigAPI != configAnnotation.enableAPITest();
		boolean isMultipleTestClass = testClassConfigInformation.size() > 1;
		boolean isInvalidConfingOneTimeSuite = initConfigOneTime && isMultipleTestClass;
		boolean isInvalidTestSuite = isDifferenceConfigOneTime || isDifferenceConfigAPI;
		if (isInvalidTestSuite || isInvalidConfingOneTimeSuite) {
			throwException();
		}
	}

	private EnableOneTimeConfig initDefaultAnnotation() {
		return new EnableOneTimeConfig() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return EnableOneTimeConfig.class;
			}

			@Override
			public boolean enableAPITest() {
				return false;
			}

			@Override
			public boolean enable() {
				return false;
			}
		};
	}

	private void setInitConfig(Object currentTestClass, EnableOneTimeConfig testConfig) {
		if (!initConfig) {
			initConfigAPI = testConfig.enableAPITest();
			initConfigOneTime = testConfig.enable();
			setEnableAPITest(testConfig.enableAPITest());
			initConfig = true;
		}
		testClassConfigInformation
				.add("Test class " + currentTestClass.getClass().getName() + " has @EnableOneTimeConfig with[enable="
						+ testConfig.enable() + ", enableAPITest=" + testConfig.enableAPITest() + "]");
	}

	private static void setEnableAPITest(boolean enableAPITest) {
		TestClassFactory.enableAPITest = enableAPITest;
	}

	public static boolean isEnableAPITest() {
		return enableAPITest;
	}

	private void throwException() {
		log.error(
				"Has error in running suite.\n1/ Suite may contains difference config in @EnableOneTimeConfig, please create new suite file and group by @EnableOneTimeConfig value. "
						+ Arrays.toString(testClassConfigInformation.toArray())
						+ "\n2/ Suite may contains multiple test classes in running mode @EnableOneTimeConfig(enable=true). Please create 1 test class for suite file ");
		throw new TestContextException(
				"Has error in running suite.\n1/ Suite may contains difference config in @EnableOneTimeConfig, please create new suite file and group by @EnableOneTimeConfig value. "
						+ Arrays.toString(testClassConfigInformation.toArray())
						+ "\n2/ Suite may contains multiple test classes in running mode @EnableOneTimeConfig(enable=true). Please create 1 test class for suite file ");
	}
}
