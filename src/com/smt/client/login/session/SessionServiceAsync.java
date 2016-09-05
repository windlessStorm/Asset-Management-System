package com.smt.client.login.session;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SessionServiceAsync {

	void exitSession(AsyncCallback<Void> callback);

	void setSession(String u, AsyncCallback<Void> callback);

}
