package com.mh.ta.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import com.mh.ta.core.browsers.BrowserType;

public class TestBrowserTypeEnum {
	@Test
	public void testValidGetPackageName() {
		String expectedPackageName = "com.mh.ta.core.webdriver.ChromeBrowser";
		String actualPackageName = BrowserType.getBrowser("chrome");
		System.out.println(actualPackageName);
		// assertEquals(actualPackageName, expectedPackageName);
		String browser = "chrome";
		
		String st = "chrome";
		String temp1 = "Browser";
		String temp = IntStream.concat(IntStream.of(st.codePointAt(0)).map(Character::toUpperCase), st.codePoints().skip(1))
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		System.out.println(temp);

		System.out.println(IntStream.concat(temp.codePoints(), temp1.codePoints())
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
		
		List<Integer> a = Arrays.asList(1,2,3,4);
		a.forEach(n-> System.out.println(n));
		
	}
}
