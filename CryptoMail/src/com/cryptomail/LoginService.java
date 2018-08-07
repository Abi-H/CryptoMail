package com.cryptomail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("login")

public class LoginService {

	@Context
	private ServletContext context;
	@Context 
	private HttpServletRequest request;

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public Response handleDetails(@QueryParam("Email") String email, @QueryParam("Password") String password)
			throws ClassNotFoundException, IOException, InterruptedException, MessagingException, SQLException, URISyntaxException {
		
		if (checkCredentials(email, password)) {
			System.out.println("Successful login.");
			MailService service = new MailService();
			service.storeEmails(email, password);
			
			String urlStr = "http://localhost:8080/CryptoMail" + "/View_Email.jsp" + "?name=" + email;
			URL url= new URL(urlStr);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());

			return Response.seeOther(uri).build();
		} else {
			System.out.println("Login failure.");
			URI invalidInputUri = UriBuilder.fromPath(context.getContextPath() + "/LoginPage.jsp").build();
			return Response.seeOther(invalidInputUri).build();
		}

	}

	public boolean checkCredentials(String email, String pass) {
		final String dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
		final String uname = "root";
		final String password = "";

		try {
			Connection conn = DriverManager.getConnection(dbUrl, uname, password);

			PreparedStatement preparedStmt = conn.prepareStatement("SELECT username,password FROM users WHERE username=? AND password=?");
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, pass);
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
