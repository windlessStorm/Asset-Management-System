/**
 * Here we are creating the employee dashboard table 
 * Here we defined two methods one is for the title bar and other is 
 * for the body.
 */

package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.EmpViewCallback;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class EmployeeDashboard {
	VerticalPanel v = new VerticalPanel();
	HorizontalPanel h1;
	Label l;
	//to access all the request by that employee
	ReqServiceAsync view = GWT.create(ReqService.class);

	public HorizontalPanel onEmpDashboardTitle() {
		
		h1 = new HorizontalPanel();
		h1.setStyleName("TableTitle");
		h1.add(l = new Label("Request ID"));
		l.setStyleName("dashBoardReq");
		h1.add(l = new Label("Product Name"));
		l.setStyleName("dashBoardPname");
		h1.add(l = new Label("Product Configuration"));
		l.setStyleName("dashBoardProdConfig");
		h1.add(l = new Label("Status"));
		l.setStyleName("dashBoardStatus");
		return h1;
	}
	
	public void onEmpDashboardBody(String empId) {
		//calling the method which is implemented in the RequestImp in server side
		view.ReqViewEmployee(empId, new EmpViewCallback());
	}
	
	
}
