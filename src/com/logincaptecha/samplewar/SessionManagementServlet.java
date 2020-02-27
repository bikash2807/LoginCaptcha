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
import javax.swing.JEditorPane;

//import org.jboss.logging.Logger;

//import org.apache.taglibs.standard.lang.jstl.Logger;

@WebServlet("/SessionManagementServlet")

public class SessionManagementServlet extends HttpServlet {
	
	//static Logger log = Logger.getLogger(SessionManagementServlet.class);
	static Logger log = Logger.getAnonymousLogger();
	
	private static final long serialVersionUID = 1L;
	
	private final String usr = "admin";
	
    private final String pas = "password";

	public void processRequest(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		
		log.info("**********************Check log properties************");
		
		log.info("**********************Check log properties************");
		
		log.info("cehck JSESSIONID before user match : "+ request.getSession());
		
		log.info("check JSESSIONID before user match "+ request.getSession().getId());
		
		HttpSession session=request.getSession();
		
		
		
		log.info("*******************Check Session before create new  Session"+session);
		
		String usr=request.getParameter("user").trim();
		
		String pas=request.getParameter("pass").trim();
		
		String captcha=(String) session.getAttribute("captcha");
		
		log.info("*********** Get captcha : "+captcha );
		
		//HttpSession getSessionThroughMethod=HttpSessionCollector.GetSessionParameters(session.getId());
		//log.info("***Get Session through calling a method Passing sessionid as argument is : "+getSessionThroughMethod);
		//log.info("*******get CAPTCHA after calling a method by geting sesson passing sessionid as argumentis  : "+ getSessionThroughMethod.getAttribute("captcha") );
		
			if(usr.equals("keenable") && pas.equals("keenable@123")  ) {
				
				HttpSession oldSession = request.getSession(false);
				
				log.info("********** old sesson is : "+oldSession);
				
				if(oldSession != null) {
					
					oldSession.invalidate();
				}
				
				HttpSession newSession = request.getSession(true);
				
				//newSession.setMaxInactiveInterval(5*60);
				
				log.info("**********After Successful LoggedIn User New Sesson is : :"+newSession);
				
				newSession.setAttribute("sessionCaptcha", captcha);
				
				newSession.setAttribute("username",usr);
				
				newSession.setAttribute("password", pas);
				
				newSession.setAttribute("SessionID", newSession.getId());
				
				log.info("**** User details set in loggedin user Session : User : "+ newSession.getAttribute("username") +" Password: " + newSession.getAttribute("password") + " : "+newSession.getAttribute("sessionCaptcha")+ ":" +newSession.getId()+" : "+newSession);
				
				/*Cookie message = new Cookie("message", "Welcome");
				Cookie userName= new Cookie("cookieUser", usr);
	            response.addCookie(message);
	            response.addCookie(userName);*/
				
				response.sendRedirect("WelcomeServlet");
				
	            /*  RequestDispatcher rd=request.getRequestDispatcher("WelcomeServlet");
					
					rd.forward(request, response);*/ //
			}
			
			else {
				
				log.info("user credentioals not matcehd");
			
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
			 
				PrintWriter out = response.getWriter();
	         
				out.println("<font color=red>Either username,password or captecha is wrong.</font>");
	         
				rd.include(request, response);
			}
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
		
	}

}
