/**
 * @author sumit
 * here we are taking the VendorReqDAO class which is the pojo class
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
package com.smt.client.vendor;

import java.io.Serializable;
import java.util.ArrayList;

public class VendorReqDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> reqId ;
	private ArrayList<String> venId;
	private ArrayList<String> productType ;
	private ArrayList<String> productConfig ;
	private ArrayList<String> quantity ;
	private ArrayList<String> vendorComment ;
	private ArrayList<String> status ;

	//getters and setters
	public ArrayList<String> getReqId() {
		return reqId;
	}
	public void setReqId(ArrayList<String> reqId) {
		this.reqId = reqId;
	}
	public ArrayList<String> getProductType() {
		return productType;
	}
	public void setProductType(ArrayList<String> productType) {
		this.productType = productType;
	}
	public ArrayList<String> getProductConfig() {
		return productConfig;
	}
	public void setProductConfig(ArrayList<String> productConfig) {
		this.productConfig = productConfig;
	}
	public ArrayList<String> getQuantity() {
		return quantity;
	}
	public void setQuantity(ArrayList<String> quantity) {
		this.quantity = quantity;
	}
	public ArrayList<String> getVendorComment() {
		return vendorComment;
	}
	public void setVendorComment(ArrayList<String> vendorComment) {
		this.vendorComment = vendorComment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ArrayList<String> getStatus() {
		return status;
	}
	public void setStatus(ArrayList<String> status) {
		this.status = status;
	}
	public ArrayList<String> getVenId() {
		return venId;
	}
	public void setVenId(ArrayList<String> venId) {
		this.venId = venId;
	}

}
