package com.mh.ta.factory;

import com.mh.ta.keywords.WebKeywords;

public class ActionKeywords {
	private static final WebKeywords webKW = GuiceInjectFactory.instance().getObjectInstance(WebKeywords.class);

	public static WebKeywords WebUI() {
		return webKW;
	}
}
