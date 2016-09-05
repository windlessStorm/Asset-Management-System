/**
 * here we are taking the Home class 
 * in this class we are going to create the Home page for the every
 * login user according to
 * role name
 * 
 * */

package com.smt.client.home;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.admin.VendorManagement;
import com.smt.client.login.session.LogoutCallback;
import com.smt.client.login.session.SessionService;
import com.smt.client.login.session.SessionServiceAsync;
import com.smt.client.login.user.UserDAO;
import com.smt.client.stock.StockSort;
import com.smt.client.vendor.PORequestsView;

public class Home {		
	   Team tm = new Team();
	   DashBoard db = new DashBoard();
	   /*
		 * take the HoriZontal class and create the object of that class
		 * the horizontal panel is used to add the GWT widgets in the line Horizontally
		 * 
		 * 
		 * */
	   private HorizontalPanel hPanel=new HorizontalPanel();
	   /*
		 * take the VerticalPanel class and create the object of that class
		 * the vertical panel is used to add the GWT widgets vertically
		 * 
		 * */
	   private VerticalPanel vPanel=new VerticalPanel();
	   /* here we are defining the button widget
		 *  to add to perform click operation
		 *  The Button class, for example, publishes click events.
		 *   The associated handler interface is ClickHandler.
		 **/
	   private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
	   private HorizontalPanel hPanel1=new HorizontalPanel();
	   private Label l,name;
	   private HorizontalPanel h3=new HorizontalPanel();
	   
	   /**
		 * here we are taking SessionServiceAsync interface type reference variable
		 * sessionService and calling GWT.create() method and passing the 
		 * Synchronous interface SessionService.class
		 * the gwt will map its path to the the corresponding servlet
		 * on the server side through the url pattern of the web.xml
		 * file.
		 * then we can call all the method of the the the corresponding servlet at the client side
		 * 
		 * */
	   SessionServiceAsync ssn = GWT.create(SessionService.class);
	   NewRequest newR = new NewRequest();
	   String userID;
	   StockSort stockview = new StockSort();
	   VendorManagement vendorManagement = new VendorManagement();
	   PORequestsView poView = new PORequestsView();
	   
	public void onDashboardLoad(UserDAO user) {
		/* here we are clearing the Previous root panel 
    	 * otherwise these will also will reflect with the current
    	 * rootpanel
    	 * 
    	 * */
		 RootPanel.get("loginEntry").clear();
		 RootPanel.get("newReq").clear();
		 RootPanel.get("hwForm").clear();
		 /*
			 * here we are setting the style for the 
			 * panels and widgets
			 * set Style is the provide the shape and color to the component
			 * so it becomes the more interesting and
			 * screen friendly
			 * we define the styling in the .css file and access that 
			 * simply calling the method setStyleName()
			 * and pass that name in the method*/
		 hPanel.addStyleName("horizontal");
		 hPanel1.setStyleName("h1panel");	
		 
		 String mId = user.getManagerId();
		 final String managerID = mId;
		 
		  String role=user.getRole();
		  final String rolename = role;
		  
	      String uId = user.getUserName();
	      final String userId = uId;
		  
	      String uname = user.getName().toUpperCase();
	      //creating dashboard component according to the role
		  if(role.equals("admin")){
		      btn1= new Button("Dashboard");
		      
		      
		      btn2=new Button("Team");
		      btn2.setVisible(false);
		      btn3=new Button("New Request");
		      btn3.setVisible(false);
		      btn4=new Button("logout");
		      btn5=new Button("Stock");
		      btn5.setVisible(true);
		      btn6=new Button("Vendor");
		      btn6.setVisible(true);
		      btn7 = new Button("PO Requests");
		      btn7.setVisible(false);
		      btn8 = new Button("PO Response");
		      btn8.setVisible(false);
		      userID = "Admin : " + uname;
		      
		  }
		  else if(role.equals("manager")){
			  btn1= new Button("Dashboard");
		      btn2=new Button("Team");
		      btn2.setVisible(true);
		      btn3=new Button("New Request");
		      btn3.setVisible(true);
		      btn4=new Button("logout");
		      btn5=new Button("Stock");
		      btn5.setVisible(false);
		      btn6=new Button("Vendor");
		      btn6.setVisible(false);
		      btn7 = new Button("PO Requests");
		      btn7.setVisible(false);
		      btn8 = new Button("PO Response");
		      btn8.setVisible(false);
		      userID = "Manager : " + uname;

		      
		  }
		  else if(role.equals("employee")) {
			  btn1= new Button("Dashboard");
		      btn2=new Button("Team");
		      btn2.setVisible(false);
		      btn3=new Button("New Request");
		      btn3.setVisible(true);
		      btn4=new Button("logout");
		      btn5=new Button("Stock");
		      btn5.setVisible(false);
		      btn6=new Button("Vendor");
		      btn6.setVisible(false);
		      btn7 = new Button("PO Requests");
		      btn7.setVisible(false);
		      btn8 = new Button("PO Response");
		      btn8.setVisible(false);
		      userID = "Employee :" + uname;

		  }
		  else if(role.equals("vendor")) {
			  btn1= new Button("Dashboard");
			  btn1.setVisible(false);
		      btn2=new Button("Team");
		      btn2.setVisible(false);
		      btn3=new Button("New. Request");
		      btn3.setVisible(false);
		      btn4=new Button("logout");
		      btn5=new Button("Stock");
		      btn5.setVisible(false);
		      btn6=new Button("Vendor");
		      btn6.setVisible(false);
		      btn7 = new Button("PO Requests");
		      btn8 = new Button("PO Response");
		      btn8.setVisible(false);
		      userID = "Vendor :" + uname;

		  }
	      
		  //for tooltip
		  btn1.setTitle("To View All the Previous Requests");
		  btn2.setTitle("To View All the Previous Team Requests");
		  btn3.setTitle("To Make Any New Request");
		  btn4.setTitle("To Go Out From The Application");
		  btn5.setTitle("To View All Available Stock");
		  btn6.setTitle("For Vendor Management");
		  btn7.setTitle("To View All the Purchase Order Request");
		  btn8.setTitle("To View Admin Reply for a Perticular Request");
		  
		  /*
			 * here we are setting the style for the 
			 * panels and widgets
			 * set Style is the provide the shape and color to the component
			 * so it becomes the more interesting and
			 * screen friendly
			 * we define the styling in the .css file and access that 
			 * simply calling the method setStyleName()
			 * and pass that name in the method*/
	      btn1.setStyleName("btnhome");
	      btn2.setStyleName("btnhome");
	      btn3.setStyleName("btnhome");
	      btn4.setStyleName("btnhome");
	      btn5.setStyleName("btnhome");
	      btn6.setStyleName("btnhome");
	      btn7.setStyleName("btnhome");
	      btn8.setStyleName("btnhome");
	      
	      vPanel.setTitle("Setting");
	      
	      vPanel.setVisible(true);
	      hPanel1.add(btn1);
	      hPanel1.add(btn2);
	      hPanel1.add(btn3);
	      hPanel1.add(btn5);
	      hPanel1.add(btn6);
	      hPanel1.add(btn7);
	      hPanel1.add(btn8);
	      hPanel1.add(btn4);

	      
	      hPanel.add(l=new Label("Asset Management System"));
	      
	     
	      
	      hPanel.add(name = new Label("Welcome " + userID));
	      name.setStyleName("label");
	      l.setStyleName("label");
	      hPanel.add(hPanel1);
	      hPanel.add(vPanel);
	      logout();
	      stockHandler();
	      newRequest(userId, managerID, rolename);
	      teamHandler(userId);
	      dashBoardHandler(rolename, userId);
	      vendorManagement();
	      poRequestDashboard(userId);
	      
	      RootPanel.get("dashboard").add(hPanel);
	}
	
