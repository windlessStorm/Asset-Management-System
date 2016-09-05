/**
 * this class is implementing the AsyncCallback interface
 * when the method of the interface called then this class is executed
 * 
 * */
package com.smt.client.login.session;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SetSessionCallback  implements AsyncCallback<Void>{
	
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Not Working");
	}

	@Override
	public void onSuccess(Void result) {
		
	}

}
