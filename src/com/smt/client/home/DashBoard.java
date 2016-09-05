 /**
  * here we are declaring the DashBoard.class
  * in this class we are creating the different home page for the user 
  * according to the user's role name in the single class
  * example if the user login as a Admin then the admin homepage should be opened
  * 
  * */

package com.smt.client.home;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DashBoard  extends Composite{
	
	
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	private VerticalPanel vp = new VerticalPanel();
	//calling all the separate dashboard class 
    AdminDashboard adminView = new AdminDashboard();
    EmployeeDashboard empView = new EmployeeDashboard();
    
    //creates a dashboard and returns VerticalPanel
    public VerticalPanel CreateDash(final String role,final String userId){
    	
    	RootPanel.get("team").clear();
    	RootPanel.get("newReq").clear();
    	RootPanel.get("hwForm").clear();
    	RootPanel.get("DashBoard").clear();

    	String empId = userId;
    	//calling individual dashboard methods according to the role
    	 if(role.equals("admin"))
    	 {
    			RootPanel.get("vendorManagement").clear();;
    			RootPanel.get("menu").clear();
    			RootPanel.get("stock").clear();	 
    			RootPanel.get("poReply").clear();;

    	vp.clear();	 
    	vp.add(adminView.onAdminDashboardTitle());
    	adminView.onAdminDashboardBody();

    	 }
    	 else if(role.equals("manager")){
    		 vp.clear();	 
    	     vp.add(empView.onEmpDashboardTitle());
    	     empView.onEmpDashboardBody(empId);
    		 
    	 }
    	 else if(role.equals("employee")){
    		 vp.clear();	 
    	     vp.add(empView.onEmpDashboardTitle());
    	     empView.onEmpDashboardBody(empId);
      
    	 }
    	 else{
    		
    	 }
		RootPanel.get("DashBoard").add(vp);
		/*
		 * here we are setting the style for the 
		 * panels and widgets
		 * set Style is the provide the shape and color to the component
		 * so it becomes the more interesting and
		 * screen friendly
		 * we define the styling in the .css file and access that 
		 * simply calling the method setStyleName()
		 * and pass that name in the method*/
		vp.setStyleName("TableTitle");
    	
    	return vp;  	
    }
    }

