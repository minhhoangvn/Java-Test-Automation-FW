package com.mh.ta.core.exception;

public class InstantiationPageObjectException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	public InstantiationPageObjectException(String message) {
		super(message);
	}

	public InstantiationPageObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public InstantiationPageObjectException(Throwable cause) {
		super(cause);
	}

	public InstantiationPageObjectException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
