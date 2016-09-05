/**
 * @author sumit
 * Here we are showing all the requests made by employees
 * who are working under the particular manager's team
 * we have created a table and there we are showing
 * are the related information associated with the request
 * along with that a button to change the status and there manager
 * can put some comment about the necessitate of the product. 
 */
package com.smt.client.requestHistory;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TeamViewCallback implements AsyncCallback<ReqDAO> {
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
	  /* here we are defining the button widget
			 *  to add to perform click operation
			 *  The Button class, for example, publishes click events.
			 *   The associated handler interface is ClickHandler.
			 **/
	 Button btn,b;
		/* here we are  taking the Listbox class of the gwt
		 * to add the list of the items
		 *  the Listbox in the gwt is the
		 * The ListBox widget represents a list of choices
		 *  to the user, either as a list box or as a drop-down list.
		 *  */
	 ListBox li = new ListBox();
	 ListBox li1 = new ListBox();
	 ReqServiceAsync manager = GWT.create(ReqService.class);
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
		HorizontalPanel hp3 = new HorizontalPanel();
		TextArea t = new TextArea();
	
		//custom dialog box with glass effect
	private  class MyDialog extends DialogBox {

	      public MyDialog(final String reqId, final String status) {
	    	  
	    	  
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
				li1.clear();
				li.clear();
	            hp.clear();
				hp3.clear();
				//listbox to change the status
				hp.add(l = new Label("Change Status"));
				l.setStyleName("dialogLabel");
					if(status.equals("Not Approved")){
						
						li.addItem("Approve");
						li.addItem("Pending");
						li.addItem("Reject");
					}
					if(status.equals("pending") || status.equals("Pending") ) {
						li1.addItem("Approve");
						li1.addItem("Reject");
					}
					vp.clear();
					vp.setStyleName("dialogPanel");
					hp1.clear();
					if(status == "Not Approved")
						hp.add(li);
					else if(status == "pending")
						hp.add(li1);
					hp1.add(l = new Label("Manager Comment"));
					l.setStyleName("dialogLabel");
					//text area to make some comment on the product
					t.setStyleName("dialogText");
					hp3.add(t);
					vp.add(hp);
					vp.add(hp1);
					vp.add(hp3);
					hp2.clear();
					b = new Button("Submit");
					b.setStyleName("dialogBtn");
					hp2.add(b);
					b.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							String lval;
							if(status == "Not Approved")
								lval = li.getSelectedItemText();
							else
								lval = li1.getSelectedItemText();

							String tval = t.getText();
							String reqID = reqId;
							//it will go to server side and manager comment will be store in the database
							manager.managerAproval(reqID, lval, tval, new ManagerApprovalCallback());	
							MyDialog.this.hide();
						}
					});
					t.setText("");
					
					//for normal exit
					Button nothing = new Button("Exit");
					nothing.setStyleName("dialogBtn1");
					hp2.add(nothing);
					vp.add(hp2);
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
	public void onSuccess(ReqDAO team) {
		
		//arraylists for receiving all the items

		 ArrayList<String> requestId ;
		 ArrayList<String> empId ;
		 ArrayList<String> productName ;
		 ArrayList<String> productConfig ;
		 ArrayList<String> status ;
		 
		 requestId = team.getRequestId();
		 empId = team.getEmpId();
		 productName = team.getProductName();
		 productConfig = team.getProductConfig();
		 status = team.getStatus();
		 RootPanel.get("adminDashboard").clear();;
	 	 h = new HorizontalPanel();
		
	 	 //content of the team dashboard

		 int count = 3;
		 for(int i = 0 ; i < requestId.size() ; i++) {
			 	btn = new Button("Change Status");

				 String r = requestId.get(i);
				 String e = empId.get(i);
				 String p = productName.get(i);
				 String pc = productConfig.get(i);
				 String s = status.get(i);
				 if(s == "NA") 
					 s = "Not Approved";
				 if(s == "granted" || s == "Grant")
					 s = "Granted";
				 if(s == "Reject")
					 s = "Rejected";
				 v1.add(l = new Label(r));
				 /*
					 * here we are setting the style for the 
					 * panels and widgets
					 * set Style is the provide the shape and color to the component
					 * so it becomes the more interesting and
					 * screen friendly
					 * we define the styling in the .css file and access that 
					 * simply calling the method setStyleName()
					 * and pass that name in the method*/
				 if(count % 2 == 0)
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
				 if(s == "Approve" || s == "Rejected" || s == "Granted") {
					btn.setEnabled(false);
				 }
				 count++;
				 final String mstatus = s;
				 final String reqID = r;
				 
				 approveButton(reqID, mstatus);
			
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
	
	private void approveButton(final String reqId,final String status) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
			            // Instantiate the dialog box and show it.
			            MyDialog myDialog = new MyDialog(reqId, status);

			            int left = Window.getClientWidth()/ 3;
			            int top = Window.getClientHeight()/ 3; 
			            myDialog.setPopupPosition(left, top);
			            myDialog.show();				

			}
		});
	}
	

}
