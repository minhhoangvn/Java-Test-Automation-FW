package com.mh.ta.interfaces.driver;

import java.util.Set;

import org.openqa.selenium.Cookie;

public interface IWebStorage {
	public void clearAllCookie();

	public Cookie getCookieValue(String key);

	public Set<Cookie> getAllCookieValue();

	public void setCookieValue(String key, String value);

	public String getLocalStorage();

	public void setLocalStorage(String key);
}
