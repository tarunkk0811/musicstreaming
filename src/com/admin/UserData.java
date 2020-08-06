package com.admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UserData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		  String url="jdbc:postgresql://ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
		     String dbuname="wpuztkpsfnclqf";
		     String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
		
		String s="<tr>\r\n" + 
				"<th>User Id</th>\r\n" + 
				"<th>User Name</th>\r\n" + 
				"<th>Email Id</th>\r\n" + 
				"<th>Role</th>\r\n" + 
				"<th>Verified User</th>\r\n" + 
				"<th>Created On </th>\r\n" + 
				"<th>Operation</th>\r\n" + 
				"</tr>";
		try {	
    		Class.forName("org.postgresql.Driver");
    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
        	String get_details="select uid,name,email,role,status,date(created_on) from users";
    		PreparedStatement getstmt = con.prepareStatement(get_details);
    		ResultSet rs = getstmt.executeQuery();
    		while(rs.next()){
    				int uid=rs.getInt(1);
    				String name=rs.getString(2);
    				String email=rs.getString(3);
    				String role=rs.getString(4);
    				Boolean status=rs.getBoolean(5);
    				Date date = rs.getDate(6);
    				s+="\r\n" + 
    						"<tr>\r\n" + 
    						"<td>"+uid+"</td>\r\n" + 
    						"<td>"+name+" </td>\r\n" + 
    						"<td>"+email+"</td>\r\n" + 
    						"<td>"+role+"</td>\r\n" + 
    						"<td>"+status+"</td>\r\n" + 
    						"<td>"+date+"</td>\r\n" +
    						"<td><i class=\"fa fa-trash\"></i></td>\r\n" +
    						"</tr>\r\n";
    						 
    			
    			
            }
    		out.print(s);
    		
    	}
    	catch (SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	
		
		
		
	}

}
