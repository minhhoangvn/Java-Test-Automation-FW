package com.mh.ta.core.exception;

public class TestContextException extends RuntimeException {
	private static final long serialVersionUID = 0L;

	public TestContextException(String message) {
		super(message);
	}

	public TestContextException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestContextException(Throwable cause) {
		super(cause);
	}

	public TestContextException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
