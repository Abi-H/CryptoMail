// CryptoMail

package com.cryptomail;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;

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
