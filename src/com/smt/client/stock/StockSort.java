/**
 * @author sumit
 * Here we are receiving all the information from 
 * the stock table.
 * And here we have made a table where we are showing all
 * the available stocks for the particular product.
 */
package com.smt.client.stock;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class StockSort {
	StockServiceAsync stockCall = GWT.create(StockService.class);
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel v = new VerticalPanel();
	/*
	 * take the HoriZontal class and create the object of that class
	 * the horizontal panel is used to add the GWT widgets in the line Horizontally
	 * 
	 * 
	 * */
	HorizontalPanel h1;
	HorizontalPanel h;
	/*here we are taking the Label
	 * The Label can contains only arbitrary text 
	 * and it can not be interpreted as HTML. 
	 * This widget uses a <div> element, 
	 * causing it to be displayed with block layout.
	 * */
	Label l, lab;
	ReqServiceAsync pending = GWT.create(ReqService.class);
	StockServiceAsync stock = GWT.create(StockService.class);
	/* here we are  taking the Listbox class of the gwt
	 * to add the list of the items
	 *  the Listbox in the gwt is the
	 * The ListBox widget represents a list of choices
	 *  to the user, either as a list box or as a drop-down list.
	 *  */
	ListBox li = new ListBox();
	public HorizontalPanel stockMenu() {
		//h1.clear();
		h1 = new HorizontalPanel();
		h1.setStyleName("TableTitle");
		h1.add(l = new Label("Ptoduct ID"));
		l.setStyleName("dashBoardReq");
		h1.add(l = new Label("Product Name"));
		l.setStyleName("dashBoardPname");
		h1.add(l = new Label("Product Configuration"));
		l.setStyleName("dashBoardProdConfig");
		h1.add(l = new Label("Warranty"));
		l.setStyleName("dashBoardStatus");
		li.clear();
		//list box items
		li.addItem("All");
		li.addItem("desktop");
		li.addItem("key board");
		li.addItem("mouse");
		li.addItem("printer");
		li.addItem("head phone");
		li.addItem("adobe reader");
		li.addItem("adobe photoshop");
		li.addItem("quickhill");
		li.addItem("vlc player");
		li.addItem("eclipse");
		h1.add(new Label("Sort"));
		h1.add(li);
		listHandler();
		l.setStyleName("dashBoardStatus");
		return h1;
	}
	//to show stock 
	public void stockBody() {
		 stockCall.viewStock(new viewStockCallback());
	
	}
	
	//list box handlers
	private void listHandler() {
		li.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String statusName = li.getSelectedItemText(); 
				if(statusName == "All")
					 stockCall.viewStock(new viewStockCallback());
				else
					stock.sortStock(statusName, new SortStockCallback());
			
			}
		
		});
	}
	
}
