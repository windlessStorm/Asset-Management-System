/**
 * @author sumit
 * 
 * here we are creating the  HardwareMenu class 
 * in this class the user will be selecting the hardware type and provide the feature ofthe 
 * hardware
 * 
 * 
 */
package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.ReqHistoryCallback;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class HardwareMenu {
	
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
	
	ReqServiceAsync request = GWT.create(ReqService.class);
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel form;
	/* here we are defining the button widget
	 *  to add to perform click operation
	 *  The Button class, for example, publishes click events.
	 *   The associated handler interface is ClickHandler.
	 **/
	Button btn = new Button("Submit");
	
	/* here we are  taking the Listbox class of the gwt
	 * to add the list of the items
	 *  the Listbox in the gwt is the
	 * The ListBox widget represents a list of choices
	 *  to the user, either as a list box or as a drop-down list.
	 *  */
	ListBox l1,l2,l3,list;
	DashBoard d = new DashBoard();
	
	//custom dialog box class
	private  class MyDialog extends DialogBox {

	      public MyDialog() {
	    	  
	    	  
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

	            
				VerticalPanel h = new VerticalPanel();
				h.clear();
				h.setStyleName("dialogReq");
				//success message when a new request will publish successfully
				h.add(new Label("New Request Published Successfully"));
					
					Button nothing = new Button("ok");
					nothing.setStyleName("dialreqBtn");
					h.add(nothing);
					nothing.setStyleName("dialogBtn1");
					nothing.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							MyDialog.this.hide();

						}
					});

	         setWidget(h);
	      }
	   }
		
	
	//it contains all the hardware lists that are available in our system
	public VerticalPanel hwMenu(final String type, final String u, final String managerId, final String rolename) {
		if(type == "desktop") {
			HorizontalPanel h1,h2,h3;
			
			Label l;
			form = new VerticalPanel();
			h1 = new HorizontalPanel();
			h2 = new HorizontalPanel();
			h3 = new HorizontalPanel();

			h1.add(l = new Label("Enter Processor Type: "));
			//h1.add(t1 = new TextBox());
			l1 = new ListBox();
			l1.addItem("Intel I3");
			l1.addItem("Intel I5");
			l1.addItem("Intel I5");
			l1.addItem("AMD FX");
			h1.add(l1);
			l1.setStyleName("hwTextBox");
			l.setStyleName("hwLabel");

			h2.add(l =  new Label("Enter Ram Size: "));
		//	h2.add(t2 = new TextBox());
			
			l2 = new ListBox();
			l2.addItem("2 GB");
			l2.addItem("4 GB");
			l2.addItem("8 GB");
			l2.addItem("16 GB");
			//l2.setStyleName("hwTextBox");
			h2.add(l2);
			l2.setStyleName("hwTBox2");
			l.setStyleName("hwLabel");
			
			h3.add(l = new Label("Enter Hard Disk Size:"));
			//h3.add(t3 = new TextBox());
			l3 = new ListBox();
			l3.addItem("320 GB");
			l3.addItem("500 GB");
			l3.addItem("1 TB");
			l3.addItem("2 TB");
			l3.setStyleName("hwTextBox");
			l.setStyleName("hwLabel");
			h3.add(l3);
			
			form.add(h1);
			form.add(h2);
			form.add(h3);
			form.add(btn);

		}
		else {
			HorizontalPanel h1;
			list = new ListBox();
			Label l;
			form = new VerticalPanel();
			h1 = new HorizontalPanel();

			
			list.addItem("Wired");
			list.addItem("Wireless");
			list.setStyleName("hwTextBox");

			h1.add(l = new Label("Select Product Type: "));
			h1.add(list);
			h1.setStyleName("h2PanelReq");
			l.setStyleName("hwLabel");
			
			form.add(h1);
			form.add(btn);	
		}
		btn.setStyleName("hwButton");
		requestButtonHandler(type, u, managerId, rolename);
		return form;
	}
	
	
	
	//it handles which product got selected and stores it to database
	public void requestButtonHandler(final String pName, final String eId, final String managerId, final String rolename) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String productName = pName;
				String empId = eId;
				String productConfig;
				String managerID = managerId;
				if(productName == "desktop") {
					
					productConfig = l1getValue() + "," + l2getValue() + "," + l3getValue();
				}
				else
					productConfig = listgetValue();
				
				
			    request.insertReq(empId, productName, productConfig, managerID, new ReqHistoryCallback());
				
			    MyDialog myDialog = new MyDialog();

	            int left = Window.getClientWidth()/ 3;
	            int top = Window.getClientHeight()/ 3; 
	            myDialog.setPopupPosition(left, top);
	            myDialog.show();
	            d.CreateDash(rolename, empId);
	            //Window.Location.reload();
				
			}
		});
	}
	
	private String l1getValue() {
		return l1.getSelectedItemText();
	}
	
	private String l2getValue() {
		return l2.getSelectedItemText();
	}
	private String l3getValue() {
		return l3.getSelectedItemText();
	}
	private String listgetValue() {
		return list.getSelectedItemText();
	}
	
}
	
