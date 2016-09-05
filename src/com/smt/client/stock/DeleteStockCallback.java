package com.smt.client.stock;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DeleteStockCallback implements AsyncCallback{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getLocalizedMessage());
	}

	@Override
	public void onSuccess(Object result) {
	}

}
