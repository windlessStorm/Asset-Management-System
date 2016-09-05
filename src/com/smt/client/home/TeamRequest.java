package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;
import com.smt.client.requestHistory.TeamViewCallback;

public class TeamRequest {
	
	VerticalPanel v = new VerticalPanel();
	/*
	 * take the HoriZontal class and create the object of that class
	 * the horizontal panel is used to add the GWT widgets in the line Horizontally
	 * 
	 * 
	 * */
	HorizontalPanel h1;
	Label l;
	ReqServiceAsync view = GWT.create(ReqService.class);
	String statusName;
	/* here we are  taking the Listbox class of the gwt
	 * to add the list of the items
	 *  the Listbox in the gwt is the
	 * The ListBox widget represents a list of choices
	 *  to the user, either as a list box or as a drop-down list.
	 *  */
	ListBox li = new ListBox();
	//method to display team request table title
public HorizontalPanel onTeamDashboardTitle(final String managerId) {
		
	
	h1 = new HorizontalPanel();
	h1.setStyleName("TableTitle");
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
	
	listHandler(managerId);
	h1.add(new Label("Sort"));
	h1.add(li);
	return h1;
	}
	//team request body
	public void onTeamDashboardBody(final String managerId) {
		String managerID = managerId;
		
		view.ReqViewManager(managerID, new TeamViewCallback());
	}
	//for shoting purpose
	private void listHandler(final String managerId) {
		li.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String statusName = li.getSelectedItemText(); 
					if(statusName == "Not Approved")
						statusName = "NA";
				if(statusName == "All")
					view.ReqViewManager(managerId, new TeamViewCallback());
				else
					view.sortStatus(statusName, managerId , new TeamViewCallback());	
			}
		
		});
	}
}
