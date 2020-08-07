package com.renderelements;
import com.Music.dao.GetMusic;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Languages")
public class Languages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Languages() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		GetMusic gm = new GetMusic(); 
		String s = gm.getLanguages();
		out.print(s);	
		
		
		
	}


}
