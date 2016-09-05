/**
 * @author sumit
 * here we are taking the StockDAO class which is the pojo class
 * a pojo class is that which does'nt depends on any other jar
 * or class
 * it has the setter and getters method
 * to store the data and target the data
 * we  have made it as implementing the Serializable interface
 * which makes it to transferable over the network
 * Serializable interface is an empty interface
 * we have to transfer data on the rpc call so we have made the UserDAo as
 * the Serializable class
 */
package com.smt.client.stock;

import java.io.Serializable;
import java.util.ArrayList;

public class StockDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> productId ;
	private ArrayList<String> productName ;
	private ArrayList<String> productConfig ;
	private ArrayList<String> warranty;
	private String reqId;
	
	//getters and setters
	public ArrayList<String> getProductId() {
		return productId;
	}
	public void setProductId(ArrayList<String> productId) {
		this.productId = productId;
	}
	public ArrayList<String> getProductName() {
		return productName;
	}
	public void setProductName(ArrayList<String> productName) {
		this.productName = productName;
	}
	public ArrayList<String> getProductConfig() {
		return productConfig;
	}
	public void setProductConfig(ArrayList<String> productConfig) {
		this.productConfig = productConfig;
	}
	public ArrayList<String> getWarranty() {
		return warranty;
	}
	public void setWarranty(ArrayList<String> warranty) {
		this.warranty = warranty;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
}
