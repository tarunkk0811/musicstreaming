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
 * Servlet implementation class Albums
 */
@WebServlet("/Albums")
public class Albums extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Albums() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		PrintWriter out=response.getWriter();
		
		response.setContentType("text/html");
		
		GetMusic gm = new GetMusic();
		
		String langclicked = request.getParameter("langclicked").toString();
		
		
		
		String s = gm.getAlbums(langclicked);
		
		//out.print(langclicked);
		out.print(s);		
		
		
	}

	

}
