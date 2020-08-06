package com.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean res=false;
		String email=request.getParameter("username");
		String pass=request.getParameter("pass");
		String conpass=request.getParameter("conpass");
		if(conpass.equals(pass) && pass.length()>=8 ) {
			LoginDao login = new LoginDao();
			res=login.changePassword(email, pass);		
		}
		if(res) {
			request.setAttribute("message","Password changed");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp"); 
			rd.forward(request, response);
		}
		else {
			request.setAttribute("message","Password change failed");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp"); 
			rd.forward(request, response);	
		}
	}

}
