package com.mh.ta.enums;

public enum TestStatus {
	PASSED(1), FAILED(2), SKIPED(3), ALL(4);

	private int statusCode;

	private TestStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	public static TestStatus getTestStatus(int statusCode) {
		for (TestStatus status : TestStatus.values()) {
			if (statusCode == status.statusCode)
				return status;
		}
		throw new IllegalArgumentException("Not valid status code");
	}

	public static int getTestStatusCode(TestStatus testStauts) {
		for (TestStatus status : TestStatus.values()) {
			if (testStauts == status){
				return status.statusCode;}
		}
		throw new IllegalArgumentException("Not valid status code");
	}
}
