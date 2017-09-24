
package com.mh.ta.core.report.notification;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author minhhoang
 *
 */
public class Mail {
	private String host;
	private String fromEmail;
	private String emailSubject;
	private String emailUser;
	private String emailPassword; 
	private String emailContent = "";
	private String 	contentType = "text/plain";
	private List<String> toEmail;
	private Message msg;
	private Session session;

	public Mail(String host, String fromEmail, String emailSubject, String emailUser, String emailPassword,
			List<String> toEmail) {
		this.host = host;
		this.fromEmail = fromEmail;
		this.emailSubject = emailSubject;
		this.emailUser = emailUser;
		this.emailPassword = emailPassword;
		this.toEmail = toEmail;
	}

	public Mail() {
	}

	public void sendAnonymousMail() throws  MessagingException {
		initAnonymousEmailConfig();
		msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(fromEmail));
		InternetAddress[] toAddress = getListToEmail();
		msg.setRecipients(Message.RecipientType.TO, toAddress);
		msg.setSubject(emailSubject);
		msg.setSentDate(new Date());
		msg.setContent(emailContent, contentType);
		Transport.send(msg);
	}

	public void sendAuthenticateMail() throws  MessagingException {
		initAuthenticateEmailConfig();
		msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(fromEmail));
		InternetAddress[] toAddress = getListToEmail();
		msg.setRecipients(Message.RecipientType.TO, toAddress);
		msg.setSubject(emailSubject);
		msg.setSentDate(new Date());
		msg.setContent(emailContent, contentType);
		Transport.send(msg);
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public void setToEmail(List<String> toEmail) {
		this.toEmail = toEmail;
	}

	private void initAnonymousEmailConfig() {
		Properties props = System.getProperties();	
		props.put("mail.host", host);
		props.put("mail.transport.protocol", "smtp");
		session = Session.getDefaultInstance(props, null);	
	}

	private void initAuthenticateEmailConfig() {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailUser, emailPassword);
			}
		});
	}

	private InternetAddress[] getListToEmail() throws AddressException {
		int toEmailSize = toEmail.size();
		InternetAddress[] address = new InternetAddress[toEmailSize];
		for (int count = 0; count < toEmailSize; count++) {
			address[count] = new InternetAddress(toEmail.get(count));
		}
		return address;
	}
}
