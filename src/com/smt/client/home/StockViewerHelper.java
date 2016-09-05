/**
 * here we are accessing the stock table from the database
 * and we are showing all the stocks which is the body of the table 
 */
package com.smt.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;
import com.smt.client.stock.CheckStockCallback;
import com.smt.client.stock.StockService;
import com.smt.client.stock.StockServiceAsync;

public class StockViewerHelper {
	/**
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
	Label l;
	/**
	 *  here we are defining the button widget
	 *  to add to perform click operation
	 *  The Button class, for example, publishes click events.
	 *   The associated handler interface is ClickHandler.
	 **/
	Button btn = new Button("Pending");
	//creating service interface instance
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
	ReqServiceAsync pending = GWT.create(ReqService.class);
	StockServiceAsync stock = GWT.create(StockService.class);
	ListBox li = new ListBox();
	//accessing all the stock info and showing it to a table
	public HorizontalPanel stockMenu(final String reqId) {
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
		btn.setStyleName("pendingBtn");
		h1.add(btn);
		pendingButton(reqId);
		li.clear();
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
		l.setStyleName("dashBoardStatus");
		return h1;
	}
	
	public void stockBody(final String reqId, final String productName) {
		stock.checkStock(reqId, productName, new CheckStockCallback());	
	}
	
	private void pendingButton(final String reqId ) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				pending.adminPending(reqId, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());
					}
				});
			}
		});
	}
	
}
