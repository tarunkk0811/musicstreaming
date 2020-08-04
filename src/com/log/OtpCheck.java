package com.log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OtpCheck
 */
@WebServlet("/OtpCheck")
public class OtpCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public OtpCheck() {
        super();
    
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String url="jdbc:postgresql://ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
	     String dbuname="wpuztkpsfnclqf";
	     String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
	    
	     String otp=request.getParameter("otp");
	     String email=request.getParameter("username");
		
		try {	
			Class.forName("org.postgresql.Driver");
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
