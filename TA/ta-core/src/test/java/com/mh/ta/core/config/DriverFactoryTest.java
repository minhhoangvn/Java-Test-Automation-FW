
package com.mh.ta.core.config;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.mh.ta.core.config.DriverFactory;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.utils.TestUtils;

/**
 * @author minhhoang
 *
 */
class DriverFactoryTest {
	static DriverFactory factory;
	static DriverFactory factoryA;
	static DriverFactory factoryB;

	@Test(invocationCount = 1000, threadPoolSize = 100)
	public void testSingletonInstance() throws InterruptedException, ExecutionException {
		Runnable stubTestA = () -> {
			factoryA = DriverFactory.instance();
		};
		Runnable stubTestB = () -> {
			factoryB = DriverFactory.instance();
		};

		Runnable stubTestC = () -> {
			factory = DriverFactory.instance();
		};

		TestUtils.startRunStubTest(stubTestA, stubTestB, stubTestC);

		Assert.assertTrue(factoryA == factoryB);
		Assert.assertTrue(factoryA == factory);
		Assert.assertTrue(factory == factoryB);
	}

	@Test(expectedExceptions = TestContextException.class, expectedExceptionsMessageRegExp = ".*Call createDriver method before can get driver, or set enableAPITest annotation in @EnableOneTimeConfig to false.*")
	public void testThrowExceptionWhenNotStartDriver() {
		DriverFactory.instance().getDriver();
	}
}
