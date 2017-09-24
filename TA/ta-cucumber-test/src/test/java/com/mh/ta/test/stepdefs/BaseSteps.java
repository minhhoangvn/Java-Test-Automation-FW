/**
 * @author minhhoang
 *
 *
 */
package com.mh.ta.test.stepdefs;

import com.google.inject.Inject;
import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.pages.search.SearchPage;

/**
 * BaseSteps
 *
 */
public class BaseSteps {
	public BaseSteps() {
		GuiceInjectFactory.instance().injectToClass(this);
	}
	
	@Inject
	protected SearchPage searchPage;
}
