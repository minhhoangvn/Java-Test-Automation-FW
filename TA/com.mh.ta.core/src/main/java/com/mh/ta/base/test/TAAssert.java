package com.mh.ta.base.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TAAssert {
	public static void textShouldMatch(String actualText, String expectedText) {
		if (!actualText.equals(expectedText))
			throw new AssertionError(
					"Expected text is " + expectedText + " is not matched with Actual text " + actualText);
	}

	public static <T> void numberShouldEquals(T actualNumber, T expectedNumber) {
		if (actualNumber != expectedNumber)
			throw new AssertionError(
					"Expected number is " + actualNumber + " is not equals with Actual number " + expectedNumber);
	}

	public static void booleanShouldTrue(boolean value, String msg) {
		if (value != true)
			throw new AssertionError("Expected boolean value is true but get false. " + msg);
	}

	public static <T> void listTextValueShouldEqual(List<T> actualList, List<T> expectedList) {
		int actualListSize = actualList.size();
		int expectedListSize = expectedList.size();
		List<List<T>> differenceItem = new ArrayList<List<T>>();
		if (actualListSize != expectedListSize)
			throw new AssertionError("Actual list has difference size with Expected list. Actual list [" + (actualList)
					+ "] and Expected list [" + expectedList + "]");
		for (int count = 0; count < expectedListSize; count++) {
			if (actualList.get(count).toString() == (expectedList.get(count).toString())) {
				System.err.println(actualList.get(count).toString());
				System.err.println(expectedList.get(count).toString());
				differenceItem.add(Arrays.asList(actualList.get(count), expectedList.get(count)));
			}
		}
		if (differenceItem.size() > 0)
			throw new AssertionError(
					"Actual list and Expected list has differnce item. [" + Arrays.toString(differenceItem.toArray()));
	}
}
