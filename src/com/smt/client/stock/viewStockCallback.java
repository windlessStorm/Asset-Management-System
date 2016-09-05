/**
 * @author sumit
 * Here we are receiving all the information from 
 * the stock table.
 * And here we have made a table where we are showing all
 * the available stocks.
 */

package com.smt.client.stock;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class viewStockCallback implements AsyncCallback<StockDao> {
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	final VerticalPanel v1 = new VerticalPanel();
	 final VerticalPanel v2 = new VerticalPanel();
	 final VerticalPanel v3 = new VerticalPanel();
	 final VerticalPanel v4 = new VerticalPanel();
	 final VerticalPanel v5 = new VerticalPanel();
	 /*
		 * take the HoriZontal class and create the object of that class
		 * the horizontal panel is used to add the GWT widgets in the line Horizontally
		 * 
		 * 
		 * */
	 final HorizontalPanel h = new HorizontalPanel();
	 /*here we are taking the Label
		 * The Label can contains only arbitrary text 
		 * and it can not be interpreted as HTML. 
		 * This widget uses a <div> element, 
		 * causing it to be displayed with block layout.
		 * */
	 Label l;
	 StockServiceAsync stockCall = GWT.create(StockService.class);
		VerticalPanel v = new VerticalPanel();
		HorizontalPanel h1;
		Label lab;
	@Override
	public void onFailure(Throwable caught) {
		System.out.println(caught.getLocalizedMessage());				

	}

	@Override
	public void onSuccess(StockDao stock) {
		RootPanel.get("stock").clear();
		//arraylists for receiving all the items
		ArrayList<String> productId ;
		 ArrayList<String> productName ;
		 ArrayList<String> productConfig ;
		 ArrayList<String> warranty ;
		 
		 productId = stock.getProductId();
		 productName = stock.getProductName();
		 productConfig = stock.getProductConfig();
		 warranty = stock.getWarranty();
		// RootPanel.get("adminDashboard").clear();
	 	HorizontalPanel h = new HorizontalPanel();
	 //	 h.clear();
	 	 int count = 3;
	 	 //content of the stock dashboard
	    	for(int i = 0 ; i < productId.size() ; i++) {
	 	 	 String pid = productId.get(i);
			 String p = productName.get(i);
			 String pc = productConfig.get(i);
			 String w = warranty.get(i);
			 
			 
			 v1.add(lab = new Label(pid));
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
				 lab.setStyleName("dashBoardPname");
			 else
				 lab.setStyleName("dashBoardPnameEven");
			 v2.add(lab = new Label(p));
			 if(count % 2 == 0)
				 lab.setStyleName("dashBoardPname");
			 else
				 lab.setStyleName("dashBoardPnameEven");
			 v3.add(lab = new Label(pc));
			 if(count % 2 == 0)
				 lab.setStyleName("dashBoardProdConfig");
			 else
				 lab.setStyleName("dashBoardProdConfigEven");
			 v4.add(lab = new Label(w));
			 if(count % 2 == 0)
				 lab.setStyleName("dashBoardProdConfig");
			 else
				 lab.setStyleName("dashBoardProdConfigEven");
			 
			 count++;
	 }
	 	
	 	 h.add(v1);
		 h.add(v2);
		 h.add(v3);
		 h.add(v4);
		 h.setStyleName("TableBody");
		 RootPanel.get("stock").add(h);
	
	}

}
