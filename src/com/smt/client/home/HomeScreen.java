/**
 * here we are combing Home class which has basically the header
 * and the dashboard class to show dashboard with header when the user logs in
 */
package com.smt.client.home;

import com.smt.client.login.user.UserDAO;
import com.smt.client.vendor.PORequestsView;

public class HomeScreen {
	
	Home h = new Home();
	DashBoard d = new DashBoard();
	PORequestsView poview = new PORequestsView();
	
	public void onHomeLoad(UserDAO user) {
		h.onDashboardLoad(user);
		if(user.getRole().equals("vendor"))
			poview.onPORequests(user.getUserName());
		else
			d.CreateDash(user.getRole(), user.getUserName());
	}
}
