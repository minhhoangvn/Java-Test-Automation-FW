package com.mh.ta.core.config;

import java.util.List;

import com.google.common.collect.ImmutableMap;

public class SUTConfig {
	public SUTConfig(){}
	public String getBaseUri() {
		return baseUri;
	}
	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}
	public List<ImmutableMap<String, String>> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<ImmutableMap<String, String>> accounts) {
		this.accounts = accounts;
	}
	String baseUri;
	List<ImmutableMap<String, String>> accounts;
}
