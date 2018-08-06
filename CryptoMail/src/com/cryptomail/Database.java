package com.cryptomail;

import java.util.Date;
import java.sql.*;

public class Database {
	
	String dbUrl;
	String uname;
	String password;
	
	public Database() throws ClassNotFoundException {
		this.dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		this.uname = "root";
		this.password = "";
		
	}
	
	
	public void read(String username) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection(dbUrl, uname, password);
		System.out.println("DB connection successful");
		Statement stmt = conn.createStatement();
		String sql = "select * from emails";
		ResultSet resultSet = stmt.executeQuery(sql);
		
		while (resultSet.next()) {
			//System.out.println(resultSet.getString("recipient") + ", " + resultSet.getString("subject"));
		}

		stmt.close();
		conn.close();
	}
	
	public void write(String sender, String recipient, String subject, String body, Date date) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection(dbUrl, uname, password);
		System.out.println("DB connection successful");
		
		String sql = "INSERT INTO emails" + " (sender, recipient, subject, body, date) VALUES (?, ?, ?, ?, ?)";		
		PreparedStatement preparedStmt = conn.prepareStatement(sql);

		// Set parameter values
		preparedStmt.setString(1, sender);
		preparedStmt.setString(2, recipient);
		preparedStmt.setString(3, subject);
		preparedStmt.setString(4, body);
		preparedStmt.setDate(5, (java.sql.Date) date);
		System.out.println("Writing to database.");
		
		preparedStmt.executeUpdate();

		System.out.println("\nInsert Complete.");

		preparedStmt.close();
		conn.close();
	}
	
	public void delete(String sender, String recipient, String subject, String body, Date date) {
		
	}
	
	public String retrievePassword(String username) {
		final String dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		final String uname = "root";
		final String password = "";

		try {
			System.out.println("Starting database**************************************");
			Connection conn = DriverManager.getConnection(dbUrl, uname, password);

			PreparedStatement preparedStmt = conn.prepareStatement("SELECT username,password FROM users WHERE username=?");
			preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				System.out.println("Password found");
				String result = rs.getString("password");
				return result;
			} 		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Password not found");
		return "";
	}
}
