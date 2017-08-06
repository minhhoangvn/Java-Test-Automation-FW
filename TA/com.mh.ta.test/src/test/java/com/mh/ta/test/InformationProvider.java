package com.mh.ta.test;

import com.google.inject.Provider;

public class InformationProvider implements Provider<Information> {

	String info;

	public InformationProvider() {
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public Information get() {
		// TODO Auto-generated method stub
		return new Information(this.info);
	}

}
