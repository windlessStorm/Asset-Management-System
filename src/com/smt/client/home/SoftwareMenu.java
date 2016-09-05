/**
 * here in this class we are going to creat ea class for the 
 * Software menu selection
 * we will define all the method in that class
 * 
 * 
 * */


package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.ReqHistoryCallback;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class SoftwareMenu {
	
	/**
	 * here we are taking ReqServiceAsync interface type reference variable
	 * sessionService and calling GWT.create() method and passing the 
	 * Synchronous interface ReqService.class
	 * the gwt will map its path to the the corresponding servlet
	 * on the server side through the url pattern of the web.xml
	 * file.
	 * then we can call all the method of the the the corresponding servlet at the client side
	 * 
	 * */


	ReqServiceAsync request = GWT.create(ReqService.class);
	/**
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel form;
	/**
	 *  here we are defining the button widget
	 *  to add to perform click operation
	 *  The Button class, for example, publishes click events.
	 *   The associated handler interface is ClickHandler.
	 **/
	Button btn = new Button("Submit");
	/**
	 *  here we are  taking the Listbox class of the gwt
	 * to add the list of the items
	 *  the Listbox in the gwt is the
	 * The ListBox widget represents a list of choices
	 *  to the user, either as a list box or as a drop-down list.
	 *  */
	ListBox list = new ListBox();
	DashBoard d = new DashBoard();

	//custom dialog box
	private  class MyDialog extends DialogBox {

	      public MyDialog() {
	    	  
	    	  
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

	            
				VerticalPanel h = new VerticalPanel();
				h.clear();
				h.setStyleName("dialogReq");
				
				h.add(new Label("New Request Published Successfully"));
					
					Button nothing = new Button("ok");
					nothing.setStyleName("dialreqBtn1");
					h.add(nothing);
					nothing.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							MyDialog.this.hide();

						}
					});

	         setWidget(h);
	      }
	   }
	
	// define a method to select to software method
	public VerticalPanel swMenu(final String type, final String u, final String managerId, final String rolename) {
		/*
		 * take the HoriZontal class and create the object of that class
		 * the horizontal panel is used to add the GWT widgets in the line Horizontally
		 * 
		 * 
		 * */
		HorizontalPanel h1;
		
		Label l;
		form = new VerticalPanel();
		h1 = new HorizontalPanel();

		
		list.addItem("32 Bit");
		list.addItem("64 Bit");
		list.setStyleName("hwTextBox");
		
		h1.add(l = new Label("Select Product Version : "));
		h1.add(list);
		h1.setStyleName("h2PanelReq");
		l.setStyleName("hwLabel");
		
		form.add(h1);
		form.add(btn);
		btn.setStyleName("hwButton");
		requestButtonHandler(type,u, managerId, rolename);
		//RootPanel.get("hwForm").add(form);	
		return form;
	}
	
	public void requestButtonHandler(final String type, final String u, final String managerId, final String rolename) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String empId = u;
				String productName = type;
				String productConfig = prodConfig();
				String managerID = managerId;
			    request.insertReq(empId, productName, productConfig, managerID, new ReqHistoryCallback());
			    
			    MyDialog myDialog = new MyDialog();

	            int left = Window.getClientWidth()/ 3;
	            int top = Window.getClientHeight()/ 3; 
	            myDialog.setPopupPosition(left, top);
	            myDialog.show();
	            d.CreateDash(rolename, empId);
			
			}
		});
	}
	
	public String prodConfig() {
		return list.getSelectedItemText();
	}
}
