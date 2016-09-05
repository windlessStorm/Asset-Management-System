/**
 * @author sumit
 *  here we are taking the VendorListDAO class which is the pojo class
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

package com.smt.client.admin;

import java.io.Serializable;
import java.util.ArrayList;

public class VendorListDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<String> vendorId;
	private ArrayList<String> vendorName;
	private ArrayList<String> vendorType;
	
	//setter getter for all the data type
	public ArrayList<String> getVendorId() {
		return vendorId;
	}
	public void setVendorId(ArrayList<String> vendorId) {
		this.vendorId = vendorId;
	}
	public ArrayList<String> getVendorName() {
		return vendorName;
	}
	public void setVendorName(ArrayList<String> vendorName) {
		this.vendorName = vendorName;
	}
	public ArrayList<String> getVendorType() {
		return vendorType;
	}
	public void setVendorType(ArrayList<String> vendorType) {
		this.vendorType = vendorType;
	}
}
