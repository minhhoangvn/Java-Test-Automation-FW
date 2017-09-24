/**
 * @author minhhoang
 *
 *
 */
package com.mh.ta.test;

import com.google.inject.Inject;
import com.mh.ta.core.base.BaseWebTestNG;
import com.mh.ta.pages.search.SearchPage;

/**
 * BaseTest
 *
 */
public class BaseTest extends BaseWebTestNG {
	@Inject
	SearchPage searchPage;
}
