package com.smt.client.requestHistory;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ReqHistoryCallback implements AsyncCallback<ReqDAO>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getLocalizedMessage());
	}

	@Override
	public void onSuccess(ReqDAO result) {

	}
}
