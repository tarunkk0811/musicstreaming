package com.renderelements;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Music.dao.*;
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	GetMusic gm = new GetMusic();
    	
    	PrintWriter out = response.getWriter();
    	
    	String query = request.getParameter("searchvalue");
    	System.out.print(query);
    	//out.print(query);
    	if(query!="") {
    	String resultant_tag = gm.searchMusic(query);
    	
    	out.print(resultant_tag);
    	}
	}

}
