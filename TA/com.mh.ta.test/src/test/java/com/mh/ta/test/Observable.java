package com.mh.ta.test;

public interface Observable {
	public void notifyUpdate();

	public void attachObject(Observers obj);

	public void detachObject(Observers obj);

}
