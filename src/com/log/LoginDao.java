package com.log;
import com.log.SendMailer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class LoginDao {
	 String url="postgres://wpuztkpsfnclqf:c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a@ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
     String dbuname="wpuztkpsfnclqf";
     String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
     String role="user";
     
    public String[] setUser(String name,String email,String password,String otp) throws NoSuchAlgorithmException, NoSuchProviderException {
    	String user[]= {"","",""};
    	
        String securedpassword=  get_SHA_256_SecurePassword(password);
    
        
	try {	
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
		String check="select email,status from users where email=?";
		PreparedStatement checkstmt = con.prepareStatement(check);
		checkstmt.setString(1,email);
		ResultSet rs = checkstmt.executeQuery();
		while(rs.next()){
			if(rs.getBoolean(2)==false) {
				System.out.print("setting"+otp);
				String update="update users set otp=? where email=?";
				PreparedStatement updatestmt = con.prepareStatement(update);
				updatestmt.setString(1,otp);
				updatestmt.setString(2,email);
				updatestmt.executeUpdate();
				System.out.print("Updated");
			}
			
			return user;
		}
		
		String query="insert into users(NAME,EMAIL,PASSWORD,ROLE,otp,status) values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, email);
		stmt.setString(3,securedpassword);
		stmt.setString(4,role);
		stmt.setString(5,otp);
		stmt.setBoolean(6, false);
		
		int rowseff = stmt.executeUpdate();
		
		if(rowseff>0) {
			String getdet="select uid,name,role from users where email=? and password=?";
    		PreparedStatement getstmt = con.prepareStatement(getdet);
    		getstmt.setString(1,email);
    		getstmt.setString(2,securedpassword);
    		ResultSet res = getstmt.executeQuery();
    		while(res.next()){
    			 user[0]=String.valueOf(res.getInt(1));
    			user[1]=res.getString(2).toString();
    			user[2]=res.getString(3).toString();
    			
    			return user;
    		}
 			return user;
			
		}
		con.close();
		
	}
	catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	return user;
  }
     
    public String[] checkUser(String email,String upassword) {
//    	System.out.print("data: "+email+upassword);
    	String user[]= {"","",""};
    
    	try {	
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
    		
        	String password=  get_SHA_256_SecurePassword(upassword);
        	String check="select uid,name,role,status from users where email=? and password=?";
    		PreparedStatement checkstmt = con.prepareStatement(check);
    		checkstmt.setString(1,email);
    		checkstmt.setString(2,password);
    		ResultSet rs = checkstmt.executeQuery();
    		
    		while(rs.next()){
    			if(rs.getBoolean(4)) {	
	    			user[0]=String.valueOf(rs.getInt(1));
	    			user[1]=rs.getString(2).toString();
	    			user[2]=rs.getString(3).toString();
	    			return user;	
    			}
            }
    		
    	}
    	catch (SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	
    	return user;
    	
    }
    
        
    private static String get_SHA_256_SecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    public boolean otpToEmail(String email) {
    	
    	try {	
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
    		
        	String check="select status from users where email=? ";
    		PreparedStatement checkstmt = con.prepareStatement(check);
    		checkstmt.setString(1,email);
    		
    		ResultSet rs = checkstmt.executeQuery();
    		
    		while(rs.next()){
    			if(rs.getBoolean(1)) {

    				int n = 10000 + new Random().nextInt(90000);
    				String otp = String.valueOf(n);
    				SendMailer.sendMail(email,"otp", otp);
    				String update="update users set otp=? where email=?";
    	    		PreparedStatement update_stmt = con.prepareStatement(update);
    	    		update_stmt.setString(1, otp);
    	    		update_stmt.setString(2, email);
    	    		update_stmt.executeUpdate();
    	    		return true;
    			}
            }
    		
    	}
    	catch (SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	
    	return false;
    }
    
 public boolean changePassword(String email,String pass) {
    	
    	try {	
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection(url,dbuname,dbpass);
    		String securedpass= get_SHA_256_SecurePassword(pass);
    		System.out.print(pass);
    		String update="update users set password=? where email=?";
    		PreparedStatement updatestmt = con.prepareStatement(update);
    		
    		updatestmt.setString(1, securedpass);
    		updatestmt.setString(2, email);
    		System.out.print(updatestmt);
    		int rs=updatestmt.executeUpdate();
    		
    		if(rs>0){
    			System.out.print("pass reset");
    			return true;
            }
    		
    	}
    	catch (SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.print("pass not reset");
    	return false;
    }
    

	
}
