
package com.mh.ta.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.utils.TestUtils;

/**
 * @author minhhoang
 *
 */
class GuiceInjectFactoryTest {
	static GuiceInjectFactory injectFactoryA;
	static GuiceInjectFactory injectFactoryB;
	static GuiceInjectFactory injectFactory;
	static Object stubInstanceObject;
	private final String storedModuleFieldName = "listInjectedModules";
	@Inject
	int stubInjectNumber;

	@Test(invocationCount = 100, threadPoolSize = 100, successPercentage = 100)
	public void testGuiceInjectSingleton() {
		// Arrange
		Injector injectA = GuiceInjectFactory.instance().getInject();

		// Act
		Thread.yield();
		Injector injectB = GuiceInjectFactory.instance().getInject();

		// Assert
		Assert.assertEquals(injectA, injectB);
	}

	@Test
	public void testInstanceMultipleThread() throws InterruptedException, ExecutionException {
		// Arrange

		Runnable stubTestA = () -> {
			injectFactoryA = GuiceInjectFactory.instance();
		};
		Runnable stubTestB = () -> {
			injectFactoryB = GuiceInjectFactory.instance();
		};

		// Act

		TestUtils.startRunStubTest(stubTestA, stubTestB);

		Assert.assertTrue(injectFactoryA != injectFactoryB);
	}

	@Test
	public void testCanInjectToClass() throws InterruptedException, ExecutionException {
		// Arrange
		Runnable stubTestA = () -> {
			injectFactoryA = GuiceInjectFactory.instance();
			injectFactoryA.createInject(stubModule());
			injectFactoryA.injectToClass(this);
		};

		// Act
		TestUtils.startRunStubTest(stubTestA);

		// Assert
		Assert.assertTrue(stubInjectNumber == 1);
	}

	@Test
	public void testInstanceObject() throws InterruptedException, ExecutionException {
		// Arrange
		Runnable stubTestA = () -> {
			injectFactoryA = GuiceInjectFactory.instance();
			stubInstanceObject = injectFactoryA.createObjectInstance(StubClass.class);
		};

		// Act
		TestUtils.startRunStubTest(stubTestA);

		// Assert
		Assert.assertTrue(stubInstanceObject.getClass() == StubClass.class);
	}

	@Test
	public void testGetObjectInstanceInContainer() throws InterruptedException, ExecutionException {

		// Arrange

		Runnable stubTestA = () -> {
			injectFactoryA = GuiceInjectFactory.instance();
			injectFactoryA.createInject(stubModuleBindAnnotation());
			stubInstanceObject = injectFactoryA.getInstanceObjectInject(Integer.class, StubAnnotation.class);
		};

		// Act

		TestUtils.startRunStubTest(stubTestA);

		// Assert

		Assert.assertTrue((int) stubInstanceObject == 2);
	}

	@Test
	public void testReturnNullWihObjectNotInstanceInContainer() throws InterruptedException, ExecutionException {
		// Arrange

		Runnable stubTestA = () -> {
			injectFactoryA = GuiceInjectFactory.instance();
			stubInstanceObject = injectFactoryA.getInstanceObjectInject(StubClassBinding.class,
					ApplicationConfig.class);
		};

		// Act

		TestUtils.startRunStubTest(stubTestA);

		// Assert

		Assert.assertTrue(stubInstanceObject == null);

	}

	@Test
	public void testStoreModule() throws InterruptedException, ExecutionException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		// Arrange

		Runnable stubTestA = () -> {
			GuiceInjectFactory.instance().createInject(new MainConfigModule());
			GuiceInjectFactory.instance().createInject(new StubModuleA());
		};

		Runnable stubTestB = () -> {
			GuiceInjectFactory.instance().createInject(new MainConfigModule());
			GuiceInjectFactory.instance().createInject(new StubModuleA());
		};

		Runnable stubTestC = () -> {
			GuiceInjectFactory.instance().createInject(new MainConfigModule());
			GuiceInjectFactory.instance().createInject(new StubModuleA());
			GuiceInjectFactory.instance().createInject(new StubModuleB());
			injectFactory = GuiceInjectFactory.instance();
		};
		// Act

		TestUtils.startRunStubTest(stubTestA);
		TestUtils.startRunStubTest(stubTestB);
		TestUtils.startRunStubTest(stubTestC);
		Set<String> listActualModuleName = getListModuleStored();

		// Assert

		assertThat(listActualModuleName).hasSize(3).doesNotContain("MainConfigModule").contains("StubModuleA")
				.contains("StubModuleB").contains("");

	}	

	@SuppressWarnings("unchecked")
	private Set<String> getListModuleStored()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = GuiceInjectFactory.class.getDeclaredField(storedModuleFieldName);
		field.setAccessible(true);
		Map<String, Module> listInjectedModules = (Map<String, Module>) field.get(injectFactory);
		return listInjectedModules.keySet();
	}

	private static class StubClass {
	}

	private Module stubModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(Integer.class).toInstance(1);
			}
		};
	}

	private Module stubModuleBindAnnotation() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(Integer.class).annotatedWith(StubAnnotation.class).toInstance(2);
			}
		};
	}

	@BindingAnnotation
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
	public @interface StubAnnotation {

	}

	private static class StubClassBinding {
	}

	private static class MainConfigModule extends AbstractModule {

		@Override
		protected void configure() {

		}

	}

	private static class StubModuleA extends AbstractModule {

		@Override
		protected void configure() {

		}

	}

	private static class StubModuleB extends AbstractModule {

		@Override
		protected void configure() {

		}

	}
}
