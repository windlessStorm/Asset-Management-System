package com.smt.client.requestHistory;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AdminAprovalCallback implements AsyncCallback {

	@Override
	public void onFailure(Throwable caught) {
		System.out.println(caught.getLocalizedMessage());
	}

	
	@Override
	public void onSuccess(Object result) {
		
	}
}
