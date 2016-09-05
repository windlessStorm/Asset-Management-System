/**
 * @author sumit
 * Here we are implementing all the unimplemented 
 * method in the SessionService interface.
 * It extends RemoteServiceServlet so it will act like a j2ee
 * servlet.
 */
package com.smt.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smt.client.login.session.SessionService;

public class SessionImp extends RemoteServiceServlet implements SessionService{
   
	private static final long serialVersionUID = 1L;
	
	/**
	 * here we are setting the HttpSession according to the userid.
	 * it will valid until the user log outs.
	 * 
	 */
	@Override
	public void setSession(String user) {
		 HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	     HttpSession session = httpServletRequest.getSession(true);
	     session.setMaxInactiveInterval(1*60);
	     session.setAttribute("user", user);
	}
	
	/**
	 * Here we are removing the session attribute for that
	 * user.When the session will expire by reloading the page it 
	 * will go to the onModuleLoad Entry class.
	 */
	@Override
	public void exitSession() {
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("user"); 
	}
}
