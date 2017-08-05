package com.mh.ta.core.config;

public class EmailConfig {
	private String emailUser, emailPass, host, sendTo, subject, attachedReport;

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

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachedReport() {
		return attachedReport;
	}

	public void setAttachedReport(String attachedReport) {
		this.attachedReport = attachedReport;
	}
}
