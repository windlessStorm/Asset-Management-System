/**
 *  here in this class we are going to take a class MenuSelect
 * in this MenuSelect class the the user will be able to select 
 * the software or the hardware
 * */

package com.smt.client.home;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuSelect {
	
	HardwareMenu hwForm = new HardwareMenu();
	SoftwareMenu swForm = new SoftwareMenu();
	/* here we are  taking the Listbox class of the gwt
	 * to add the list of the items
	 *  the Listbox in the gwt is the
	 * The ListBox widget represents a list of choices
	 *  to the user, either as a list box or as a drop-down list.
	 *  */
	ListBox list1,list2;
	/*
	 * take the HoriZontal class and create the object of that class
	 * the horizontal panel is used to add the GWT widgets in the line Horizontally
	 * 
	 * 
	 * */
	HorizontalPanel h1 = new HorizontalPanel();
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel v = new VerticalPanel();
	VerticalPanel v1 = new VerticalPanel();
	Label l;
	// define a method to select the menu item
	public VerticalPanel onMenuHW(final String u, final String managerId, final String rolename) {	
			v.clear();
			h1.clear();
    		list1 = new ListBox();
    		list1.setTitle("Hardware");
    		list1.addItem("Desktop");
    		list1.addItem("Key Board");
    		list1.addItem("Mouse");
    		list1.addItem("Printer");
    		list1.addItem("Head Phone");
    		list1.setVisibleItemCount(0);
    		h1.add(l = new Label("choose hardware"));
    		l.setStyleName("hwLebelNew");
    		list1.setStyleName("menuListNew");
    		h1.add(list1);
    		v.add(h1); 
    		v.setStyleName("menuList");
    		h1.setStyleName("menuStyleNew");
            onSelectHW(u, managerId, rolename);
    		RootPanel.get("menu").add(v);
    		return v;
	}
	// define a method for the user when he selects the hardware menu
	public VerticalPanel onMenuSW(final String u, final String managerId, final String rolename) {
		v.clear();
		h1.clear();
		list2 = new ListBox();
		list2.setTitle("Software");
		list2.addItem("Adobe Reader");
		list2.addItem("Adobe Photoshop");
		list2.addItem("Quick Hill");
		list2.addItem("VLC Player");
		list2.addItem("Eclipse");
		list2.setVisibleItemCount(0);
		l.setStyleName("hwLebelNew");
		list2.setStyleName("menuListNew");
		h1.add(l = new Label("choose software"));

		h1.add(list2);
		v.add(h1);
		v.setStyleName("menuList");
		onSelectSW(u, managerId, rolename);
		
		
		RootPanel.get("menu").add(v);
		return v;
    	}

	// define a method for the user when he selects the software menu
	private void onSelectHW(final String u, final String managerId, final String rolename) {
		
		list1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				v1.clear();
				final String type = list1.getSelectedItemText().toLowerCase().trim();
				v1 = hwForm.hwMenu(type,u, managerId, rolename);
				v1.setStyleName("hwForm");
				v.add(v1);      

			}
		});
			
	}
	
private void onSelectSW(final String u, final String managerId, final String rolename) {
		
		list2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				v1.clear();
				final String type = list2.getSelectedItemText().toLowerCase().trim();
				v1 = swForm.swMenu(type,u, managerId, rolename);
				v1.setStyleName("hwForm");
				v.add(v1);
							
			}
		});
			
	}	
}
