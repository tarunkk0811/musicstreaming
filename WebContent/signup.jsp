<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <!--jQuery-->
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <!-- Bootstarp -->
    <link
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
      crossorigin="anonymous"
    />
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
      integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
      crossorigin="anonymous"
    ></script>
   
    <!-- Font Awesome -->
    <script src="https://use.fontawesome.com/bae75bb48f.js"></script>
    
    <!-- ajax -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link rel="stylesheet" href="css/login-css.css">
<meta charset="ISO-8859-1">
<meta name="theme-color" content="#343a40">
<title>Signup here</title>
</head>

<body>


<%
response.setHeader("cache-control","no-cache, no-store, must-revalidate");//http 1.1
response.setHeader("Pragma","no-cache");//http 1.0
response.setHeader("Expires","0");//Proxies
if(session.getAttribute("uid")==null);
else
{
	response.sendRedirect("homepage.jsp");		
}
%>



<div class="background-wrap">
  <div class="background"></div>
</div>
<form id="accesspanel" name='signupform' action="VerifyUser" method="POST" style="height: 350px;">
  <h1 id="litheader">Music Streamer</h1>
  <div class="inset">

	<p id='otp' style="display:none;">
	<input type="password" required id="otpid" name="otp" placeholder="Enter OTP">
	</p>
	
  	<p id='name'>
      <input type='text' required name='name' id='nameid' placeholder='Name' autofocus>
    </p>
    
    <p id='email'>
      <input type="text" required name="username" id="emailid" placeholder="Email address">
    </p>
      
    
    <p id='password'>
      <input type="password" required name="password" id="passwordid" placeholder="Password">
    </p>
    
    
     <input type="submit" id='submitform' value="Sign In" style="display: none;" >
     
  </div>
  
  

   <center>
     <p id='message'  style='margin-bottom:10px;' ><%if(request.getAttribute("message")!=null){
  out.print(request.getAttribute("message"));
     }	 
    	 %> </p>
     
     </center>
    
      
    <center>
     <p id='insbtn'></p>
     </center>
   <br>
  <p class="p-container" style="width:300px;" id="button">
   <!--  <input type="submit" form=""  name="Login" id="go" value="Send Otp"/> -->
   <button type="submit" form="" name="Login" id="go" value="Send Otp">
                Send Otp
            </button>
    </p>
    
   
    
    
    <center>
     <p id='ins'> Already a User ? <a href='login.jsp'>Login here</a></p>
     </center>
     
</form>


<script src='auth.js'></script>



</body>
</html>