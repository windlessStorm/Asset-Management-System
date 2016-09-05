package com.smt.client.login.user;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.home.Home;
import com.smt.client.home.HomeScreen;
import com.smt.client.login.Login;
import com.smt.client.login.session.SessionService;
import com.smt.client.login.session.SessionServiceAsync;
import com.smt.client.login.session.SetSessionCallback;

public class UserDAOCallback implements AsyncCallback<UserDAO>{
	Home d = new Home();
	Login l = new Login();
	SessionServiceAsync ss = GWT.create(SessionService.class);
	PropertyFileAccess error= GWT.create(PropertyFileAccess.class);
	HomeScreen hs = new HomeScreen();
	
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
				glassStyle.setProperty("backgroundColor", "#e8edea");
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
	
	
	private  class MyDialogSuccess extends DialogBox {

	      public MyDialogSuccess(final String error) {
	    	  
	    	  
	         // Set the dialog box's caption.
	         //setText("Manager Approval");
	         
	         // Enable animation.
	         setAnimationEnabled(true);

	         // Enable glass background.
	         setGlassEnabled(true);
	         

				Style glassStyle = getGlassElement().getStyle();
				glassStyle.setProperty("width", "100%");
				glassStyle.setProperty("height", "100%");
				glassStyle.setProperty("backgroundColor", "#e8edea");
				glassStyle.setProperty("opacity", "0.45");
				glassStyle.setProperty("mozOpacity", "0.45");
				glassStyle.setProperty("filter", " alpha(opacity=45)");

				 Label l,l1;
				VerticalPanel h = new VerticalPanel();
				h.clear();
				h.setStyleName("dialogErr");
				
				h.add(l = new Label(error));
				h.add(l1 = new Label("Press OK to Continue.."));
				l.setStyleName("successDial");
				l1.setStyleName("successDialHope");
					
					Button nothing = new Button("ok");
					nothing.setStyleName("dialreqBtn");
					h.add(nothing);
					nothing.setStyleName("errBtn");
					nothing.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							MyDialogSuccess.this.hide();

						}
					});

	         setWidget(h);
	      }
	   }


	@Override
	public void onFailure(Throwable caught) {
		//Window.alert(caught.getLocalizedMessage());
		String invaliduser = error.invaliduser();
		MyDialog myDialog = new MyDialog(invaliduser);

        int left = Window.getClientWidth()/3;
        int top = Window.getClientHeight()/ 3; 
        myDialog.setPopupPosition(left, top);
        myDialog.show();	
        
        l.onLogin();
	}

	@Override
	public void onSuccess(UserDAO result) {
		
		String success = error.success() + " " + result.getRole().toUpperCase() + " : " + result.getName().toUpperCase();
		MyDialogSuccess myDialog = new MyDialogSuccess(success);

        int left = Window.getClientWidth()/ 3;
        int top = Window.getClientHeight()/ 3; 
        myDialog.setPopupPosition(left, top);
		myDialog.show();
		
		
		ss.setSession(result.getUserName(), new SetSessionCallback());	
		hs.onHomeLoad(result);
		
	}	
}
