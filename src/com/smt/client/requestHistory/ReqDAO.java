/**
 * @author sumit
 * here we are taking the ReqDAO class which is the pojo class
 * a pojo class is that which does'nt depends on any other jar
 * or class
 * it has the setter and getters method
 * to store the data and target the data
 * we  have made it as implementing the Serializable interface
 * which makes it to transferable over the network
 * Serializable interface is an empty interface
 * we have to transfer data on the rpc call so we have made the UserDAo as
 * the Serializable class*/
package com.smt.client.requestHistory;

import java.io.Serializable;
import java.util.ArrayList;

public class ReqDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> requestId ;
	private ArrayList<String> empId ;
	private ArrayList<String> productName ;
	private ArrayList<String> productConfig ;
	private ArrayList<String> status ;
	private ArrayList<String> managerComment ;

	//getters and setters
	public ArrayList<String> getRequestId() {
		return requestId;
	}
	public void setRequestId(ArrayList<String> requestId) {
		this.requestId = requestId;
	}
	public ArrayList<String> getEmpId() {
		return empId;
	}
	public void setEmpId(ArrayList<String> empId) {
		this.empId = empId;
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
	public ArrayList<String> getStatus() {
		return status;
	}
	public void setStatus(ArrayList<String> status) {
		this.status = status;
	}
	public ArrayList<String> getManagerComment() {
		return managerComment;
	}
	public void setManagerComment(ArrayList<String> managerComment) {
		this.managerComment = managerComment;
	}
	

}
