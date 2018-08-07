<%@ page language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Welcome to the CryptoMail, <%= session.getAttribute( "username" ) %>!</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/awesome-bootstrap-checkbox.css" rel="stylesheet">
          <link href="css/style.css" rel="stylesheet">

   
  </head>
  <body>
  <%@include file="header.jsp" %>
  <%@ page import="java.sql.*" %>
  <%@ page import="com.cryptomail.Database" %>
  <%@ page import="java.util.*" %>
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
     
<nav class="navbar navbar-default">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav">
      
      <li  class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" id="dropdown_actions">Choose action <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Edit</a></li>
          <li><a href="#">Delete</a></li>
        </ul>
      </li>
      <li ><a id="dropdown_chosen" href="#">Apply action <span class="glyphicon glyphicon-ok"></span></a></li>
      <li><a href="#"><span class="glyphicon glyphicon-refresh"></span> Refresh</a></li>
      
    </ul>
    
    <button class="btn btn-danger navbar-btn navbar-right" id="compose_button" onClick="CreateNewEmail.jsp"><span class="glyphicons glyphicon-envelope"></span> Compose</button>
  </div>
</nav>
  

<table class="table table-condensed">
    <thead>
        <tr>
            <th>Select</th>
            <th>From</th>
            <th>Date</th>
            <th>Subject</th>
        </tr>
    </thead>
    <tbody>
    	<%   	
	    	Database db = new Database();
    		db.read("abi.cryptomail@gmail.com");
	    	ArrayList<String> fields = db.getFields();
	    	System.out.println("Size of fields is " + fields.size());
	    	String entry;
	    	
	    	for(int i = 0; i < fields.size(); i+=4){%>
	    	    <tr>
	            <td><div class="checkbox checkbox-warning"><input type="checkbox" id="checkbox2" class="styled"><label></label></div></td>
	            <% entry = fields.get(i); %>
	            <td><%=entry %> </td>
	            <% entry = fields.get(i+1); %>
	            <td><%=entry %></td>
	            <% entry = fields.get(i+2); %>
	            <td><%=entry %></td>
	            <% entry = fields.get(i+3); %>
	            <td><%=entry %></td>
	        </tr> 
	        <%}%>
	    	
		   
    </tbody>
</table>

</body>
</html>

