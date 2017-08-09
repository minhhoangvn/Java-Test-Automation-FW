package com.mh.ta.factory;

import com.mh.ta.keywords.web.WebKeywords;

public class ActionKeywords {
	private static final WebKeywords WebKw = GuiceInjectFactory.instance().getObjectInstance(WebKeywords.class);

	public static WebKeywords WebUi() {
		return WebKw;
	}
}
