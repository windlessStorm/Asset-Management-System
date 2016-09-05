/**
 * @author sumit
 * Here we are showing all the requests made by employees
 * we have created a table and there we are showing
 * are the related information associated with the request
 * along with that a button to change the status by seeing 
 * the stock availability.
 */
package com.smt.client.requestHistory;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.home.StockView;

public class AdminViewCallback implements AsyncCallback<ReqDAO> {
	
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	 VerticalPanel v1 = new VerticalPanel();
	 VerticalPanel v2 = new VerticalPanel();
	 VerticalPanel v3 = new VerticalPanel();
	 VerticalPanel v4 = new VerticalPanel();
	 VerticalPanel v5 = new VerticalPanel();
	 VerticalPanel v6 = new VerticalPanel();
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
	 Label mCmnt = new Label();
	  /* here we are defining the button widget
			 *  to add to perform click operation
			 *  The Button class, for example, publishes click events.
			 *   The associated handler interface is ClickHandler.
			 **/
	 Button btn,b;
	 
	 StockView stockView = new StockView();
		/*
		 * here we are going to define the Popup panel
		 * The PopupPanel widget represents a panel that can pop up over other widgets.
		 *  It overlays the browser's client area (and any previously-created popups).
		 * */
	 	PopupPanel p = new PopupPanel(true);
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		HorizontalPanel hp1 = new HorizontalPanel();
		HorizontalPanel hp2 = new HorizontalPanel();
		
	//custom dialog box with glass effect
		private  class MyDialog extends DialogBox {

		      public MyDialog(final String reqId, final String status, final String mCmnt, final String productName) {
		    	  
		    	  
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

		            hp.clear();
					
						vp.clear();
						vp.setStyleName("dialogPanelAdmin");
						hp1.clear();
						hp.clear();
						hp2.clear();
						hp.add(l = new Label("Manager Comment"));
						l.setStyleName("dialogLabel");
						vp.add(hp);
						hp1.add(l = new Label(mCmnt));
						l.setStyleName("dialogLabel1");
						vp.add(hp1);
						b = new Button("Check Stock");
						b.setStyleName("dialogBtn");
						hp2.add(b);
						b.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								//to check stock for the particular item
								stockView.stockViewer(reqId, productName);
								MyDialog.this.hide();
							}
						});
						
						
						Button nothing = new Button("Exit");
						nothing.setStyleName("dialogBtn1");
						hp2.add(nothing);
						vp.add(hp2);
						//for normal exit
						nothing.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								MyDialog.this.hide();

							}
						});

		         setWidget(vp);
		      }
		   }
			
		
		
		
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getLocalizedMessage());
	}

	@Override
	public void onSuccess(ReqDAO admin) {	
		//arraylists for receiving all the items
		 ArrayList<String> requestId ;
		 ArrayList<String> empId ;
		 ArrayList<String> productName ;
		 ArrayList<String> productConfig ;
		 ArrayList<String> status ;
		 ArrayList<String> managerComment ;
		 
		 requestId = admin.getRequestId();
		 empId = admin.getEmpId();
		 productName = admin.getProductName();
		 productConfig = admin.getProductConfig();
		 status = admin.getStatus();
		 managerComment = admin.getManagerComment();


		 RootPanel.get("adminDashboard").clear();;
	 	 h = new HorizontalPanel();
	 	 
		 int count = 3;
		 //content of the admin dashboard
		 for(int i = 0 ; i < requestId.size() ; i++) {
			 	btn = new Button("Change Status");

				 String r = requestId.get(i);
				 String e = empId.get(i);
				 String p = productName.get(i);
				 String pc = productConfig.get(i);
				 String s = status.get(i);
				 String m = managerComment.get(i);
				 if(s == "NA") 
					 s = "Not Approved";
				 if(s == "Approve")
					 s = "Manager Approved"; 
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
				 v2.add(l = new Label(e));
				 if(count % 2 == 0)
					 l.setStyleName("dashBoardEmp");
				 else
					 l.setStyleName("dashBoardEmpEven");
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
				 v6.add(btn);	
			 	 btn.setStyleName("approveButton");
				 if(s != "Manager Approved") {
					btn.setEnabled(false);
				 }
				 
				 final String mstatus = s;
				 final String reqID = r;
				 
				 approveButton(reqID, mstatus, m, p);
				 
				 count++;
			
		 }
		 h.add(v1);
		 h.add(v2);
		 h.add(v3);
		 h.add(v4);
		 h.add(v5);
		 h.add(v6);
		 h.setStyleName("TableBody");
		 RootPanel.get("adminDashboard").add(h);
	
	
	}
	//button handler to approve the request
	private void approveButton(final String reqId,final String status, final String mCmnt, final String productName) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
			
				
				 // Instantiate the dialog box and show it.
	            MyDialog myDialog = new MyDialog(reqId, status, mCmnt, productName);

	            int left = Window.getClientWidth()/ 3;
	            int top = Window.getClientHeight()/ 3; 
	            myDialog.setPopupPosition(left, top);
	            myDialog.show();
			}
		});
	}
}
