package com.log;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Signup() {
        super();
   
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
		String res[]= {"","",""};
		LoginDao login = new LoginDao();
		String name=request.getParameter("name");
		String email=request.getParameter("username");
		String password=request.getParameter("password");
		
		res=login.checkMail(email);
		
//		System.out.print("data: "+name+email+password);

//		| name=="" |email==""| password==""
		if(res[0]!="") {
			response.setStatus(400);	
		}
		else {
			
			int n = 10000 + new Random().nextInt(90000);
			
			String otp = String.valueOf(n);
			
			SendMailer.sendMail(email, "OTP", otp);
			
			try {
				login.setUser(name, email, password, otp);
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
		}	
		
	
//			try {
//				login.setUser(name, email, password,otp);
//				 	 
//			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//				
//				e.printStackTrace();
//			}
//			
//		
			
//			if(res[0]!="") {
//				String uname = res[1];
//				String uuname = uname.substring(0, 1).toUpperCase() + uname.substring(1);					
//				HttpSession session=request.getSession();
//				session.setAttribute("uid",res[0]);
//				session.setAttribute("name",uuname);
//				session.setAttribute("role",res[2]);
//				response.sendRedirect("homepage.jsp");
//			}
//			else {
//				request.setAttribute("message","Already Registered please Login");
//				RequestDispatcher rd = request.getRequestDispatcher("login.jsp"); 
//				rd.forward(request, response);
//			}
		
//		response.sendRedirect("login.jsp");
//		
		

	}
}


