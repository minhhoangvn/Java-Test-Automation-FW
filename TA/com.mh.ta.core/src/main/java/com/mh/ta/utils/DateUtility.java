package com.mh.ta.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	public static String getCurrentDateString(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}
}
