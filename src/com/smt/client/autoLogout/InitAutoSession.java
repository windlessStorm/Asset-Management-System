package com.smt.client.autoLogout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smt.client.login.Login;

/* here in this class we are taking the class
 * InitAutoSession 
 * in this class we are calling all the method of the server side to 
 * perform session management*/
public class InitAutoSession {
	// create the Login class object
	Login login = new Login();
	
	/*
	 * here we are taking AutoLogoutServiceAsync interface type reference variable
	 * sessionService and calling GWT.create() method and passing the 
	 * Synchronous interface AutoLogoutService.class
	 * the gwt will map its path to the the corresponding servlet
	 * on the server side through the url pattern of the web.xml
	 * file.
	 * then we can call all the method of the the the corresponding servlet at the client side
	 * 
	 * */
	AutoLogoutServiceAsync sessionService = GWT.create(AutoLogoutService.class);
	public  Timer sessionTimeoutResponseTimer;
	int timeoutCycle;
	 public void initSessionTimer() {
		 sessionService.getUserSessionTimeout(new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer timeout) {
				timeoutCycle = timeout; 
				sessionTimeoutResponseTimer = new Timer() {
					
					@Override
					public void run() {
					isSessionAlive();
					}
					};
					sessionTimeoutResponseTimer.schedule(timeout);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
					System.out.println(caught.getLocalizedMessage());
				
			}
		});
		 
	 }
	 
	 public void isSessionAlive() {
			sessionService.isSessionAlive(new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println(caught.getLocalizedMessage());
				}

				@Override
				public void onSuccess(Boolean alive) {
					if(alive.booleanValue()) {
						sessionTimeoutResponseTimer.cancel();
						sessionTimeoutResponseTimer.scheduleRepeating(timeoutCycle);
					}
					else {
						Window.alert("Session Expired!!\nYou will be redirected to Login Page..");
						RootPanel.get("dashboard").clear(true);
						Window.Location.reload();
						sessionTimeoutResponseTimer.cancel();	
					}
				}
			});
		}
}
