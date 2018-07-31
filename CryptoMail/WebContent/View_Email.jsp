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
  <%@include file="header.jsp" %>
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
    
    <button class="btn btn-danger navbar-btn navbar-right" id="compose_button"><span class="glyphicons glyphicon-envelope"></span> Compose</button>
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
    
		  <tr class="unread_message">
            <td><div class="checkbox checkbox-warning"><input type="checkbox" id="checkbox1" class="styled"><label></label></div></td>
            <td>Mark</td>
            <td>13.03.2018</td>
            <td>Fresh message</td>
        </tr>
            
        <tr>
            <td><div class="checkbox checkbox-warning"><input type="checkbox" id="checkbox2" class="styled"><label></label></div></td>
            <td>John</td>
            <td>13.01.2018</td>
            <td>Not so important message</td>
        </tr>
        <tr>
            <td><div class="checkbox checkbox-success"><input type="checkbox" id="checkbox3 class="styled""><label></label></div></td>
            <td>Peter</td>
            <td>12.01.2018</td>
            <td>Very important message</td>
        </tr>
    </tbody>
</table>
</div></div></div>

</body>
</html>

