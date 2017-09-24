
package com.mh.ta.core.config.driver.services.internal;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.Annotations;

import com.mh.ta.core.config.annotation.FindByUntil;
import com.mh.ta.core.config.enums.WaitUntilType;

/**
 * @author minhhoang
 *
 */
public class AnnotationsExtend extends Annotations {

	public AnnotationsExtend(Field field) {
		super(field);
	}

	@Override
	public By buildBy() {
		assertValidAnnotations();

		By ans = null;

		FindBys findBys = this.getField().getAnnotation(FindBys.class);
		if (findBys != null) {
			ans = buildByFromFindBys(findBys);
		}

		FindAll findAll = this.getField().getAnnotation(FindAll.class);
		if (ans == null && findAll != null) {
			ans = buildBysFromFindByOneOf(findAll);
		}

		FindBy findBy = this.getField().getAnnotation(FindBy.class);
		if (ans == null && findBy != null) {
			ans = buildByFromFindBy(findBy);
		}
		FindByUntil findByUntil = this.getField().getAnnotation(FindByUntil.class);
		if (ans == null && findByUntil != null) {
			ans = buildByFromFindByUntil(findByUntil);
		}

		if (ans == null) {
			ans = buildByFromDefault();
		}

		if (ans == null) {
			throw new IllegalArgumentException("Cannot determine how to locate element " + this.getField());
		}

		return ans;
	}

	public boolean isFindByUntil() {
		return getField().getAnnotation(FindByUntil.class) != null;
	}

	public int getTimeOut() {
		if (isFindByUntil()) {
			FindByUntil findByUntil = this.getField().getAnnotation(FindByUntil.class);
			return findByUntil.timeOut();
		}
		return 0;
	}

	public WaitUntilType getWaitType() {
		if (isFindByUntil()) {
			FindByUntil findByUntil = this.getField().getAnnotation(FindByUntil.class);
			return findByUntil.waitType();
		}
		return null;
	}

	protected By buildByFromFindByUntil(FindByUntil findByUntil) {
		assertValidFindByUntil(findByUntil);
		By ans = buildByFromShortFindByUntil(findByUntil);
		if (ans == null) {
			ans = buildByFromLongFindByUntil(findByUntil);
		}

		return ans;
	}

	private void assertValidFindByUntil(FindByUntil findByUntil) {
		if (findByUntil.how() != null && findByUntil.using() == null) {
			throw new IllegalArgumentException("If you set the 'how' property, you must also set 'using'");
		}

	}

	private By buildByFromShortFindByUntil(FindByUntil findByUntil) {
		if (!"".equals(findByUntil.className()))
			return By.className(findByUntil.className());

		if (!"".equals(findByUntil.css()))
			return By.cssSelector(findByUntil.css());

		if (!"".equals(findByUntil.id()))
			return By.id(findByUntil.id());

		if (!"".equals(findByUntil.linkText()))
			return By.linkText(findByUntil.linkText());

		if (!"".equals(findByUntil.name()))
			return By.name(findByUntil.name());

		if (!"".equals(findByUntil.partialLinkText()))
			return By.partialLinkText(findByUntil.partialLinkText());

		if (!"".equals(findByUntil.tagName()))
			return By.tagName(findByUntil.tagName());

		if (!"".equals(findByUntil.xpath()))
			return By.xpath(findByUntil.xpath());

		// Fall through
		return null;
	}

	private By buildByFromLongFindByUntil(FindByUntil findByUntil) {
		How how = findByUntil.how();
		String using = findByUntil.using();

		switch (how) {
		case CLASS_NAME:
			return By.className(using);

		case CSS:
			return By.cssSelector(using);

		case ID:
		case UNSET:
			return By.id(using);

		case ID_OR_NAME:
			return new ByIdOrName(using);

		case LINK_TEXT:
			return By.linkText(using);

		case NAME:
			return By.name(using);

		case PARTIAL_LINK_TEXT:
			return By.partialLinkText(using);

		case TAG_NAME:
			return By.tagName(using);

		case XPATH:
			return By.xpath(using);

		default:
			// Note that this shouldn't happen (eg, the above matches all
			// possible values for the How enum)
			throw new IllegalArgumentException("Cannot determine how to locate element ");
		}
	}
}
