package com.log;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login() {
        super();
    
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String res[]= {"","",""};
			LoginDao login = new LoginDao();
	
//			String name=request.getParameter("name");
			String email=request.getParameter("username");
			String password=request.getParameter("password");
			
			
			res=login.checkUser(email, password); // id and name and role is ret
			if(res[0]!="") {
				String uname = res[1];
				String uuname = uname.substring(0, 1).toUpperCase() + uname.substring(1);
				HttpSession session=request.getSession();
				session.setAttribute("uid",res[0]);
				session.setAttribute("name",uuname);
				session.setAttribute("role",res[2]);
				response.sendRedirect("homepage.jsp");
			}
			else {
				
				request.setAttribute("message","Incorrect Email or Password ");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			
			}
			
}
