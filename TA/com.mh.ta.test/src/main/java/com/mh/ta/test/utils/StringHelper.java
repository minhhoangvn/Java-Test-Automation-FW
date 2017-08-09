package com.mh.ta.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.mh.ta.core.exception.TestContextException;

public class StringHelper {
	public static List<String> getStringByRegex(String source, String pattern) {
		try {
			List<String> matched = new ArrayList<>();
			Pattern regex = Pattern.compile(pattern);
			Matcher match = regex.matcher(source);
			while (match.find()) {
				matched.add(match.group());
			}
			return matched;
		} catch (PatternSyntaxException e) {
			throw new TestContextException("Regex pattern is not correct");
		}
	}
}
