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
    
    

<link rel="stylesheet" href="css/login-css.css">
<meta charset="ISO-8859-1">
<meta name="theme-color" content="#343a40">
<title>Login</title>
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
<form id="accesspanel" action="Login" method="POST" style="height: 315px;">
  <h1 id="litheader">Music Streamer</h1>
  <div class="inset">


    <p>
      <input type="text" required name="username" id="email" placeholder="Email address" autofocus>
    </p>

    <p>
      <input type="password" required name="password" id="password" placeholder="Password">
    </p>
    
    
    <div style="text-align: center;">
    <p class="ins">  <a id="fp" href="forgotpassword.jsp">Forgot password</a></p>
    </div>
    <input id='hidethis' class="loginLoginValue" type="hidden" name="service" value="login" />
  </div>
<center>
<p id='message'>
     <% 
 if(request.getAttribute("message")!=null)
 {
  out.print(request.getAttribute("message"));
 }
   %>  
   </p>
   </center>
  <p class="p-container" style="width:300px;" id="button">
   
    <!-- <input type="submit" name="Login" id="go" value="Login"/> -->
   <button type="submit" name="Login" id="logingo" value="Login">
                Login
            </button>
    
   <center>
 <p class="ins">New to Music Streamer? <a href="signup.jsp">SignUp</a> </p>
 </center>


</form>

<script src='js/auth.js'></script>

</body>
</html>