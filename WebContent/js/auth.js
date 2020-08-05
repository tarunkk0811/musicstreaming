//signup
var form = document.forms[0];
var timer = 30;


let loadingBtn = (load_msg) => {
            let btn = form.getElementsByTagName('button')[0];
            btn.disabled = true;
            btn.innerHTML = '<i class="fa fa-circle-o-notch fa-spin fa-lg fa-fw"></i> ' + load_msg + '...';
            btn.style.opacity = 0.5;
            btn.style.cursor = "not-allowed";
        }
let validateEmail = () => {
            const email = form["username"].value;
            if (email.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/))
                return true;
            else
                return false
        };
let validatePassword = () => {
            const password = form["password"].value;
            return password.length >= 8 ? true : false;
        } 



function sendotp(){
		
		let countDownResend = setInterval(()=>{
   		 let tag = document.getElementsByTagName('strong')[0];
  		 timer--; 
		 tag.innerText = timer;
  		 
 		   if(timer === 0){
 		   clearInterval(countDownResend);
			document.getElementById("resendotp").disabled=false;
			document.getElementById("resendotp").style.opacity=1;
			timer=30;
			}
			},1000)

		
		document.getElementById('accesspanel').style.height="260px";
		
		document.getElementById('name').style.display="none";
	
		document.getElementById('email').style.display="none";
	
		document.getElementById('password').style.display="none";
		
		document.getElementById('ins').style.display="none";

		document.getElementById('insbtn').innerHTML="Try again in <strong>30 </strong> seconds: <button form='' style='margin: 0; opacity:0.4; width: auto; height: auto;margin-top: 0; margin-bottom: 5px; background: transparent; color: white;' id='resendotp' disabled>Resend Otp</button>";
		  
		document.getElementById('otp').style.display="block";
		
		document.getElementById('button').innerHTML= "<button type='submit' form='' name='verify' id='verify' value='Confirm Otp'>Confitm Otp</button>";
		
		document.getElementById('message').innerText="";
	
	}



$(document).on("click", "#go", function() {
	if(validateEmail()&& validatePassword()){
		loadingBtn("Sending Otp");
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
}
else{
	document.getElementById('accesspanel').style.height="360px";
	document.getElementById('message').style.color="red";
	document.getElementById("message").innerText="Invalid Email or Password";
}

});


$(document).on("click", "#resendotp", function() {
	sendotp();
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
			document.getElementById('message').style.color="lightgreen";	
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
			document.getElementById('message').style.color="red";
           
          }
    });
});



//forgot password
function forgotsendotp(){
	
/*		 let countDownResend = setInterval(()=>{
   		 let tag = document.getElementsByTagName('strong')[0];
  		 timer--; 
		 tag.innerText = timer;
  		 
 		   if(timer === 0){
 		   clearInterval(countDownResend);
			document.getElementById("resendotp").disabled=false;
			document.getElementById("resendotp").style.opacity=1;
			timer=30;
			}
			},1000)
*/
	
	//if(document.getElementById('name').innerText!="" &&  (document.getElementById('email').innerText!="") && (document.getElementById('password').innerText!="")){
		
		document.getElementById('accesspanel').style.height="240px";
	
		document.getElementById('ins').innerHTML="<button form='' style='height:auto;width:auto; background-color:transparent;border:0;color:#ffffff;';  id='forgotresendotp'>Resend Otp</button>";
		
		document.getElementById('otp').style.display="block";
		
		document.getElementById('button').innerHTML= "<button type='submit' form='' name='verify' id='forgotverify' value='Confirm Otp'>Confirm Otp</button>";
		
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
	document.getElementById('button').innerHTML= "<button type='submit' name='verify' id='forgotverify' value='Change Password'>Change Password</button>";
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
	if(validateEmail()){
	loadingBtn("Sending Otp");
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
}
else{
	
	document.getElementById("message").innerText="Invalid Email";
}

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

//login


document.getElementById("logingo").addEventListener('click',function(){
	loadingBtn("Authenticating");
	document.forms[0].submit();
	
})
