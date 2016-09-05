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
package com.smt.client.stock;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockServiceAsync {

	void checkStock(String reqId, String productName, AsyncCallback<StockDao> callback);

	void deleteStock(String productId, AsyncCallback<Void> callback);

	void sortStock(String pname, AsyncCallback<StockDao> callback);

	void viewStock(AsyncCallback<StockDao> callback);

	void insertStock(ArrayList<String> pid, ArrayList<String> pname, ArrayList<String> pconfig,
			ArrayList<String> warranty, AsyncCallback<Void> callback);



}
