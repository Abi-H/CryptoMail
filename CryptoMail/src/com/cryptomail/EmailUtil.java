// CryptoMail

package com.cryptomail;

import java.util.Date;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	private static String sender;
	
	public EmailUtil(String sender) {
		EmailUtil.sender = sender;
	}

	public static void sendEmail(Session session, String[] recipients, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.setFrom(sender);
			
			Address[] addresses = new InternetAddress[recipients.length];
			
			for(int i = 0; i < recipients.length; i++) {
				Address address = new InternetAddress(recipients[i]);
				addresses[i] = address;
			}
			
			msg.setReplyTo(addresses);			
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, addresses);			
			System.out.println("Message is ready");
			
			Transport.send(msg);			
			System.out.println("Email sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


