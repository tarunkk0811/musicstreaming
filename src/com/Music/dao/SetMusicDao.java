package com.Music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetMusicDao {
	
	 String url="postgres://wpuztkpsfnclqf:c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a@ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
     String dbuname="wpuztkpsfnclqf";
     String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
     
	  
	 public String setLanguage(String language){
		  
		  String lid="";
		  
		  int lang_count=0;
		  
		  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
		  Connection con = DriverManager.getConnection(url,dbuname,dbpass);
		  
		  String lang_search_query="select * from languages";
		  
		  PreparedStatement lang_search_stmt = con.prepareStatement(lang_search_query);
		  
		  ResultSet res = lang_search_stmt.executeQuery();
		  
		  while(res.next()){ 
			  
				String id = res.getString(1).toString();
				
				String l = res.getString(2).toString();
				
				if(l.equals(language)) {
					//Language already exist now just move on to albums	
						con.close();
						return id;
				}
			lang_count+=1;
			//To generate new lang if we need to insert a new lang - we need count 
			}
		  
		  	lang_count++;
		  	
		  	PreparedStatement insert_lang_stmt = con.prepareStatement("insert into languages(LID,LNAME) value(?,?)");
			
			lid="L"+lang_count;
			
			insert_lang_stmt.setString(1,lid);
			
			insert_lang_stmt.setString(2,language);
				
			int r_e = insert_lang_stmt.executeUpdate();
			
			if(r_e>0) {
				con.close();
				return lid;
			}
		
		  	}
		  catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		  return lid;
	  
	  }
	  
	 
	 public String setAlbum(String lid,String albumname,String posterurl) {
		 
		  String aid="";
		  
		  int album_count=0;
		  
		  try {
			  
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
		  Connection con = DriverManager.getConnection(url,dbuname,dbpass);
		  
		 String album_search_query = "Select * from albums";
		 
		 PreparedStatement album_search_stmt = con.prepareStatement(album_search_query);
		 
		 ResultSet alb_res = album_search_stmt.executeQuery();
			
		 while(alb_res.next()){
				
				String id = alb_res.getString(1).toString();
			
				String a = alb_res.getString(3).toString();
				
				if(a.equals(albumname)) {
					//album already exist now just move on to songs
					
					if(posterurl!="") {
						
						PreparedStatement stmt = con.prepareStatement("update albums set poster=? where aid=?");
						stmt.setString(1, posterurl);
						stmt.setString(2, id);
						stmt.executeUpdate();
						
					}
					con.close();
					 return id;
				}
				album_count+=1;
		  }
		 
		 	String album_insert_query = "insert into albums(AID,LID,ANAME,POSTER) values(?,?,?,?)";
		 	
			PreparedStatement album_insert_stmt = con.prepareStatement(album_insert_query);
			
			album_count++;
			
			aid="A"+album_count;
			
			album_insert_stmt.setString(1,aid);
			
			album_insert_stmt.setString(2,lid);
			
			album_insert_stmt.setString(3,albumname);
			
			album_insert_stmt.setString(4,posterurl);
			
			int rows_eff = album_insert_stmt.executeUpdate();
			
			
			if (rows_eff>0) {
				con.close();
				return aid;
			}
		  }
		  catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		 
		 
		 return aid;

	 }
	 
	 
	 public String setSongs(String lid,String aid,String songname,String song_url) {
		 
		 	String sid="";
		 	
		 	int song_count=0;
		 	
		 	try {
		 	
	 		Class.forName("com.mysql.cj.jdbc.Driver");
				  
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
				   		
			String count_songs_query="select * from songs";
			
			PreparedStatement statement = con.prepareStatement(count_songs_query);
			
			ResultSet song_res = statement.executeQuery();
			
			
			while(song_res.next()) {
				
				String id = song_res.getString(3).toString();
			
				String s = song_res.getString(4).toString();
				
				if(s.equals(songname) && id.equals(aid)){
					//song already exist now just move on to albums	\
						con.close();
						return "Already Exist";
				}
				
				song_count+=1;
				
			}
			song_count++;
			String song_insert_query = "insert into songs(SID,LID,AID,SNAME,SONG_URL) values(?,?,?,?,?)";
			
			PreparedStatement song_insert_stmt = con.prepareStatement(song_insert_query);
			
			sid="S"+song_count;
			
			song_insert_stmt.setString(1,sid);
			song_insert_stmt.setString(2,lid);
			song_insert_stmt.setString(3,aid);
			song_insert_stmt.setString(4,songname);
			song_insert_stmt.setString(5,song_url);
			
			int rows_eff = song_insert_stmt.executeUpdate();
			if(rows_eff>0) {
				con.close();
				return sid;
			}
		 	}
		 	catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return sid;
	  
	 }
	 
	 public Boolean setRecents(String uid,String sid) {
		 try {
			 	
		 		Class.forName("com.mysql.cj.jdbc.Driver");
					  
				Connection con = DriverManager.getConnection(url,dbuname,dbpass);
				
				String check_previous_occurred = "select *from recents where uid=? and sid=?";	
				String del_previous_occurred = "delete from recents where uid=? and sid=?";
				
				PreparedStatement check_stmt = con.prepareStatement(check_previous_occurred);
				PreparedStatement del_stmt = con.prepareStatement(del_previous_occurred);
				
				check_stmt.setString(1, uid);
				check_stmt.setString(2, sid);
				
				ResultSet res = check_stmt.executeQuery();
				while(res.next()) {
					del_stmt.setString(1, uid);
					del_stmt.setString(2, sid);
					int delres = del_stmt.executeUpdate();
					
				}
				
				String insert_recents_query="insert into recents(uid,sid) values(?,?)";
				
				PreparedStatement statement = con.prepareStatement(insert_recents_query);
				
				statement.setString(1, uid);
				statement.setString(2, sid);
				
				
				int re = statement.executeUpdate();
				con.close();
				if(re>0) {
					return true;
				}
			 
		 }
		 catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
	 
		 	return true;
}
}
