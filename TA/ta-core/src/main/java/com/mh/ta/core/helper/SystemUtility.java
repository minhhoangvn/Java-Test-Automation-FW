
package com.mh.ta.core.helper;

/**
 * @author minhhoang
 *
 */
public class SystemUtility {
	private SystemUtility() {
		throw new IllegalStateException("System Utility class");
	}

	public static String getOsName() {
		return System.getProperty("os.name").toLowerCase();
	}
	
	public static void sleep(int timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (Exception ignore) {
			/**
			 * ignore sleep
			 */
		}
	}
}
