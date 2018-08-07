package com.cryptomail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.*;

public class Database {

	String dbUrl;
	String uname;
	String password;
	ArrayList<String> fields;

	public Database() throws ClassNotFoundException {
		this.dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		this.uname = "root";
		this.password = "";
		fields = new ArrayList<>(); 
	}

	public void write(String sender, String recipient, String subject, String body, Date date)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, uname, password);

		String sql = "INSERT INTO emails" + " (sender, recipient, subject, body, date) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(sql);

		preparedStmt.setString(1, sender);
		preparedStmt.setString(2, recipient);
		preparedStmt.setString(3, subject);
		preparedStmt.setString(4, body);
		preparedStmt.setDate(5, (java.sql.Date) date);
		System.out.println("Writing to database.");

		preparedStmt.executeUpdate();

		preparedStmt.close();
		conn.close();
	}

	public String retrievePassword(String username) {
		final String dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		final String uname = "root";
		final String password = "";

		try {
			Connection conn = DriverManager.getConnection(dbUrl, uname, password);

			PreparedStatement preparedStmt = conn
					.prepareStatement("SELECT username,password FROM users WHERE username=?");
			preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				String result = rs.getString("password");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public void read(String username) throws ClassNotFoundException, SQLException {
		
		String email = "abi.cryptomail@gmail.com";
		String dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		String uname = "root";
		String password = "";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, uname, password);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM emails WHERE recipient = '" + email + "'";
		ResultSet resultSet = stmt.executeQuery(sql);
		
		while(resultSet.next()) {
			fields.add(resultSet.getString(1));
			fields.add(resultSet.getString(5));
			fields.add(resultSet.getString(3));
			fields.add(resultSet.getString(4));
		}
	}
	
	public ArrayList<String> getFields() {
		return fields;
	}
}
