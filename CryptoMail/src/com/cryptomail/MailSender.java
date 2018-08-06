package com.cryptomail;

import java.net.URI;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("send")
public class MailSender {
	@Context
	private ServletContext context;

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public Response sendMail(@QueryParam("to") String recipient, @QueryParam("subject") String subject, @QueryParam("message") String message) throws ClassNotFoundException {
		String username = "cryptomail.weltec@gmail.com";
		MailService service = new MailService();
		Database db = new Database();
		String password = db.retrievePassword(username);
		
		service.composeEmail(username, password, recipient, subject, message);
		
		URI uri = UriBuilder.fromPath(context.getContextPath() + "/View_Email.jsp").build();

		return Response.seeOther(uri).build();
	}
}
