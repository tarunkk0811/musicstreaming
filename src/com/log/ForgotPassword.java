package com.log;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ForgotPassword() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDao login = new LoginDao();
		
		String email=request.getParameter("username");
		boolean res = login.otpToEmail(email);
		if(res) {
			response.setStatus(200);
		}
		else {
			response.setStatus(400);
		}
		
		
		
	}

}
