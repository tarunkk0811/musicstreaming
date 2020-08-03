package com.renderelements;
import com.Music.dao.GetMusic;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Songs")
public class Songs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Songs() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		String s="";
		
		GetMusic gm = new GetMusic();
		
		String aid = request.getParameter("album");
		
		s=gm.getSongs(aid);
		
		out.print(s);
		
		
		
	}


}
