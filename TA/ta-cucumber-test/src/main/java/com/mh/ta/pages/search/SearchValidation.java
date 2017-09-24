/**
 * @author minhhoang
 *
 *
 */
package com.mh.ta.pages.search;

import org.assertj.core.api.Assertions;

import com.google.inject.Inject;
import com.mh.ta.core.base.BaseValidation;


public class SearchValidation extends BaseValidation{
	
	@Inject
	SearchPage search;

	public void shouldContainsSearchDataInPageTitle(String expected) {
		Assertions.assertThat(search.getSearchPageTitle()).contains(expected);
	}
}
