/**
 here we are taking the MainFunc.java
 this class implements the EntryPoint interface
 The EntryPoint interface implementing class is called EntryPoint
 class in the GWT
 This is the main class of the module
 This class is mentioned in the module descriptor in.
 This class is the main class for the module  
 */
package com.smt.client;

import com.google.gwt.core.client.EntryPoint;
import com.smt.client.autoLogout.InitAutoSession;
import com.smt.client.login.Login;


public class MainFunc implements EntryPoint {
	 InitAutoSession init = new InitAutoSession();

	@Override
	public void onModuleLoad() {
		/* we are craeting the object of the Login class
		 * */
		Login in = new Login();
		/*
		 * her e we are calling the onLogin() method of the  Login class
		 * 
		 * */
		in.onLogin();		
	}
	
	
	
}
