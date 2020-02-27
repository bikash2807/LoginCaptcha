package com.logincaptecha.samplewar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

@WebServlet("/Logoutservlet")

public class Logoutservlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(Logoutservlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html");
		
		PrintWriter pr = response.getWriter();
		
		try{
			
		HttpSession logutSession=request.getSession(false);
		
		if(logutSession!=null) {
		
			log.info("Session in logout page : "+logutSession);
		
		if(request.getParameter("logout") != null) {
			logutSession.invalidate();
		}
		
		else {
			
			response.sendRedirect("Logout.html");
			//response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Login.jsp"));
		}
		
		log.info("invalidate session is : "+logutSession);
		log.info("**** context path is :  "+request.getContextPath());
		response.sendRedirect("Logout.html");
		
		pr.close();
		}
		else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Login.jsp"));
			//response.sendRedirect("Login.jsp");
		}
		
		}
		
		
    catch(Exception e){
    	
    	System.out.println(e);
    }

	}
}
