// CryptoMail

package com.cryptomail;

import java.util.Scanner;
import javax.mail.MessagingException;

public class App {
	
	static String username;
	static String password;

	public static void main(String[] args) throws MessagingException {
		
		login();
		printMenu();
		//char choice = getInput();
		char choice = '2';

		while (choice != '0') {

			switch (choice) {
			case '1':
				composeEmail();
				break;
			case '2':
				readEmail();
				break;
			case '0':
				System.out.println("Goodbye.");
				System.exit(0);
			default:
				System.out.println("Invalid option.");
				break;
			}

			printMenu();
			choice = getInput();
		}
	}
	
	public static void login() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter username: ");
		username = scanner.nextLine();
		System.out.println("Enter password: ");
		password = scanner.nextLine();
		scanner.close();
	}

	public static void printMenu() {
		System.out.println("\n\n+=====================+");
		System.out.println("+      CryptoMail     +");
		System.out.println("+=====================+");
		System.out.println("\n[1] Send email");
		System.out.println("[2] Read emails");
		System.out.println("[0] Exit");
	}

	public static char getInput() {
		Scanner in = new Scanner(System.in);
		char input = in.nextLine().charAt(0);
		in.close();
		return input;
	}

	public static void readEmail() throws MessagingException {
		MailService mailService = new MailService();
		mailService.login("imap.gmail.com", username, password);
		mailService.viewEmails(username, password);
	}

	public static void composeEmail() {
		MailService mailService = new MailService();
		mailService.composeEmail(username, password);
	}
}