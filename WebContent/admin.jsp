 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="theme-color" content="#343a40">
<title>Official Admin</title>
<meta
name="viewport"
content="width=device-width, initial-scale=1, maximum-scale=1"
/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<style>

table { 
	
	width: 50%; 
	background-color:#ffffcc;
	border-collapse: collapse; 
	margin:50px auto;
	}

tr:nth-of-type(odd) { 
	background: #eee; 
	}

td, th { 
	padding: 10px; 
	border: 1px solid #ccc; 
	text-align: left; 
	font-size: 18px;
	}

input[type=text] {
  width: 100%;
  padding: 2px 2px;
  margin: 2px 0;
  box-sizing: border-box;
}

@media 
only screen and (max-width: 760px),
(min-device-width: 768px) and (max-device-width: 1024px)  {

	table { 
	  	width: 100%; 
	}

	/* Force table to not be like tables anymore */
	table, thead, tbody, th, td, tr { 
		display: block; 
	}
	
	
	tr 
	{ border: 1px solid #ccc; 
	
	}
	
	td { 
		/* Behave  like a "row" */
		border: none;
		border-bottom: 1px solid #eee; 
		position: relative;
		padding-left: 50%; 
	}


}
</style>

</head>


<body>
	<div align="center">

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
%>
<br>


<div class="container">
  <div class="row">
    <div class="col-md">
    <h4>Upload Files</h4>
    
				<table  cellpadding="5px" cellspacing="3px">
					<form id="data" action="MusicData" method="post">
					<tr>
						<td>Language:</td><td> 	<input required type="text" name="lang" id="lang" style="text-transform:uppercase;"></td>
					</tr>
					<tr>
						<td>Album:</td> <td><input required type="text" name="album" id="album" style="text-transform:uppercase;"></td>
					</tr >
					<tr>
						<td>Song Name:</td> <td><input type="text" name="sname" id="sname" style="text-transform:uppercase;"></td>
					</tr>
						<input type="hidden" name="url" id="url" value="" />
						<input type="hidden" name="posterurl" id="posterurl" value="" />
					</form>
					<tr>
						<td>Upload Song here</td> <td> <input type="file" id="files"/><br /><br /></td>
					</tr>
					<tr>
						<td>Upload Progress</td><td><progress value="0" max="100" id="progress"></progress></td>
					</tr>
					<tr>
						<td>Upload Song</td><td> <button id="send">Upload</button></td>
					</tr>
					
					<tr>
						<td>Upload Poster here</td> <td><input type="file" id="posterfiles"/></td>
					</tr>
					<tr>
						<td>Upload Progress</td> <td><progress value="0" max="100" id="posterprogress"></progress></td>
					</tr>
					<tr>
						<td>Upload Poster</td> <td> <button id="sendposter">Upload</button></td>
					</tr>
					
				</table>
				<br>
				
				<input type="submit" value="SAVE TO DATABASE" form="data"/> 
				<br/>
				<br/>
				
				<%
				if(request.getAttribute("data")!=null){
					out.print(request.getAttribute("data"));
				}
				%>
				    
    
      
    </div>
    <div class="col-sm">
    
     					<br>
					<a href="homepage.jsp">Back to HomePage</a>
					<br>
					<br>
					<a href="#">Add Staff</a>
					<br><br><br>
     				 <h4> LOG </h4>	
     				 <br><br>
					Song Uploaded :<p id="uploading"></p>	
					
					Song Uploaded at:<p id="sp"></p>
					
					Poster Uploaded at:<p id="pp"></p>
					
    </div>
  </div>
</div>






</div>



<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.13.1/firebase-app.js"></script>
<!-- TODO: Add SDKs for Firebase products that you want to use -->
<script src="https://www.gstatic.com/firebasejs/7.13.1/firebase-storage.js"></script>
<script type="text/javascript" src="db.js"></script>
</body>
</html>	