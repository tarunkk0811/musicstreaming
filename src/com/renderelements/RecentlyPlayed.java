package com.renderelements;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.dao.GetMusic;
import com.Music.dao.SetMusicDao;

@WebServlet("/RecentlyPlayed")
public class RecentlyPlayed extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public RecentlyPlayed() {
        super();

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String sid=request.getParameter("sid");
		String uid=request.getParameter("uidd");
		
		SetMusicDao sm = new SetMusicDao();
		if(sid== null);
		else {
		boolean res=sm.setRecents(uid, sid);
		}
		GetMusic gm = new GetMusic();
		String result = gm.getRecents(uid);
		out.print(result);	
		
		
	}


}

