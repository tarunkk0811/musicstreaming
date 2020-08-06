package com.admin;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.dao.SetMusicDao;
@WebServlet("/MusicData")
public class MusicData extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MusicData() {
        super();
    }
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
		
		String lid="",aid="",sid="";
		
		PrintWriter out = response.getWriter();
		
		String language=request.getParameter("lang").toUpperCase();
		
		String albumname=request.getParameter("album").toUpperCase();	
	
		String song=request.getParameter("sname").toUpperCase();
	
		String songurl=request.getParameter("url");
		
		String posterurl=request.getParameter("posterurl");
		
		SetMusicDao set = new SetMusicDao();
		
		lid=set.setLanguage(language);
		
		if(lid!="") {
			aid=set.setAlbum(lid, albumname, posterurl);
			
			if(aid!="" && songurl!="") {
				sid=set.setSongs(lid, aid, song, songurl);
				if(sid.equals("Already Exist")) {
					request.setAttribute("data",song+"Already Exist");
					
				}
				else 
				{
				request.setAttribute("data",song+" Successfully Inserted");
				
				}
				}
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request, response);
		
		
	}

}
