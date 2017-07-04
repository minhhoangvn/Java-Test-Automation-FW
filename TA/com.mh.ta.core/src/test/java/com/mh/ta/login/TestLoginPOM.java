package com.mh.ta.login;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mh.ta.core.annotation.SampleMH;

public class TestLoginPOM {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		Class cls = Sample.class;
		//Object obj = cls.getDeclaredConstructor((TestLoginPOM.class)).newInstance(TestLoginPOM.class.newInstance());
		for (Method m : cls.getMethods()) {
			SampleMH a = (SampleMH) m.getAnnotation(SampleMH.class);
			System.err.println(m.getAnnotation(SampleMH.class));
			if (a != null) {
				System.err.println("=========================");
				System.out.println(m.getName());
				System.out.println(a.priority());
				System.out.println(a.status());
				System.out.println(a.configFileName());
				System.err.println("=========================");
				
			} else {
				System.out.println("Test Show Method Without annotations");
				System.out.println(m.getName());
			}
		}
	}

	class Sample {
		public Sample() {
			super();
		}

		public void completedMethod() {
			System.out.println("this is method complete");
		}

		@SampleMH(priority = SampleMH.Priority.H)
		public void notYetStarted() {

		}

		@SampleMH(priority = SampleMH.Priority.H, status = SampleMH.Status.D)
		public void DoneStarted() {

		}

		@SampleMH(priority = SampleMH.Priority.H, status = SampleMH.Status.S)
		public void notDoneStarted() {

		}
	}
}
