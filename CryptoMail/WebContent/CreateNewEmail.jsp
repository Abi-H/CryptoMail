<%@ page language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Welcome to the CryptoMail!</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/awesome-bootstrap-checkbox.css" rel="stylesheet">
          <link href="css/style.css" rel="stylesheet">

   
  </head>
  <body>
  <%@include file="header.jsp" %>
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/cryptomail/send">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">To:</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="to" name="to"  value="">
		</div>
	</div>
	
	<div class="form-group">
		<label for="subject" class="col-sm-2 control-label">Subject:</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="subject" name="subject" value="">
		</div>
	</div>
	
	<div class="form-group">
		<label for="message" class="col-sm-2 control-label">Message:</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="message"></textarea>
		</div>
	</div>
	
	
	<div class="form-group">
	<label class="col-sm-2 control-label" for="inputGroupFile01">Choose file:</label>
		<div class="col-sm-10 col-sm-offset-2">
			<div class="custom-file">
    <input type="file" class="custom-file-input" id="inputGroupFile01" name="File_Attachment">
    
  </div>
</div>
</div>
	
	
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<input id="submit" name="Submit_Email" type="submit" value="Send" class="btn btn-primary" >
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
		<!-- To display an alert to the user -->
		
		</div>
	</div>
</form>
 


</head>
<body>

<% 
	 String sender = request.getParameter("sender");
	 String recipient = request.getParameter("recipient");
	 String subject = request.getParameter("subject");
	 String body = request.getParameter("body");
	 
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cryptomail");
	Statement stmt = conn.createStatement();
	
	stmt.executeUpdate("insert into email"(sender,recipient,subject,body) values ('"+sender+"','"+recipient+"','"+subject'",'"+body+"')");
}

%>


</body>
</html>