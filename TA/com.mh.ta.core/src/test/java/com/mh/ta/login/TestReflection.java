package com.mh.ta.login;

public class TestReflection {
	public TestReflection() {
		System.out.println("Default constructor");
	}

	public TestReflection(int a, long b) {
		System.out.println("Two parameter constructor : int,long => " + a + ":" + b);
	}

	public TestReflection(int a, long b, String c) {
		System.out.println("Three parameter constructor : int,long,String => " + a + ":" + b + ":" + c);
	}

	public TestReflection(String text, int count) {
		System.out.println("Two parameter constructor : String, int => " + text + ":" + count );
	}

}
