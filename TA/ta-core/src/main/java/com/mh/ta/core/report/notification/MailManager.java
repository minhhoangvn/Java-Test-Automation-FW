
package com.mh.ta.core.report.notification;

import javax.mail.MessagingException;

import org.apache.logging.log4j.Logger;

import com.mh.ta.core.config.GuiceInjectFactory;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.settings.EmailConfig;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.helper.Constant;
import com.mh.ta.core.report.ReportInformation;

/**
 * @author minhhoang
 *
 */
public class MailManager {
	private static Logger log = LoggerFactory.instance().createClassLogger(MailManager.class);

	private MailManager() {
		throw new IllegalStateException("Utility class");
	}

	public static void sendMail() {
		try {
			MainConfig config = GuiceInjectFactory.instance().getInstanceObjectInject(MainConfig.class,
					ApplicationConfig.class);
			Mail mail = new Mail();
			EmailConfig mailConfig = config.getEmailConfig();
			mail.setEmailSubject(mailConfig.getSubject());
			mail.setToEmail(mailConfig.getSendTo());
			mail.setFromEmail(mailConfig.getFromTo());
			mail.setEmailContent(String.format(Constant.EMAIL_TEMPLATE, createEmailHtmlStyle() + createEmailContent()));
			mail.setContentType("text/HTML");
			mail.setHost(mailConfig.getHost());
			mail.setEmailUser(mailConfig.getEmailUser());
			mail.setEmailPassword(mailConfig.getEmailPass());
			mail.sendAuthenticateMail();
		} catch (MessagingException | NullPointerException e) {
			log.error("Error in sending email. ", e);
		}
	}

	private static String createEmailContent() {
		String tableHtml = "<body>" + "<table>" + "<tr>" + "<th>Test Name</th>" + "<th>Status</th>"
				+ "<th>Test Message</th>" + "</tr>%s" + "</table>" + "</body>";
		StringBuilder builder = new StringBuilder();
		for (String row : ReportInformation.getEmailInformation()) {
			builder.append(row);
		}
		return String.format(tableHtml, builder.toString());
	}

	private static String createEmailHtmlStyle() {
		return Constant.HTML_SYTLE_SOURCE_STRING;
	}
}
