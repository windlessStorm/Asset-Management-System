/**
 * @author sumit
 * POServiceAsync.java : Its the asynchronous interface which takes
 * 						 or give value to client side.	
 */
package com.smt.client.admin;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smt.client.vendor.VendorReqDAO;

public interface POServiceAsync {
	//all this methods will be called to client side at respective position
	void PORequest(ArrayList<String> vendorid, String productType, String productConfig, int quantity,
			AsyncCallback<Void> callback);

	void vendorPODashboard(String vid, AsyncCallback<VendorReqDAO> callback);

	void vendorComment(String vendorComment, String reqId, AsyncCallback<Void> callback);

	void adminViewVendorReply(AsyncCallback<VendorReqDAO> callback);

	void adminReplyToVendor(String reqId, String status, AsyncCallback<Void> callback);

}
