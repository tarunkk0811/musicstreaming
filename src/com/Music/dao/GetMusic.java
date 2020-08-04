package com.Music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetMusic {
	 String url="jdbc:postgresql://ec2-52-6-143-153.compute-1.amazonaws.com:5432/de4qcm5vtmrvmg";
     String dbuname="wpuztkpsfnclqf";
     String dbpass="c593ec65fb4a4e9d2729e634784081a9420f37705d3bfca52ded291661f2787a";
    
     
	public String getLanguages() {
		String s="<option value=\"\" disabled selected>\r\n" + 
				"                        Language\r\n" + 
				"                    </option>";
		
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			String query="Select lid,lname from languages";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			while(res.next()){
				String id=res.getString(1).toString();
				String temp=res.getString(2).toString();
				s+="<option value='"+id+"'>"+temp+ "</option>";
			}
			con.close();
			return s;
			
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	public String getAlbums(String language_id) {
		
		String s="";
		
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			
			String query="Select AID,ANAME,POSTER from albums where LID=? order by aname";
			
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1,language_id);	
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()){
				String aid = res.getString(1);
				String albnamee=res.getString(2);
				String albname = albnamee.substring(0, 1).toUpperCase() + albnamee.substring(1).toLowerCase();
				String posurl=res.getString(3);
				s+="<div> "
						+ "<button name='"+albname+"' class='album-btn' id='albumbutton' value='"+aid+"'>"+ albname+"</button>"
						+ "  <input id='"+aid+"' type='hidden' value='"+posurl+"' />"
						+ "</div>";   
			}
			con.close();
			return s;
			
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
		
		
		
	}
	
	
	public String getSongs(String aid) {
		String s="";
		//System.out.print("Entered songs");
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			
			String query="Select sid,sname,song_url from songs where aid=?";
			
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1,aid);	
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()){
				String sid = res.getString(1);
				String snames=res.getString(2);
				String sname = snames.substring(0, 1).toUpperCase() + snames.substring(1).toLowerCase();
				String song_url=res.getString(3);
				
				s+="<li id='songlist' class='album-songs-list'><p class='music-icon'><i class='fa fa-music' aria-hidden='true'></i></span></p><p class='song-details'><span class='song-name'><button id='"+sid+"' class='album-song-name-btn' value='"+sname+"' onClick=playSong(this.id,this.value)  >"+sname+"</button></span><!--<span class='tech-details'> Music director . singers . film</span>--></p><p class='song-fav-icon'> <button> <i class='fa fa-heart-o' aria-hidden='true'></i> </button> </p></li>"
						+ "<input class='"+sid+"' type='hidden' value='"+song_url+"'>";
				   
				}
			con.close();
			
			
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
		return s;
	}
	
	
	
	public String getRecents(String uid) {
		
		String s="";
		
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			
			String query="select songs.sid,songs.sname,songs.song_url from songs,recents where  songs.sid = recents.sid and recents.uid=? order by recents.rid desc";
			
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setString(1,uid);	
			
			ResultSet res = stmt.executeQuery();
				while(res.next()) {
					String sid=res.getString(1);
					String ssname=res.getString(2);
					String sname = ssname.substring(0, 1).toUpperCase() + ssname.substring(1).toLowerCase();
					
					String song_url=res.getString(3);
					
					
				s+="<li id='songlist' class='album-songs-list'><button id='"+sid+"' class='album-song-name-btn' value='"+sname+"' onClick=playSong(this.id,this.value)  >"+sname+"</button> </li>"
							+ "<input class='"+sid+"' type='hidden' value='"+song_url+"'>";
				}
		
		con.close();
		return s;
		}
		
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
		
	
	public String getTrendingSongs() {
		
		String s="";
		
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			
			String query="select songs.sid,songs.sname,songs.song_url from songs right outer join (select sid,count(*) as count from recents group by sid order by count desc limit 10) t2 on songs.sid=t2.sid";
			
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);
				while(res.next()) {
					String sid=res.getString(1);
					String ssname=res.getString(2);
					String sname = ssname.substring(0, 1).toUpperCase() + ssname.substring(1).toLowerCase();
					String song_url=res.getString(3);
					
				s+="<li id='songlist' class='album-songs-list'><button id='"+sid+"' class='album-song-name-btn' value='"+sname+"' onClick=playSong(this.id,this.value)  >"+sname+"</button> </li>"
							+ "<input class='"+sid+"' type='hidden' value='"+song_url+"'>";
				}
		
		con.close();
		return s;
		}
		
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	

	public String getTrendingAlbums() {
		
		String s="";
		
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			
			String query="select lid,lname from languages";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);
				while(res.next()) {
					String lid = res.getString(1);
					String llname=res.getString(2);
					String lname = llname.substring(0, 1).toUpperCase() + llname.substring(1).toLowerCase();
					
				s+= "<div class='row'><div class='lang-heading'>"+
                    "<h5 class='primary-dark'>"+lname+"</h4>"+
                    "</div>"+
                    "<ul class='album-cards "+lid+"' >";
				
				String albumquery="select songs.aid,albums.aname,albums.poster,count(*) as albcount from songs,recents,albums where albums.aid=songs.aid and songs.sid=recents.sid and songs.lid=? group by aid order by songs.lid,albcount desc limit 5";
				PreparedStatement albumstmt = con.prepareStatement(albumquery);
				albumstmt.setString(1,lid);
				ResultSet albres = albumstmt.executeQuery();
				while(albres.next()) {
					String aid=albres.getString(1);
					String aaname=albres.getString(2);
					String aname = aaname.substring(0, 1).toUpperCase() + aaname.substring(1).toLowerCase();
					String url=albres.getString(3);
					
					s+= "<li class='album-card'>"+
						"<div id='album-name' class='card-div'>"+					
                        "<button name='"+aname+"' id='albumbutton' value='"+aid+"' >"+
                        "<img name='"+aname+"' src='"+url+"' alt='Loading..' class='img-fluid'>"+aname+
                        "</button>"+
                        "</div>"+
                        "</li>"+
                        "<input id='"+aid+"' type='hidden' value='"+url+"'>";
					
				}
				s+=" </ul></div>";	
				}
		
		con.close();
		return s;
		}
		
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
			
	
	
	
	
	
	
	
	public String searchMusic(String query) {
		String results="";
		
		
		try {	
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url,dbuname,dbpass);
			
			String album_res_query = "select aid,aname,poster,lid from albums where aname like '%"+query+"%'";
			
			
			PreparedStatement stmt = con.prepareStatement(album_res_query);
			
			
			ResultSet alb_res = stmt.executeQuery();
			
			while(alb_res.next()) {
				String aid=alb_res.getString(1);
				String albnamee=alb_res.getString(2);
				String albname = albnamee.substring(0, 1).toUpperCase() + albnamee.substring(1).toLowerCase();
				String posurl=alb_res.getString(3);
				String lid=alb_res.getString(4);
				System.out.print(lid);
				results+="<div> "
						+ "<button name='"+albname+"' class='album-btn "+lid+"' id='albumbutton' value='"+aid+"'>"+ albname+"</button>"
						+ "  <input id='"+aid+"' type='hidden' value='"+posurl+"' />"
						+ "</div>"; 	
			
			}
			
			
			String songs_res_query = "select a.aid,sname,aname,poster,a.lid from albums a,songs s where a.aid=s.aid and sname like '%"+query+"%'";
			
			PreparedStatement song_stmt = con.prepareStatement(songs_res_query);
			
			ResultSet song_res = song_stmt.executeQuery();
			
			while(song_res.next()) {
				String aid=song_res.getString(1);
				String snames=song_res.getString(2);
				String sname = snames.substring(0, 1).toUpperCase() + snames.substring(1).toLowerCase();
				String albnamee=song_res.getString(3);
				String aname = albnamee.substring(0, 1).toUpperCase() + albnamee.substring(1).toLowerCase();
				String posurl=song_res.getString(4);
				String lid=song_res.getString(5);
				results+="<div>"
						+ "<button name='"+aname+"' class='album-btn "+lid+"' id='albumbutton' value='"+aid+"'>"+ sname+"<sub>  &nbsp&nbsp&nbsp("+aname+")</sub> </button>"
						+ " <input id='"+aid+"' type='hidden' value='"+posurl+"' />"
						+ "</div>";
			}
			
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return results;
	}
	
	

}
