/**
 * @author sumit
 * 
 * Here we have created a drop down list box by using
 * popup panel, popup panel has two component 
 * 1.to see all the vendors : Vendor List
 * 2.to see all the vendors response : Vendor Reply  
 */

package com.smt.client.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VendorManagement {
	//widgets
	VendorList vlist = new VendorList();
	VendorReply reply = new VendorReply();
	PopupPanel p;
	Button b1,b2;
	VerticalPanel v1 = new VerticalPanel();

	VerticalPanel vendor = new VerticalPanel();
	//we will call it from the Home.java
	public VerticalPanel onVendor() {
		RootPanel.get("vendorManagement").clear();;
		 p = new PopupPanel(true);
		 b1 = new Button("Vendor List");
		 b2 = new Button("Vendor Reply");
		 v1.clear();

		b1.setStyleName("btnPopup");
		b2.setStyleName("btnPopup");
		v1.add(b1);
		v1.add(b2);
		p.setStyleName("reqPopup");
		p.add(v1);		
		onVendorMenu();
		onVendorReply();
		return v1;
	}
	//button handler
	private void onVendorMenu() {
		b1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vlist.onVendorList();
			}
		});

	}
	//button handler
	private void onVendorReply() {
		b2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				reply.onReply();
			}
		});
	}
	
	
}
