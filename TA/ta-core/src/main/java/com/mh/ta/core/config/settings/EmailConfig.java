
package com.mh.ta.core.config.settings;

import java.util.List;

/**
 * @author minhhoang
 *
 */
public class EmailConfig {
	private String emailUser;
	private String emailPass;
	private String host;
	private String subject;
	private String fromTo;
	private List<String> sendTo;
	boolean attachedReport;
	private boolean enable=false;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailPass() {
		return emailPass;
	}

	public void setEmailPass(String emailPass) {
		this.emailPass = emailPass;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List<String> getSendTo() {
		return sendTo;
	}

	public void setSendTo(List<String> sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Boolean getAttachedReport() {
		return attachedReport;
	}

	public void setAttachedReport(Boolean attachedReport) {
		this.attachedReport = attachedReport;
	}

	public String getFromTo() {
		return fromTo;
	}

	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}
}
