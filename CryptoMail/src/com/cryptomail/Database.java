package com.cryptomail;

import java.util.Date;
import java.sql.*;

public class Database {
	
	String dbUrl;
	String uname;
	String password;
	
	public Database() {
		this.dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		this.uname = "root";
		this.password = "";
	}
	
	
	public void read(String username) throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, uname, password);
		System.out.println("DB connection successful");
		Statement stmt = conn.createStatement();
		String sql = "select * from emails";
		ResultSet resultSet = stmt.executeQuery(sql);
		
		while (resultSet.next()) {
			System.out.println(resultSet.getString("recipient") + ", " + resultSet.getString("subject"));
		}

		stmt.close();
		conn.close();
	}
	
	public void write(String sender, String recipient, String subject, String body, Date date) throws SQLException {
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
}
