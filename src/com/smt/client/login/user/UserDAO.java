/**
 * here we are taking the UserDAO class which is the pojo class
 * a pojo class is that which does'nt depends on any other jar
 * or class
 * it has the setter and getters method
 * to store the data and target the data
 * we  have made it as implementing the Serializable interface
 * which makes it to transferable over the network
 * Serializable interface is an empty interface
 * we have to transfer data on the rpc call so we have made the UserDAo as
 * the Serializable class*/
package com.smt.client.login.user;

import java.io.Serializable;

public class UserDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String role;
	private String name;
	private String managerId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login(String u, String p) {
		return p;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
