package com.mh.ta.interfaces.driver;

import java.util.Set;

public interface IWindows {
	public String getCurrentWindowsHandle();

	public Set<String> getListWindowsHandle();

	public void setWindowsSize(int width, int height);

	public void maximizeWindows();

	public void hiddenWindows();
}
