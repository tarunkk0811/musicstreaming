package com.log;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerifyUser
 */
@WebServlet("/VerifyUser")
public class VerifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VerifyUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String url="jdbc:postgresql://ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
		 String dbuname="wpuztkpsfnclqf";
		 String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
	 	 String role="user";
		 String otp=request.getParameter("otp");
		 String uname=request.getParameter("name");
		 String email=request.getParameter("username");
		 String password=request.getParameter("password");
		
//		System.out.print(otp+name+email+password);
		if(uname!=null && password!=null) {
		try {	
			Class.forName("org.postgresql.Driver");
    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
        	String verifyotp="select uid from users where email=? and otp=?";
    		PreparedStatement verify_otp_stmt = con.prepareStatement(verifyotp);
    		verify_otp_stmt.setString(1, email);
    		verify_otp_stmt.setString(2, otp);
    		
    		String update="update users set status=true where email=?";
    		PreparedStatement update_stmt = con.prepareStatement(update);
    		ResultSet res = verify_otp_stmt.executeQuery();
    		if(res.next()) {
    			update_stmt.setString(1, email);
    			update_stmt.executeUpdate();
    			String uuname = uname.substring(0, 1).toUpperCase() + uname.substring(1);
				HttpSession session=request.getSession();
				session.setAttribute("uid",res.getString(1));
				session.setAttribute("name",uuname);
				session.setAttribute("role",role);
				response.sendRedirect("homepage.jsp");
				
    		}
    		else {
    			request.setAttribute("message","Wrong otp");
    			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp"); 
    			rd.forward(request, response);
    		}
    	
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
		else {
			
			try {	
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
	        	String verifyotp="select uid from users where email=? and otp=?";
	    		PreparedStatement verify_otp_stmt = con.prepareStatement(verifyotp);
	    		verify_otp_stmt.setString(1, email);
	    		verify_otp_stmt.setString(2, otp);
	    		ResultSet res = verify_otp_stmt.executeQuery();
	    		if(res.next()) {
	    			response.setStatus(200);
	    		}
	    		else {
	    			response.setStatus(400);
	    		}
	    		
			}
			catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
