package com.cryptomail;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;

@Path("login")

public class LoginService {

	@Context
	private ServletContext context;

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public Response handeDetails(@QueryParam("Email") String email, @QueryParam("Password") String password)
			throws ClassNotFoundException, IOException, InterruptedException, MessagingException, SQLException {

		URI uri;

		if (checkCredentials(email, password)) {
			System.out.println("Successful login************************************************************");
			MailService service = new MailService();
			service.storeEmails(email, password);
			uri = UriBuilder.fromPath(context.getContextPath() + "/View_Email.jsp").build();
			//uri = UriBuilder.fromPath(context.getContextPath() + "/View_Email.jsp" +"?"+"name=" + email).build();

			return Response.seeOther(uri).build();
		} else {
			System.out.println("Login failure***********************************************");
			uri = UriBuilder.fromPath(context.getContextPath() + "/LoginPage.jsp").build();
			return Response.seeOther(uri).build();
		}

	}

	public boolean checkCredentials(String email, String pass) {
		final String dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		final String uname = "root";
		final String password = "";

		try {
			System.out.println("Starting database**************************************");
			Connection conn = DriverManager.getConnection(dbUrl, uname, password);

			PreparedStatement preparedStmt = conn.prepareStatement("SELECT username,password FROM users WHERE username=? AND password=?");
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, pass);
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				System.out.println("Result set found.");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
