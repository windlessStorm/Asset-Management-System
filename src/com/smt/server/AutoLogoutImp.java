/**
 *  this class is the implementation class of the AutoLogout
 * interface of the client side
 * it extends RemoteServiceServlet servlet 
 * we have implemented all the business logic and dev operation here in this class
 * 
 * */
package com.smt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smt.client.autoLogout.AutoLogoutService;

public class AutoLogoutImp extends RemoteServiceServlet implements AutoLogoutService {


	private static final long serialVersionUID = 1L;

	private int timeout;
	private boolean isAlive;
	//starting httpSession time out as per minute
	@Override
	public Integer getUserSessionTimeout() {
		timeout = getThreadLocalRequest().getSession().getMaxInactiveInterval()*1000;
		return timeout;
	}
	//boolean methods returns whether the session is still alive or not.
	@Override
	public Boolean isSessionAlive() {
		isAlive = new Boolean(System.currentTimeMillis() - getThreadLocalRequest()
				.getSession().getLastAccessedTime() < timeout );
		return isAlive;
	}

}
