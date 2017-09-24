
package com.mh.ta.core.config;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mh.ta.core.report.logger.LoggerManager;

/**
 * @author minhhoang
 *
 */
public class LoggerFactory implements Serializable {

	private static final long serialVersionUID = 1L;
	private static LoggerFactory instance;

	private LoggerFactory() {
		LoggerManager.instance();
	}

	public static synchronized LoggerFactory instance() {
		if (instance == null)
			instance = new LoggerFactory();
		return instance;
	}

	public Logger createClassLogger(Class<?> cls) {
		return LogManager.getLogger(cls);
	}
}
