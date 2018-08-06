// CryptoMail

package com.cryptomail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;

public class MailService {
	private Session session;
	private Store store;
	private Folder folder;

	private String protocol;
	private String file;
	private String host;
	private String username;
	private String password;

	public MailService() {
		this.session = null;
		this.store = null;
		this.folder = null;
		this.protocol = "imaps";
		this.file = "INBOX";
		this.host = "localhost";
	}

	public void login(String host, String username, String password) {
		URLName url = new URLName(protocol, host, 993, file, username, password);

		if (session == null) {
			Properties props = null;

			try {
				props = System.getProperties();
			} catch (SecurityException e) {
				props = new Properties();
			}

			session = Session.getInstance(props, null);
		}

		try {
			store = session.getStore(url);
			store.connect();
			folder = store.getFolder(url);
			folder.open(Folder.READ_WRITE);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public boolean isLoggedIn() {
		return store.isConnected();
	}

	public int getMessageCount() {
		int messageCount = 0;
		try {
			messageCount = folder.getMessageCount();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return messageCount;
	}

	public Message[] getMessages() throws MessagingException {
		return folder.getMessages();
	}

	public void viewEmails(String username, String password) throws MessagingException {
		MailService mailService = new MailService();

		mailService.login("imap.gmail.com", username, password);

		int messageCount = mailService.getMessageCount();
		Message[] messages = mailService.getMessages();

		for (int i = 0; i < messageCount; i++) {
			System.out.print("\n" + (i + 1) + " - ");

			if (messages[i].getFrom() != null) {
				System.out.print(messages[i].getFrom()[0]);
			}

			if (messages[i].getSubject() != null) {
				System.out.print(" > ");
				System.out.print(messages[i].getSubject());
			}
		}
	}

	public void storeEmails(String username, String password) throws MessagingException, IOException, SQLException, ClassNotFoundException {
		Database db = new Database();

		login("imap.gmail.com", username, password);

		int messageCount = getMessageCount();
		Message[] messages = getMessages();

		for (int i = 0; i < messageCount; i++) {
			
			String sender = messages[i].getFrom()[0].toString();
			String recipient = username;
			
			String subject = "";

			if (messages[i].getSubject() != null) {
				subject = messages[i].getSubject();
			} 
			
			String body = getBody(messages[i]);
			
			Date date = messages[i].getReceivedDate();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
						
			db.write(sender, recipient, subject, body, sqlDate);
		}

	}
	
	private String getBody(Message message) throws MessagingException, IOException {
	    if (message.isMimeType("text/plain")){
	        return message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        String result = "";
	        MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
	        
	        int count = mimeMultipart.getCount();
	        
	        for (int i = 0; i < count; i ++){
	            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	            
	            if (bodyPart.isMimeType("text/plain")){
	                result = result + "\n" + bodyPart.getContent();
	                break;  //without break same text appears twice
	                
	            } else if (bodyPart.isMimeType("text/html")){
	                String html = (String) bodyPart.getContent();
	                result = result + "\n" + Jsoup.parse(html).text();
	            }
	        }
	        return result;
	    }
	    return "";
	}

	public void composeEmail(String sender, String password) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> recipientList = new ArrayList<>();

		System.out.println("Enter subject: ");
		String subject = scanner.nextLine();
		System.out.println("Enter message: ");
		String body = scanner.nextLine();
		String recipient = "";

		while (!recipient.equals("end")) {
			System.out.println("Enter recipient or 'end' to continue: ");
			recipient = scanner.nextLine();

			if (!recipient.equals("end")) {
				recipientList.add(recipient);
			}
		}

		scanner.close();

		String[] recipients = new String[recipientList.size()];

		for (int i = 0; i < recipients.length; i++) {
			recipients[i] = recipientList.get(i);
		}

		Email email = new Email(sender, recipients, password);
		email.createEmail(subject, body);
	}
}
