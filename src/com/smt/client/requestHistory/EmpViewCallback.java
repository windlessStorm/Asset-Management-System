/**
 * @author sumit
 * here we are showing all the requests made by the particular 
 * employee who will login as employee
 * we have created a table and there we are showing
 * are the related information associated with the request.
 */
package com.smt.client.requestHistory;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EmpViewCallback implements AsyncCallback<ReqDAO> {
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	 VerticalPanel v1 = new VerticalPanel();
	 VerticalPanel v3 = new VerticalPanel();
	 VerticalPanel v4 = new VerticalPanel();
	 VerticalPanel v5 = new VerticalPanel();
	 /*
		 * take the HoriZontal class and create the object of that class
		 * the horizontal panel is used to add the GWT widgets in the line Horizontally
		 * 
		 * 
		 * */
	HorizontalPanel h = new HorizontalPanel();
	 /*here we are taking the Label
	 * The Label can contains only arbitrary text 
	 * and it can not be interpreted as HTML. 
	 * This widget uses a <div> element, 
	 * causing it to be displayed with block layout.
	 * */
	Label l;
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getLocalizedMessage());

	}

	@Override
	public void onSuccess(ReqDAO emp) {
		
		//arraylists for receiving all the items from ReqDAO

		 ArrayList<String> requestId ;
		 ArrayList<String> productName ;
		 ArrayList<String> productConfig ;
		 ArrayList<String> status ;
		 
		 requestId = emp.getRequestId();
		 productName = emp.getProductName();
		 productConfig = emp.getProductConfig();
		 status = emp.getStatus();

		 RootPanel.get("adminDashboard").clear();;
	 	 h = new HorizontalPanel();
		 int count = 3; 
		 //content of the employee dashboard table
		 for(int i = 0 ; i < requestId.size() ; i++) {
			 	

				 String r = requestId.get(i);
				 String p = productName.get(i);
				 String pc = productConfig.get(i);
				 String s = status.get(i);
				 if(s == "NA") 
					 s = "Not Approved";
				 v1.add(l = new Label(r));
				 if(count % 2 == 0)
					 /*
						 * here we are setting the style for the 
						 * panels and widgets
						 * set Style is the provide the shape and color to the component
						 * so it becomes the more interesting and
						 * screen friendly
						 * we define the styling in the .css file and access that 
						 * simply calling the method setStyleName()
						 * and pass that name in the method*/
					 l.setStyleName("dashBoardReq");
				 else
					 l.setStyleName("dashBoardReqEven");
				 v3.add(l = new Label(p));
				 if(count % 2 == 0)
					 l.setStyleName("dashBoardPname");
				 else
					 l.setStyleName("dashBoardPnameEven");
				 v4.add(l = new Label(pc));
				 if(count % 2 == 0)
					 l.setStyleName("dashBoardProdConfig");
				 else
					 l.setStyleName("dashBoardProdConfigEven");
				 v5.add(l = new Label(s));
				 if(count % 2 == 0)
					 l.setStyleName("dashBoardStatus");
				 else
					 l.setStyleName("dashBoardStatusEven");
				 				 
		 }
		 h.add(v1);
		 h.add(v3);
		 h.add(v4);
		 h.add(v5);
		 h.setStyleName("TableBody");
		 RootPanel.get("adminDashboard").add(h);
	}
}
