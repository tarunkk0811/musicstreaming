//signup 

function sendotp(){
	
		document.getElementById('accesspanel').style.height="235px";
		
		document.getElementById('name').style.display="none";
	
		document.getElementById('email').style.display="none";
	
		document.getElementById('password').style.display="none";

		document.getElementById('ins').innerHTML="<button form='' id='resendotp'>Resend Otp</button>";
		
		document.getElementById('otp').style.display="block";
		
		document.getElementById('button').innerHTML= "<input type='submit' form='' name='verify' id='verify' value='Confirm Otp'>";
		
		document.getElementById('message').innerText="";
	
	}



$(document).on("click", "#go", function() {
	
    $.ajax({
    	method: 'post',
        url: 'Signup',
        data: {
            name: document.getElementById("nameid").value,
            username:document.getElementById("emailid").value,
            password:document.getElementById("passwordid").value
        },
        success: function(responseText) {
           sendotp();
        },
        error: function () {
        	$("#message").html("You are Already a User please Login");
           
          }
    });
});


$(document).on("click", "#resendotp", function() {

    $.ajax({
    	method: 'post',
        url: 'Signup',
        data: {
        	name: document.getElementById("nameid").value,
            username:document.getElementById("emailid").value,
            password:document.getElementById("passwordid").value
        },
        success: function(responseText) {
        	$("#message").html("Otp Sent Successfully");
        }
    });

});


$(document).on("click", "#verify", function() {
    $.ajax({
    	method: 'post',
        url: 'OtpCheck',
        data: {
            username:document.getElementById("emailid").value,
            otp:document.getElementById("otpid").value
        },
        success: function(responseText) {
           $('#submitform').click();
        },
        error: function () {
        	$("#message").html("Incorrect Otp");
           
          }
    });
});



//forgot password
function forgotsendotp(){
	
	
	//if(document.getElementById('name').innerText!="" &&  (document.getElementById('email').innerText!="") && (document.getElementById('password').innerText!="")){
		
	//	document.getElementById('accesspanel').style.height="235px";
	
		document.getElementById('ins').innerHTML="<button form='' id='forgotresendotp'>Resend Otp</button>";
		
		document.getElementById('otp').style.display="block";
		
		document.getElementById('button').innerHTML= "<input type='submit' form='' name='verify' id='forgotverify' value='Confirm Otp'>";
		
		document.getElementById('message').innerHTML="";
		
		document.getElementById('nwuser').innerHTML="";
		
		document.getElementById('accesspanel').style.height="300px";
	
	//}
}


function newpassword(){
	document.getElementById('email').style.display="none";
	document.getElementById('otp').style.display="none";
	document.getElementById('new').style.display="block";
	document.getElementById('con').style.display="block";
	document.getElementById('button').innerHTML= "<input type='submit' name='verify' id='forgotverify' value='Change Password'>";
	document.getElementById('message').innerHTML="";
}


$(document).on("click", "#forgotresendotp", function(e) {
	document.getElementById('message').innerHTML="";
    $.ajax({
    	method: 'post',
        url: 'ForgotPassword',
        data: {
            username:document.getElementById("emailid").value
        },
        success: function(responseText) {
           forgotsendotp();
           $("#message").html("Otp sent");
        },
        error: function () {
        	$("#message").html("failed to send otp");
        
          }
    });


});



$(document).on("click", "#forgotgo", function(e) {
	document.getElementById('message').innerHTML="";
    $.ajax({
    	method: 'post',
        url: 'ForgotPassword',
        data: {
            username:document.getElementById("emailid").value
        },
        success: function(responseText) {
           forgotsendotp();
           $("#message").html("otp sent");
        },
        error: function () {
        	$("#message").html("You are not a User please SignUp");
        
          }
    });


});


$(document).on("click", "#forgotverify", function(e) {
	document.getElementById('message').innerHTML="";
	
    $.ajax({
    	method: 'post',
        url: 'VerifyUser',
        data: {
            username:document.getElementById("emailid").value,
            otp:document.getElementById("otpid").value
        },
        success: function(responseText) {
        	document.getElementById('ins').innerHTML= "";
        	newpassword();
        },
        error: function () {
        	$("#message").html("Incorrect Otp");
        
          }
    });

});

