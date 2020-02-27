<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@ page session="false" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	 <%-- <script type="text/javascript">
  		var loggedInUser = "<%= (String)session.getAttribute("username")%>";
  		if(isLoggedIn !=null){
    	 window.location.href="WelcomeServlet";
 		 }
  	 </script> --%>
</head>
<body>
	<center>
		<form action="SessionManagementServlet" method="post">
	  		<table cellspacing="15">
	   			<tr>
	   				<td>User</td>
	    			<td><input type="text" name="user"/></td>
	  		    </tr>
	  		    <tr>
	   				<td>Password</td>
	   				<td><input type="password" name="pass"/></p></td>
	  			</tr>
	   			<tr>
	    			<td>Captcha</td>
	    			<td><input type="text" name="code"></td>
	   			</tr>
	  		</table><br>
	  		<!-- <img src="http://localhost:8081/LoginCaptecha/CaptchaServlet"> -->
	  		<img src="http://localhost:8082/LoginCaptecha/CaptchaServlet">
	  		<br><br>
	  		<input type="submit" value="Login">
		 </form>
	 </center>
	 
	 <%-- <c:out value="${sessionScope.username}"/> --%>
 	
 	<%
 	
 		
 	
 		HttpSession session = request.getSession(false); //&& session.getAttribute("username")!=null
 		
 		//System.out.println("******** >>>>>>>>>>>>>>>>>check captcha in JSP page 1: "+session.getAttribute("captcha"));
 		
 		//System.out.println("******** check session in JSP page : "+session);
 		
		//System.out.print("****************** check session work in JSP page : "+session.getAttribute("username")   );
 		
 	    if(session!=null) {
 	    	
 	    System.out.println("******** >>>>>>>>>>>>>>>>>check captcha in JSP page 2: "+session.getAttribute("captcha"));

 		System.out.println("********>>>>>>>>>>>>>>>>>>************ check user in JSP page : "+session.getAttribute("username"));
 	
 		System.out.println("******** check session Captcha in JSP page : "+session.getAttribute("sessionCaptcha"));
 		
    	if(session.getAttribute("username")!=null) {
    		
    		
    		//session.invalidate();
    		
    		
    		System.out.println("********* User after check user is not null to manage loggedIn User  : "+session.getAttribute("username"));
    		
    		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/WelcomeServlet"));
    		
    	    return;
    	    
    	}
 	    }
 	    /* else{
 	    	session.invalidate();
 	    	//response.sendRedirect("Login.jsp");
 	    }  */
  %>
 
</body>
</html>