
package com.mh.ta.core.exception;

/**
 * @author minhhoang
 *
 */
public class InstanceWebDriverException extends RuntimeException {
	private static final long serialVersionUID = 0L;

	public InstanceWebDriverException(String message) {
		super(message);
	}

	public InstanceWebDriverException(String message, Throwable cause) {
		super(message, cause);
	}

	public InstanceWebDriverException(Throwable cause) {
		super(cause);
	}

	public InstanceWebDriverException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
