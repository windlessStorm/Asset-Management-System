/**
 * here we are taking the Newrequest.java class
 * in this class the employee will generate the new request
 */

package com.smt.client.home;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewRequest {
	// create the object of the menuSelect class
		// we have defined all the method in that class
	MenuSelect m = new MenuSelect();
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel v1 = new VerticalPanel();
	/*
	 * take the HoriZontal class and create the object of that class
	 * the horizontal panel is used to add the GWT widgets in the line Horizontally
	 * 
	 * 
	 * */
	HorizontalPanel menu = new HorizontalPanel();
	VerticalPanel v = new VerticalPanel();
	VerticalPanel main = new VerticalPanel();
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
	Button b1,b2;
	/*
	 * here we are going to define the Popup panel
	 * The PopupPanel widget represents a panel that can pop up over other widgets.
	 *  It overlays the browser's client area (and any previously-created popups).
	 * */
	PopupPanel p;
		// create the method which is called when the 
		// request is raised
	public VerticalPanel onRequest(final String uname, final String managerId, final String rolename) {
		/** here we are clearing the Previous root panel 
    	 * otherwise these will also will reflect with the current
    	 * rootpanel
    	 * 
    	 * */
		RootPanel.get("team").clear();
    	RootPanel.get("DashBoard").clear();
		RootPanel.get("adminDashboard").clear();
    	main.clear();
		 p = new PopupPanel(true);
		 b1 = new Button("Hardware");
		 b2 = new Button("Software");
		 v1.clear();

		b1.setStyleName("btnPopup");
		b2.setStyleName("btnPopup");
		v1.add(b1);
		v1.add(b2);
		p.setStyleName("reqPopup");
		p.add(v1);
/*		p.show();
		p.setAutoHideEnabled(true);*/
		
		onSoft(uname, managerId, rolename);
		onHard(uname, managerId, rolename);
		main.setStyleName("menuList"); 
		RootPanel.get("newReq").add(main); 
		return v1;
	}
	
	public VerticalPanel onSoft(final String u, final String managerId, final String rolename) {
		b2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				main.clear();
				// cal the onMenuSW method when user select software
				// menu
				v = m.onMenuSW(u, managerId, rolename);
				main.add(v);
			
			}
		});
		return v;
	}
	
	public VerticalPanel onHard(final String u, final String managerId, final String rolename) {
		b1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
		    	main.clear();
				v = m.onMenuHW(u, managerId, rolename);
				main.add(v);
			}
		});
		return v;
	}
}
