package com.mh.ta.login;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mh.ta.core.factory.DriverFactory;

public class TestLoginPOM {
	@Before
	public void initDriver() {
		//DriverFactory.startDriver("Start Chrome Driver");
	}

	@Test
	public void testDemo() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		/*
		 * LoginPage login = new LoginPage(LoginElements::new, () -> new
		 * LoginValidations(LoginElements::new)); login.goToLoginPage();
		 * login.inputEmail(); login.clickNext(); Boolean check =
		 * login.Validations().shouldShowPassowdField();
		 * System.err.println("Test Login Check " + check);
		 */
		// Class cls = TestReflection.class;
		// TestReflection s = (TestReflection) cls.getDeclaredConstructor(new
		// Class[] { String.class, int.class })
		// .newInstance("Test Minh Hoang", 69696);
		LoginPage login = new LoginPage(LoginElements.class, LoginValidations.class);
		System.err.println(login.goToLoginPage().inputEmail().clickNext().Validations().shouldShowPassowdField());
		System.err.println(login.Validations().shouldShowPassowdField());

	}

	@After
	public void diposeDriver() {
		DriverFactory.diposeDriver();
	}

	class Sample {
		private String test;
		private int count;

		public Sample(String test, int count) {
			this.test = test;
			this.count = count;
		}

		protected String getTest() {
			return test;
		}

		protected void setTest(String test) {
			this.test = test;
		}

		protected int getCount() {
			return count;
		}

		protected void setCount(int count) {
			this.count = count;
		}

	}
}
