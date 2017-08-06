package com.mh.ta.factory;

import com.mh.ta.keywords.TAWebKeywords;

public class ActionKeywords {
	private static final TAWebKeywords WebKw = GuiceInjectFactory.instance().getObjectInstance(TAWebKeywords.class);

	public static TAWebKeywords WebUi() {
		return WebKw;
	}
}
