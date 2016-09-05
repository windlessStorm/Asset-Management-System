/**
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
package com.smt.client.requestHistory;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReqServiceAsync {

	void insertReq(String empId, String productName, String productConfig, String managerId,
			AsyncCallback<ReqDAO> callback);

	void ReqViewAdmin(AsyncCallback<ReqDAO> callback);

	void ReqViewEmployee(String empId, AsyncCallback<ReqDAO> callback);

	void ReqViewManager(String maanagerId, AsyncCallback<ReqDAO> callback);

	void managerAproval(String reqId, String status, String managerCmnt, AsyncCallback<Void> callback);

	void adminAproval(String reqId, AsyncCallback<Void> callback);

	void adminPending(String reqId, AsyncCallback<Void> callback);

	void sortStatus(String statusName, String managerId, AsyncCallback<ReqDAO> callback);

	void sortStatusAdmin(String statusName, AsyncCallback<ReqDAO> callback);
}
