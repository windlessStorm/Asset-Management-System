package com.smt.client.home;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Team  extends Composite{
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	private VerticalPanel vp = new VerticalPanel();
    AdminDashboard adminView = new AdminDashboard();
    TeamRequest teamReq = new TeamRequest(); 
    //method to show all the team request
    public VerticalPanel create(final String managerId){
    	
    	RootPanel.get("DashBoard").clear();
    	RootPanel.get("newReq").clear();
    	RootPanel.get("hwForm").clear();

    	 vp.clear();	 
    	 //title
	     vp.add(teamReq.onTeamDashboardTitle(managerId));
	     //body
	     teamReq.onTeamDashboardBody(managerId);
		  
		
		 
		vp.setStyleName("TableTitle");
				
		RootPanel.get("team").add(vp);	
		return vp;
		
    }
}
