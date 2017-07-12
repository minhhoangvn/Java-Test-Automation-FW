package com.mh.ta.utils;

import org.testng.annotations.Test;

public class TestA {
	@Test
	public void A1() {
		System.out.println("A Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void A2() {
		System.out.println("A Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void A3() {
		System.out.println("A Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void A4() {
		System.out.println("A Name: " + Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
