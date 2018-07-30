// CryptoMail

package com.cryptomail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class Email {

	String sender;
	String[] recipients;
	String password;
	String host;
	String port;

	public Email(String sender, String[] recipients, String password) {
		this.sender = sender;
		this.recipients = recipients;
		this.password = password;
		this.host = "smtp.gmail.com";
		this.port = "587";
	}

	public void createEmail(String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		};

		Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, recipients, subject, body);	
	}
}