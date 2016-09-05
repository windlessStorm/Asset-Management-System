/**
 * here we are taking the AdminDashBoard class
 * we will create the dashboard for the admin
 * in the dashboard the admin will see all the request made by the employee
 * and manager
 * */

package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.AdminViewCallback;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class AdminDashboard {
	
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
	
	ReqServiceAsync view = GWT.create(ReqService.class);
	
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel v = new VerticalPanel();
	/*
	 * take the HoriZontal class and create the object of that class
	 * the horizontal panel is used to add the GWT widgets in the line Horizontally
	 * 
	 * 
	 * */
	HorizontalPanel h1;
	Label l;
	ListBox li = new ListBox();
		// define method which contains the logic to create the 
		// dashboard for the admin
	
	public HorizontalPanel onAdminDashboardTitle() {
		
		h1 = new HorizontalPanel();
		
		/**
		 * here we are setting the style for the 
		 * panels and widgets
		 * set Style is the provide the shape and color to the component
		 * so it becomes the more interesting and
		 * screen friendly
		 * we define the styling in the .css file and access that 
		 * simply calling the method setStyleName()
		 * and pass that name in the method*/
		
		h1.setStyleName("TableTitle");
		// label is just to name to any widget
		h1.add(l = new Label("Request ID"));
		l.setStyleName("dashBoardReq");
		h1.add(l= new Label("Employee ID"));
		l.setStyleName("dashBoardEmp");
		h1.add(l = new Label("Product Name"));
		l.setStyleName("dashBoardPname");
		h1.add(l = new Label("Product Configuration"));
		l.setStyleName("dashBoardProdConfig");
		h1.add(l = new Label("Status"));
		l.setStyleName("dashBoardStatus");
		li.clear();
		li.addItem("All");
		li.addItem("Not Approved");
		li.addItem("Approve");
		li.addItem("Granted");
		li.addItem("Reject");
		li.addItem("pending");
		h1.add(new Label("Sort"));
		h1.add(li);
		listHandler();
		return h1;
	}
	
	public void onAdminDashboardBody() {
		//	adminView.ReqViewAdmin(new AdminViewCallback());
		view.ReqViewAdmin(new AdminViewCallback());
	}
	//list handler to handle approval status
	private void listHandler() {
		li.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String statusName = li.getSelectedItemText(); 
					if(statusName == "Not Approved")
						statusName = "NA";
				if(statusName == "All") 
					view.ReqViewAdmin(new AdminViewCallback());
				else
					view.sortStatusAdmin(statusName, new AdminViewCallback());	
			}
		
		});
	}
}
