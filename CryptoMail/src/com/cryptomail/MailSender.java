package com.cryptomail;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.sun.research.ws.wadl.Request;

@Path("send")
public class MailSender {
	@Context
	private ServletContext context;
	@Context 
	private HttpServletRequest request;

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public Response sendMail(@QueryParam("to") String recipient, @QueryParam("subject") String subject, @QueryParam("message") String message, @QueryParam("username") String user) throws ClassNotFoundException, MalformedURLException, URISyntaxException { 
		MailService service = new MailService();
		Database db = new Database();
		String password = db.retrievePassword(user);
		
		service.composeEmail(user, password, recipient, subject, message);
		
		String urlStr = "http://localhost:8080/CryptoMail" + "/View_Email.jsp" + "?name=" + user;
		URL url= new URL(urlStr);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());

		return Response.seeOther(uri).build();
	}
}
