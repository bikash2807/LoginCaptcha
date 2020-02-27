package com.logincaptecha.samplewar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionCollector implements HttpSessionListener {
	
	static Logger log = Logger.getAnonymousLogger();
	
    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    private int sessionCount = 0;
    
    @Override
	public void sessionCreated(HttpSessionEvent event) {
    	
		synchronized (this) {
			
			sessionCount++;
		}
			
		log.info("Session Count: " + sessionCount);
		
		HttpSession session = event.getSession();
		
		log.info("*** Get SESSION in Listenere class : "+" "+session);
		
		log.info("*** Get SESSION ID in Listenere class : "+" "+session.getId());
		
		log.info("*** Get CreationTime in Listenere class : "+" "+session.getCreationTime());
		
		log.info("*** Get LastAccessedTime in Listenere class : "+" "+session.getLastAccessedTime());
		
		log.info("*** Get GetMaxInactiveInterval in Listener class : "+session.getMaxInactiveInterval());
		
		sessions.put(session.getId(), session );
		
	}
    
    @Override
	public void sessionDestroyed(HttpSessionEvent event) {
    	//https://stackoverflow.com/questions/6070652/how-to-access-application-scope-in-http-session-listeners *** to remove attributes from invalid session	
		synchronized (this) {
			
			sessionCount--;
		}
		
		log.info("Session Destroyed: " + event.getSession().getId());
		
		log.info("Total Sessions: " + sessionCount);
	}
	
	public static HttpSession GetSessionParameters(String sessionId) {
		
		return (HttpSession) sessions.get(sessionId);
	}

}