package com.mh.ta.core.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {

	public static String getDateStringFormat(String dateFormat){
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
}
