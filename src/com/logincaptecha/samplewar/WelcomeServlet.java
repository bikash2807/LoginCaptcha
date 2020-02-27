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
//import org.jboss.logging.Logger;

@WebServlet("/WelcomeServlet")

public class WelcomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//static Logger log = Logger.getLogger(SessionManagementServlet.class);
	static Logger log = Logger.getAnonymousLogger();
	
	String message = null;
	
	String sessionID = null;

	public void processRequest(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		
			response.setContentType("text/html");
			
			//Cookie[] cookies = request.getCookies();
			
			PrintWriter out = response.getWriter();
		
			HttpSession loginSuccessSession = request.getSession();
			
			Cookie[] cookies=request.getCookies();
			
			String cookieJSESSIONID="";
			
			int i =0;
			
			for (Cookie cookie : cookies ) {
				  
				//System.out.print(cookies[i].getName()+ " : ");
				//System.out.println(cookies[i].getValue());
				if(cookie.getName().equals("JSEESIONID"))
					log.info("************************: "+cookie );
					  cookieJSESSIONID=cookie.getValue();
				  i++;
				}
			
			log.info("JSESSIONID form cookie : "+cookieJSESSIONID);
			
			log.info("Session in welcome page : "+loginSuccessSession);
			
			//cookie operation
			/*log.info("* Session with cookies : "+sessionID);
			log.info("* Session with cookies checking substring"+sessionID.substring(0 , sessionID.length() - 5));
			if(sessionID.equals(loginSuccessSession.getId())) {
				log.info("****cookies http session matced*****");
				out.println("<center><p> "+"***"+"</p></center>");
			}
			else {
				log.info("**** Cookie and httpsessison id not matched");
			}*/
			
			
			String user = null;
			
			if(loginSuccessSession.getAttribute("username") == null){
				
				response.sendRedirect("Login.jsp");
			}
			else {
				
				user = (String) loginSuccessSession.getAttribute("username");
			}
			
			//cookie operation
			
			out.println("<center>LogedIn User : " + loginSuccessSession.getAttribute("username")+"</center></br>");
			
			//out.println("<center>LogedIn User by cookie : " +message +" : "+ userName+"</center>");
			
			out.println("<p> Welcome Page  Session : "+loginSuccessSession+"</p>");
			
			out.println("<p>SessionId is  : " + loginSuccessSession.getId()+"<p>");
			
			//out.println("<p>SessionId is by cookie  : " + sessionID+"<p>");
			
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


