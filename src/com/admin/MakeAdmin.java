package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class MakeAdmin
 */
@WebServlet("/MakeAdmin")
public class MakeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MakeAdmin() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	PrintWriter out = response.getWriter();
     	   String url="jdbc:postgresql://ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
	     String dbuname="wpuztkpsfnclqf";
	     String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
		int uid=Integer.parseInt(request.getParameter("uid"));
		try {	
    		Class.forName("org.postgresql.Driver");
    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
        	String get_details="select role from users where uid=?";
    		PreparedStatement getstmt = con.prepareStatement(get_details);
    		getstmt.setInt(1, uid);
    		ResultSet rs=getstmt.executeQuery();
    		while(rs.next()){
    			String cur_role=rs.getString(1);
    			String up_query="update users set role=? where uid=?";
    			PreparedStatement up_stmt = con.prepareStatement(up_query);
    			up_stmt.setInt(2,uid);
    			
    			if(cur_role.equals("admin"))
    				up_stmt.setString(1,"user");
    			else
    				up_stmt.setString(1,"admin");
    			
    			up_stmt.executeUpdate();
    		}
    	}
		catch (SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
		
	}

}
