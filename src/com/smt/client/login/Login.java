/**
 * here we are going to define a  class Login.java
 * this will create the Login screen on the browser
 * where the user will enter in the application
 * */

package com.smt.client.login;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1Digest;
import com.smt.client.login.user.ErrorMsg;
import com.smt.client.login.user.Service;
import com.smt.client.login.user.ServiceAsync;
import com.smt.client.login.user.UserDAOCallback;


public class Login {
	// take the text box to enter the user name
	 private TextBox userName; 
	 // take the password text box to enter the password
	 private PasswordTextBox password;
	 // take the Buton to submit user credential
	 private Button btn1;
	 private VerticalPanel vpanel=new VerticalPanel();
	 private HorizontalPanel h1,h2;
	 private Label l1,l2;
	 ServiceAsync users = GWT.create(Service.class);
	 /*
	  * here we are creating object of the ErrorMsg
	  * we have defined this to maintain property file
	  * in this property file we are keeping the message that will be 
	  * shown on the login screen when the 
	  * user enters the wrong credential
	  * we can change message what i want to see on the screen
	  * 
	  * the property file name must be the same as the class name
	  * and must be in same package
	  * */
	 ErrorMsg error = new ErrorMsg();

	 
	// define a method to which cont ains the logic to create the login screen
	public void onLogin() {
		 
		 vpanel.add(l1 = new Label("Login to Asset Management System"));	
		 l1.setStyleName("labelLogin");
	     h1 = new HorizontalPanel();
	     h1.add(l2 = new Label("Username"));
	     h1.add(userName=new TextBox());
	     userName.setFocus(true);
	     userName.setStyleName("txtStyle");
	     l2.setStyleName("loginLabel");
	     
	     h2 = new HorizontalPanel();
	     h2.add(l2 = new Label(" Password "));
	     h2.add(password=new PasswordTextBox());
	     password.setFocus(true);
	     password.setStyleName("txtPass");
	     l2.setStyleName("loginLabel");
	     
	     vpanel.add(h1);
	     vpanel.add(h2);
	     vpanel.add(btn1=new Button("LOGIN"));
	    
	     
	     btn1.setStyleName("btnStyle");
	     buttonHandler(btn1);
	     keyHandler();
	     vpanel.setStyleName("tabStyle");
	     
	     RootPanel.get("loginEntry").add(vpanel);
		}
	
	private void keyHandler() {
		// Listen for keyboard events in the input box.
		//log.info("Listen for keyboard events in the input box");
	    password.addKeyDownHandler(new KeyDownHandler() {
	      public void onKeyDown(KeyDownEvent event) {
	        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	        	    	 String UserName=userName.getValue();
			              String Password=password.getValue();
			              String pass = getSHA1for(Password);
			              
			              if(UserName.isEmpty() || Password.isEmpty()) {
			            	  error.onError(UserName, pass);
			            	  userName.setValue("");
			            	  password.setValue("");
			              }
			              else {
			            	  users.login(UserName, pass, new UserDAOCallback()); 
			            	  RootPanel.get("loginEntry").clear();
			              }
	        	    	}
	      }
	    });
	}
		

		void buttonHandler(Button btn){
				 btn.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {	
							//log.info("Listen for Button events in the input box");
						//RootPanel.get("loginEntry").clear();	
					      String UserName=userName.getValue();
			              String Password=password.getValue();
			              String pass = getSHA1for(Password);
			              
			              if(UserName.isEmpty() || Password.isEmpty()) {
			            	  error.onError(UserName, Password);
			            	  userName.setValue("");
			            	  password.setValue("");
			              }
			              else {
			            	  users.login(UserName, pass, new UserDAOCallback()); 
			            	  RootPanel.get("loginEntry").clear();
			              }
						}
					});
		}
		//for password encryption
		String getSHA1for(String text) {
			  SHA1Digest sd = new SHA1Digest();
			  byte[] bs = text.getBytes();
			  sd.update(bs, 0, bs.length);
			  byte[] result = new byte[20];
			  sd.doFinal(result, 0);
			  return byteArrayToHexString(result);
			}

			String byteArrayToHexString(final byte[] b) {
			  final StringBuffer sb = new StringBuffer(b.length * 2);
			  for (int i = 0, len = b.length; i < len; i++) {
			    int v = b[i] & 0xff;
			    if (v < 16) sb.append('0');
			    sb.append(Integer.toHexString(v));
			  }
			  return sb.toString();
			}	
	}
