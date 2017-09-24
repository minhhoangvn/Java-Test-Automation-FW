
package com.mh.ta.core.config;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mh.ta.core.base.BaseWebTestNG;
import com.mh.ta.core.config.TestClassFactory;
import com.mh.ta.core.config.annotation.EnableOneTimeConfig;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
class TestClassFactoryTest {
	private static TestClassFactory testClassFactory;
	private final static String regxExceptionMsg = ".*Suite may contains difference config in @EnableOneTimeConfig.*Suite may contains multiple test classes in running mode.*";

	@BeforeMethod
	public void init() {
		testClassFactory = new TestClassFactory();
	}

	@Test
	public void testCanCreateNewTestClass() throws NoSuchMethodException, SecurityException {

		StubClass stub = (StubClass) testClassFactory.newInstance(StubClass.class.getConstructor(new Class[] {}),
				new Object[] {});
		Assert.assertEquals(stub.getClass(), StubClass.class);
	}

	@Test
	public void testCanCreateNewNestedTestClass() throws NoSuchMethodException, SecurityException {

		ChildStubClass stub = (ChildStubClass) testClassFactory
				.newInstance(ChildStubClass.class.getConstructor(new Class[] {}), new Object[] {});
		Assert.assertEquals(stub.getClass(), ChildStubClass.class);
	}

	@Test
	public void testCanCreateNewTestClassWithAnnotation() throws NoSuchMethodException, SecurityException {
		ChildStubA stub = (ChildStubA) testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}),
				new Object[] {});
		Assert.assertEquals(stub.getClass(), ChildStubA.class);
	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithRunningMultipleOneTimeConfigClass() throws Exception {
		testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}), new Object[] {});

	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithDifferenceEnableAPI() throws NoSuchMethodException, SecurityException {
		testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubB.class.getConstructor(new Class[] {}), new Object[] {});
	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithRunningDiffrenceEnableOneTimeConfig() throws Exception {
		testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubC.class.getConstructor(new Class[] {}), new Object[] {});

	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithRunningDiffrenceTestAllAnnotationValueSetA() throws Exception {
		testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubD.class.getConstructor(new Class[] {}), new Object[] {});

	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithRunningDiffrenceTestAllAnnotationValueSetB() throws Exception {
		testClassFactory.newInstance(ChildStubB.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubC.class.getConstructor(new Class[] {}), new Object[] {});

	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithRunningDiffrenceOneTimeValue() throws Exception {
		testClassFactory.newInstance(ChildStubB.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubD.class.getConstructor(new Class[] {}), new Object[] {});

	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = regxExceptionMsg)
	public void testThrowExceptionWithRunningDiffrenceAPIValue() throws Exception {
		testClassFactory.newInstance(ChildStubC.class.getConstructor(new Class[] {}), new Object[] {});
		testClassFactory.newInstance(ChildStubD.class.getConstructor(new Class[] {}), new Object[] {});

	}

	@Test
	public void testenableApiIsTrue() throws Exception {
		testClassFactory.newInstance(ChildStubA.class.getConstructor(new Class[] {}), new Object[] {});
		Assert.assertTrue(TestClassFactory.isEnableAPITest());
	}

	@Test
	public void testenableApiIsFalse() throws Exception {
		testClassFactory.newInstance(ChildStubB.class.getConstructor(new Class[] {}), new Object[] {});
		Assert.assertFalse(TestClassFactory.isEnableAPITest());
	}

	@Test(expectedExceptions = TestContextException.class)
	public void testThrowExceptionWithFieldConstructor() throws Exception {
		testClassFactory.newInstance(ChildStubE.class.getDeclaredConstructor(new Class[] { String.class }),
				new Object[] { 1 });
	}

	static class StubClass extends BaseWebTestNG {
		public StubClass() {

		}
	}

	static class ChildStubClass extends StubClass {
		public ChildStubClass() {

		}
	}

	@EnableOneTimeConfig(enable = true, enableAPITest = true)
	static class ChildStubA extends StubClass {
		public ChildStubA() {
		}
	}

	@EnableOneTimeConfig(enable = true, enableAPITest = false)
	static class ChildStubB extends StubClass {
		public ChildStubB() {
		}
	}

	@EnableOneTimeConfig(enable = false, enableAPITest = true)
	static class ChildStubC extends StubClass {
		public ChildStubC() {
		}
	}

	@EnableOneTimeConfig(enable = false, enableAPITest = false)
	static class ChildStubD extends StubClass {
		public ChildStubD() {
		}
	}

	static class ChildStubE extends BaseWebTestNG {

		public ChildStubE(String stubField) {
		}
	}

}
