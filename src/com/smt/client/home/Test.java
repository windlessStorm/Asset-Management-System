package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.smt.client.requestHistory.AdminViewCallback;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class Test {
	
	ReqServiceAsync view = GWT.create(ReqService.class);
	
	public void onTest() {
		view.ReqViewAdmin(new AdminViewCallback());
	}
}
