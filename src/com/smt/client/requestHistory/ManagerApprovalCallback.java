package com.smt.client.requestHistory;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ManagerApprovalCallback implements AsyncCallback{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getLocalizedMessage());
	}

	@Override
	public void onSuccess(Object result) {
	}
	
}
