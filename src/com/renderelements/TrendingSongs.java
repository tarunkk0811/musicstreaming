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
 * Servlet implementation class TrendingSongs
 */
@WebServlet("/TrendingSongs")
public class TrendingSongs extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TrendingSongs() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		GetMusic gm = new GetMusic();
		String result = gm.getTrendingSongs();
		out.print(result);	
		
	}


}
