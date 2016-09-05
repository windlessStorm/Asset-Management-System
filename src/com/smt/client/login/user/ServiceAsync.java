/*
 * this is the ServiceAsync  interface 
 * this interface automTICALLY will be created by the 
 * gwt
 * this interface is used to make the rpc call s the Asynchronous
 * all the method of its corresponding interface will be added in this
 * interface
 * and return type must be the void
 * in its method it will insert AsyncCallback as a parameter 
 * this interface has two method 
 * 1. onfailure()... when the operation fails
 * 2.onSuccess()..... when the operation is successful
 * */
package com.smt.client.login.user;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceAsync {
	
	
	void login(String u, String p, AsyncCallback<UserDAO> callback);
	
	
}
