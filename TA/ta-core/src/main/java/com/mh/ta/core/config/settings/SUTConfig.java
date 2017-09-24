
package com.mh.ta.core.config.settings;

import java.util.List;
import java.util.Map;

/**
 * @author minhhoang
 *
 */
public class SUTConfig {

	String baseUri;
	Boolean enableRecord = false;
	List<Map<String, String>> accounts;

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public List<Map<String, String>> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Map<String, String>> accounts) {
		this.accounts = accounts;
	}

	public Boolean getEnableRecord() {
		return enableRecord;
	}

	public void setEnableRecord(Boolean enableRecord) {
		this.enableRecord = enableRecord;
	}

}
