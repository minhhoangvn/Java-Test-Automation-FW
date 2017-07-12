package com.mh.ta.utils;

import org.testng.annotations.Test;

public class TestB {
	@Test
	public void B1() {
		System.out.println("B Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void B2() {
		System.out.println("B Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void B3() {
		System.out.println("B Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void B4() {
		System.out.println("B Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
