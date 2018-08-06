<%@ page language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Welcome to the Crypto Email, <%= session.getAttribute( "username" ) %>!</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/awesome-bootstrap-checkbox.css" rel="stylesheet">
          <link href="css/style.css" rel="stylesheet">

   
  </head>
  <body>
  <%@include file="header.jsp" %>
  <%@ page import="java.sql.*" %>
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
    
    <button class="btn btn-danger navbar-btn navbar-right" id="compose_button" onClick="JavaScript:window.location='CreateNewEmail.jsp';"><span class="glyphicons glyphicon-envelope"></span> Compose</button>
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
    	String email = "abi.cryptomail@gmail.com";
    	String dbUrl = "jdbc:mysql://localhost:3306/cryptomail";
    	String uname = "root";
    	String password = "";
    	Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection(dbUrl, uname, password);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM emails WHERE recipient = '" + email + "'";
		ResultSet resultSet = stmt.executeQuery(sql);  	
    	%>
  	
		<% while(resultSet.next()){ %>
				<tr>
				<td><div class="checkbox checkbox-warning"><input type="checkbox" id="checkbox1" class="styled"><label></label></div></td>
				<td>  <%= resultSet.getString(1) %></td>
				<td>  <%= resultSet.getString(5) %></td>
				<td>  <%= resultSet.getString(3) %></td>
				<td>  <%= resultSet.getString(4) %></td>
				</tr>
		<%} %>
		   
    </tbody>
</table>

</body>
</html>

