package com.mh.ta.keywords;

import static java.lang.String.format;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.mh.ta.core.annotation.HighLightElement;
import com.mh.ta.factory.ActionKeywords;
import com.mh.ta.factory.WebDriverFactory;

public class KeywordInterceptor implements MethodInterceptor {
	private final String webElementClassName = "RemoteWebElement";

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		HighLightElement highlight = invocation.getMethod().getAnnotation(HighLightElement.class);
		for (Object arg : invocation.getArguments()) {
			if (arg.getClass().getSimpleName().equals(webElementClassName))
				this.highlightElement((WebElement) arg, highlight);
		}
		return invocation.proceed();

	}

	private void highlightElement(WebElement element, HighLightElement highlight) {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getDriver();
		String orig = element.getAttribute("style");
		js.executeScript(format("arguments[0].setAttribute('%s',arguments[1]);", "style"), element, format(
				"border: 3px solid %s; background-color: %s;", highlight.borderColor(), highlight.backgroundColor()));
		ActionKeywords.WebUI().sleepInMilliseconds(highlight.timeoutInMs());
		js.executeScript(format("arguments[0].setAttribute('%s',arguments[1]);", "style"), element, orig);
	}
}
