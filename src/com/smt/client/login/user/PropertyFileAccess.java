/**
 * this is the property like class 
 * we have taken this class to access the property file
 * a property file keeps the data in the key and pair formats
 * gwt does'nt support the java.util.Property class
 * so gwt has given Constants interface
 * We have taken   PropertyFileAccess interface and extends it from 
 * the Constants interface
 * the Property file name and the interface name must be the same and in the 
 * same package in the client side
 * declare all the key of the property file s the method in this interface
 * */
package com.smt.client.login.user;

import com.google.gwt.i18n.client.Constants;

public interface PropertyFileAccess extends Constants{
	// defining all the key of property  as the method in this interface
	String unameandpas();
	String uname();
	String password();
	String invaliduser();
	String success();
}
