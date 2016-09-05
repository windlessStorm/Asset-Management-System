package com.smt.client.login.session;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;


public class LogoutCallback implements AsyncCallback<Void> {
	//InitAutoSession init = new InitAutoSession();
	//Login in = new Login();
	//if anything goes wrong
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Session does'nt expires successfully!!");
	}
	//if operation successful
	@Override
	public void onSuccess(Void result) {
		RootPanel.get("dashboard").clear();
		RootPanel.get("hwForm").clear();
		RootPanel.get("newReq").clear();
		RootPanel.get("team").clear();
		RootPanel.get("DashBoard").clear();
		RootPanel.get("menu").clear();
		RootPanel.get("adminDashboard").clear();
		RootPanel.get("stock").clear();
		Window.Location.reload();
		//in.onLogin();
	}
}
