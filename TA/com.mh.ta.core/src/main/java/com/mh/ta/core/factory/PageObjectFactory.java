package com.mh.ta.core.factory;

import java.lang.reflect.InvocationTargetException;

import com.google.inject.Injector;
import com.mh.ta.core.exception.InstantiationPageObjectException;
import com.mh.ta.page.BaseElements;
import com.mh.ta.page.BasePage;
import com.mh.ta.page.BaseValidations;

public class PageObjectFactory {

	public static <PageObject extends BasePage<?, ?>, Elements extends BaseElements, Validations extends BaseValidations<? extends BaseElements>> PageObject getPageObject(
			Class<PageObject> pageObjectClass, Class<Elements> clsElements, Class<Validations> clsValidations) {
		try {
			return (PageObject) pageObjectClass
					.getDeclaredConstructor(new Class[] { clsElements.getClass(), clsValidations.getClass() })
					.newInstance(clsElements, clsValidations);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InstantiationPageObjectException("Error in init page object ", e, true, true);
		}
	}

	public static <E extends BasePage<?, ?>> E initPageObject(Injector inject, Class<E> cls) {
		return inject.getInstance(cls);
	}
}
