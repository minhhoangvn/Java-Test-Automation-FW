
package com.mh.ta.core.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author minhhoang
 *
 */
public class DateTimeUtility {
	private DateTimeUtility() {
		throw new IllegalStateException("Utility class");
	}

	public static String getDateStringFormat(String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
}
