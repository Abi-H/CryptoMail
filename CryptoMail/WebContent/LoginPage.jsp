<%@ page language="java" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Welcome to the Crypto Email!</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/awesome-bootstrap-checkbox.css" rel="stylesheet">
          <link href="css/style.css" rel="stylesheet">

   
  </head>
  <body>
 
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <!-- LOGO -->
  <div class="col-lg-1 col-offset-10 centered">
  <img src="img/crypto2.jpg"/ ></th>
	
  <!-- login form -->
  
 <form action="/action_page.php">
  <div class="form-group" class="col-sm-10">
    <label for="email">Email address:</label>
    <input type="email" class="form-control" id="email" placeholder="username">
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" class="form-control" id="pwd" placeholder="password">
  </div>
 
  <button type="submit" class="btn btn-default">Submit</button>
</form>
  </div>
  <!--================ -->

<%@ page import = "java.sql.*" %>

<%

Connection con= null;

PreparedStatement preparedStatement= null;

ResultSet resultSet= null;

String driverName = "com.mysql.jdbc.Driver";

String url = "jdbc:mysql://localhost:3306/record";

String user = "root";

String password = "";


String sql = "select * from user where username=? and password=? ";



String name = request.getParameter("username");

String password = request.getParameter("password");

if((!(name.equals(null) || name.equals("")) && !(password.equals(null) || 
password.equals(""))) 

{
try{

	Class.forName(driverName);

	conn = DriverManager.getConnection(url, user, password);

	preparedStatement = con.prepareStatement(sql);

	preparedStatement.setString(username, username);

	preparedStatement.setString(password, password);

	resultSet = ps.executeQuery();

	if(resultSet.next())

	{ 

	username = rs.getString("name");

	password = rs.getString("password");

	if(name.equals(username) && password.equals(password))

	{

	session.setAttribute("username",username);

	session.setAttribute("usertype", username); 

	response.sendRedirect("View_Email.jsp"); 

	} 

	}

	else

	response.sendRedirect("LoginPage.jsp");

	rs.close();

	ps.close(); 

	}

	catch(SQLException sqe)

	{

	out.println(sqe);

	} 

	}

	else

	{ 
%>
</body>
</html>