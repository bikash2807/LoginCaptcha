package com.logincaptecha.samplewar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WelcomeServlet")

public class WelcomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getAnonymousLogger();
	
	String message = null;
	
	String sessionID = null;

	public void processRequest(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();	
			HttpSession loginSuccessSession = request.getSession();
			Cookie[] cookies=request.getCookies();
			String cookieJSESSIONID="";
			int i =0;
			for (Cookie cookie : cookies ) {
				if(cookie.getName().equals("JSEESIONID"))
					log.info("************************: "+cookie );
					  cookieJSESSIONID=cookie.getValue();
				  i++;
				}
			log.info("JSESSIONID form cookie : "+cookieJSESSIONID);
			log.info("Session in welcome page : "+loginSuccessSession);
			String user = null;
			if(loginSuccessSession.getAttribute("username") == null){
				response.sendRedirect("Login.jsp");
			}
			else {
				user = (String) loginSuccessSession.getAttribute("username");
			}
			out.println("<center>LogedIn User : " + loginSuccessSession.getAttribute("username")+"</center></br>");
			out.println("<p> Welcome Page  Session : "+loginSuccessSession+"</p>");
			out.println("<p>SessionId is  : " + loginSuccessSession.getId()+"<p>");
			out.print("<form action='Logoutservlet' method=post>");
			out.print("<input type='submit' value='Logout' name='logout'> ");
			out.print("</form>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);	
	}

}


