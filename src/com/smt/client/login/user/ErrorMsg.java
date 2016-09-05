/**
 * @author sumit
 *  here we are taking the class to maintain the property file
 * */
package com.smt.client.login.user;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ErrorMsg {
	PropertyFileAccess error= GWT.create(PropertyFileAccess.class);
   // Login log = new Login();
	//custom dialog box
	private  class MyDialog extends DialogBox {

	      public MyDialog(final String error) {
	    	  
	    	  
	         // Set the dialog box's caption.
	         //setText("Manager Approval");
	         
	         // Enable animation.
	         setAnimationEnabled(true);

	         // Enable glass background.
	         setGlassEnabled(true);
	         

				Style glassStyle = getGlassElement().getStyle();
				glassStyle.setProperty("width", "100%");
				glassStyle.setProperty("height", "100%");
				glassStyle.setProperty("backgroundColor", "#86898c");
				glassStyle.setProperty("opacity", "0.45");
				glassStyle.setProperty("mozOpacity", "0.45");
				glassStyle.setProperty("filter", " alpha(opacity=45)");

	            Label l,l1;
				VerticalPanel h = new VerticalPanel();
				h.clear();
				h.setStyleName("dialogErr");
				
				h.add(l = new Label(error));
				h.add(l1 = new Label("Press OK to Try Again.."));
				l.setStyleName("erroDial");
				l1.setStyleName("erroDialHope");
				Button nothing = new Button("ok");
					nothing.setStyleName("dialreqBtn");
					h.add(nothing);
					nothing.setStyleName("errBtn");
					nothing.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							MyDialog.this.hide();

						}
					});

	         setWidget(h);
	      }
	   }
	
	public void onError(String userName, String password) {
		  String unameandpas = error.unameandpas();
          String uname = error.uname();
          String pass =error.password();
         
          // if the user name and password is the empty
	     if(userName.isEmpty() && password.isEmpty()){

	    	 MyDialog myDialog = new MyDialog(unameandpas);

	            int left = Window.getClientWidth()/ 3;
	            int top = Window.getClientHeight()/ 3; 
	            myDialog.setPopupPosition(left, top);
	            myDialog.show();
	            

        }
	     // if the user name   is the empty
        else if(userName.isEmpty()){
        	
        	 MyDialog myDialog = new MyDialog(uname);

	            int left = Window.getClientWidth()/ 3;
	            int top = Window.getClientHeight()/ 3; 
	            myDialog.setPopupPosition(left, top);
	            myDialog.show();

        }
	     // if the paaasord is the empty
        else if(password.isEmpty()){
        	
        	    MyDialog myDialog = new MyDialog(pass);

	            int left = Window.getClientWidth()/ 3;
	            int top = Window.getClientHeight()/ 3; 
	            myDialog.setPopupPosition(left, top);
	            myDialog.show();
        }
	}
	
}
