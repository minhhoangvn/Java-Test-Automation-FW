
package com.mh.ta.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.mh.ta.core.exception.TestContextException;


public class DateTimeHelper {
	private DateTimeHelper() {
		throw new IllegalStateException("DateTimeHelper class");
	}

	public static String getFutureDateStringFormat(String format) {
		LocalDate futureDate = LocalDate.now().plusMonths(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return futureDate.format(formatter);
	}

	public static Date convertDateStringToDate(String dateString, String dateFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			return format.parse(dateString);
		} catch (ParseException e) {
			throw new TestContextException("Not matching for date string and date format for converting to Date. ["
					+ dateString + "] format [" + dateFormat + "]");

		}
	}

	public static String convertDateToStringFormat(Date date, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}

	public static String changeDateFormat(Date date, String newFormat) {
		SimpleDateFormat format = new SimpleDateFormat(newFormat);
		return format.format(date);
	}

	public static boolean compareDateBefore(Date date, Date dateToCompare) {
		int greateOrEqualDate = 1;
		return date.compareTo(dateToCompare) >= greateOrEqualDate;
	}

	public static boolean compareDateAfter(Date date, Date dateToCompare) {
		int lessOrEqualDate = 0;
		return date.compareTo(dateToCompare) < lessOrEqualDate;
	}
}
