package com.renderelements;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Music.dao.GetMusic;

/**
 * Servlet implementation class TrendingAlbums
 */
@WebServlet("/TrendingAlbums")
public class TrendingAlbums extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public TrendingAlbums() {
        super();
     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		GetMusic gm = new GetMusic();
		String s=gm.getTrendingAlbums();
		out.print(s);
		
	}

}
