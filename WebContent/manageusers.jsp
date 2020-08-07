<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="icon" href="css/music.png" type="image/icon type"> 
	
<title>User Management</title>
</head>
<body><%-- 
<%
response.setHeader("cache-control","no-cache, no-store, must-revalidate");//http 1.1
response.setHeader("Pragma","no-cache");//http 1.0
response.setHeader("Expires","0");//Proxies
if(session.getAttribute("uid")==null){
	request.setAttribute("message","session invalid");
	response.sendRedirect("login.jsp");
	
}
else if(session.getAttribute("role").equals("admin"));
else{
	request.setAttribute("message","session invalid");
		response.sendRedirect("homepage.jsp");		
}
%> --%>

<div>
<center>
<h4>Manage Users</h4>
</center>
<a href="addstaff.jsp">Add Admin</a>
<center>
<table class="table table-bordered" id='userdata'>
<tr>
<th>User Id</th>
<th>User Name</th>
<th>Email Id</th>
<th>Role</th>
<th>Verified User</th>
<th>Created On </th>
<th>Operation</th>
</tr>

</table>

</center>
</div>

<script>
$(document).ready(function() { 
		
			$.ajax({
				 url: 'UserData',	
	    		success: function(responseText) {
		        		$('#userdata').html(responseText);
		    	}
			});
	}); 
</script>

</body>
</html>