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

/**
 * Servlet implementation class Favourites
 */
@WebServlet("/Favourites")
public class Favourites extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("sid");
		int uid=Integer.parseInt((request.getParameter("uidd")));
//		String message=request.getParameter("mes");
		SetMusicDao set=new SetMusicDao();
		
		if(!sid.equals("") && sid!=null)
			set.setfav(uid, sid);
		GetMusic gm = new GetMusic();
	
		String result=gm.getFavs(uid);
		PrintWriter out = response.getWriter();
		out.print(result);
		
		
	}
}