	//button handlers
	public void logout() {
		btn4.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ssn.exitSession(new LogoutCallback());
			}
		});
	}
	
	public void newRequest(final String uname , final String managerId, final String rolename) {
		btn3.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				PopupPanel p = new PopupPanel(true);
				p.add( newR.onRequest(uname, managerId, rolename));
				p.showRelativeTo(btn3);
				p.setAutoHideEnabled(true);
				

			      
			     
			}
		});
	}
	
    private void teamHandler(final String managerId ) {
		btn2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				tm.create(managerId);
			}			 
		 
		});

    }
    
    private void dashBoardHandler(final String type, final String userId) {
 		btn1.addClickHandler(new ClickHandler() {
 			public void onClick(ClickEvent event){
 				 
 				 
 				db.CreateDash(type,userId);
 				  	
 			}		 
 		 
 		});

     }
    
    private void stockHandler() {
    final VerticalPanel vp = new VerticalPanel();
    	btn5.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				RootPanel.get("menu").clear();
				RootPanel.get("stock").clear();
				RootPanel.get("DashBoard").clear();
		    	RootPanel.get("adminDashboard").clear();
				RootPanel.get("stockList").clear();
				RootPanel.get("vendorManagement").clear();
				RootPanel.get("poReply").clear();;
		    	vp.clear();
		    	h3.clear();
				h3 = stockview.stockMenu();
		    	vp.add(h3);
				vp.setStyleName("TableTitle");
				stockview.stockBody();
				RootPanel.get("menu").add(vp);
			}
		});
    }
    
    
    private void vendorManagement() {
    	btn6.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				RootPanel.get("menu").clear();
				RootPanel.get("stock").clear();
				RootPanel.get("DashBoard").clear();
		    	RootPanel.get("adminDashboard").clear();
				RootPanel.get("stockList").clear();
				RootPanel.get("vendorManagement").clear();
				RootPanel.get("poReply").clear();
				PopupPanel p = new PopupPanel(true);
				p.add(vendorManagement.onVendor());
				p.showRelativeTo(btn6);
				p.setAutoHideEnabled(true);
			}
		});
    }
    
    private void poRequestDashboard(final String vid) {
    	btn7.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("poReqView").clear();	
				poView.onPORequests(vid);
			}
		});
    }
    
}
