/**
 * Here we are showing a dynamic form according to the 
 * admin requested item quantity
 * we have done all the validation
 * the form contains four column product id, product name, product
 * config and warranty after submitting the form it will update the 
 * stock table.
 */

package com.smt.client.vendor;

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
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.admin.POService;
import com.smt.client.admin.POServiceAsync;
import com.smt.client.stock.StockService;
import com.smt.client.stock.StockServiceAsync;

public class POForm {
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel pid = new VerticalPanel();
	VerticalPanel pname = new VerticalPanel();
	VerticalPanel pconfig = new VerticalPanel();
	VerticalPanel warranty = new VerticalPanel();
	TextBox t1,t2,t3,t4;
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
	Button btn1,btn2,addBtn;
	VerticalPanel main = new VerticalPanel();
	/*
	 * take the HoriZontal class and create the object of that class
	 * the horizontal panel is used to add the GWT widgets in the line Horizontally
	 * 
	 * 
	 * */
	HorizontalPanel h1 = new HorizontalPanel();
	//array lists to store all the input elements
	ArrayList<String> proid = new ArrayList<String>();
	ArrayList<String> proname = new ArrayList<String>();
	ArrayList<String> procon = new ArrayList<String>();
	ArrayList<String> warr = new ArrayList<String>();
	boolean flag = true;

	StockServiceAsync stock = GWT.create(StockService.class);
	POServiceAsync po = GWT.create(POService.class);
	
	int count = 0;
	public void onPOForm(final String quantity, final String req) {
		
		proid.clear();
		proname.clear();
		procon.clear();
		warr.clear();
		pid.clear();
		pname.clear();
		pconfig.clear();
		warranty.clear();
		h1.clear();
		h.clear();
		main.clear();
		


		MyDialog myDialog = new MyDialog(quantity, req);
		
        int left = Window.getClientWidth()/4;
        int top = Window.getClientHeight()/ 3; 
        myDialog.setPopupPosition(left, top);
        myDialog.show();
		
		
	}
	//custom dialog box with glass effect
	 private  class MyDialog extends DialogBox {

		 
		
		  
	      public MyDialog(final String quantity, final String req) {
	    	  	  
			int quant = Integer.parseInt(quantity);
				
	
	    	  
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
				
			
				
				
			
				
				
				pid.add(l = new Label("Product Id"));
				l.setStyleName("poReqDialogTitle1");
				pname.add(l = new Label("Product Name"));
				l.setStyleName("poReqDialogTitle1");
				pconfig.add(l = new Label("Product Configuation"));
				l.setStyleName("poReqDialogTitle1");
				warranty.add(l = new Label("Warranty"));
				l.setStyleName("poReqDialogTitle1");
				
			
				
				h.add(pid);
				h.add(pname);
				h.add(pconfig);
				h.add(warranty);
				main.add(h);
				btn1 = new Button("Submit");
				btn2 = new Button("Exit");
				btn1.setEnabled(false);
				addBtn = new Button("Add Row");
				h1.add(addBtn);
				h1.add(btn1);
				h1.add(btn2);
				addButtonHandler(quant);
				
				
				
				main.add(h1);
				
				addBtn.setStyleName("dialogBtn1");
				btn1.setStyleName("dialogBtn1");
				btn1.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
					
						/*
						 * here we are going to define the Popup panel
						 * The PopupPanel widget represents a panel that can pop up over other widgets.
						 *  It overlays the browser's client area (and any previously-created popups).
						 * */
						PopupPanel p = new PopupPanel(true);
						//receiving all the text box value
						String t1val = t1.getValue();
						String t2val = t2.getValue();
						String t3val = t3.getValue();
						String t4val = t4.getValue();
						
						
						//validating all the text box
						if(t1val.isEmpty()) {
							p.add(l = new Label("Product ID Can Not Be Empty"));
							l.setStyleName("poReqPop");
							p.showRelativeTo(t1);
							flag = false;
						}
						
						if(t2val.isEmpty()) {
							p.add(l = new Label("Product Name Can Not Be Empty"));
							l.setStyleName("poReqPop");
							p.showRelativeTo(t2);
							flag = false;
						}
						if(t3val.isEmpty()) {
							p.add(l = new Label("Product Config Can Not Be Empty"));
							l.setStyleName("poReqPop");
							p.showRelativeTo(t3);
							flag = false;
						}
						//regular expression for date validation
						String matching = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
						if(!t4val.matches(matching)) {
							p.add(l = new Label("Please Enter in YYYY-MM-DD Format "));
							l.setStyleName("poReqPop");
							p.showRelativeTo(t4);
							flag = false;
							t4.setValue("");
							t1.setValue("");
							t2.setValue("");
							t3.setValue("");

						}
						//if every thing is correct
						if(flag) {
						
						proid.add(t1val);
						proname.add(t2val);
						procon.add(t3val);
						warr.add(t4val);
						//insert data into stock table
						stock.insertStock(proid, proname, procon, warr, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getLocalizedMessage());
							}

							@Override
							public void onSuccess(Void result) {
								MyDialog.this.hide();
							
								
							}
							
							
							
						}  );

						po.adminReplyToVendor(req, "submitted", new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getLocalizedMessage());
							}

							@Override
							public void onSuccess(Void result) {
							}
						});
						
						}

					}
				});
				
				
				btn2.setStyleName("dialogBtn1");
				btn2.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						MyDialog.this.hide();
					
						
					}
				});
				
				setWidget(main);
				
	      }
	   }
	 
	 //for do the operation again and again by clicking on add row
	 //Until no of quantity reaches
	private void addButtonHandler(final int quant) {
		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				for(int i = 0 ; i < quant && count < quant ; i++) {
					if(flag)
						addTextBox();
					//if(count >= quant ) {
					//t4KeyHandler();
					PopupPanel p = new PopupPanel(true);
					String t1val = t1.getValue();
					String t2val = t2.getValue();
					String t3val = t3.getValue();
					String t4val = t4.getValue();
					
					
					
					
					if(t1val.isEmpty()) {
						p.add(l = new Label("Product ID Can Not Be Empty"));
						l.setStyleName("poReqPop");
						p.showRelativeTo(t1);
						flag = false;
						//count--;
					}
				
					
					if(t2val.isEmpty()) {
						p.add(l = new Label("Product Name Can Not Be Empty"));
						l.setStyleName("poReqPop");
						p.showRelativeTo(t2);
						flag = false;
						//count--;
						

					}
					
					if(t3val.isEmpty()) {
						p.add(l = new Label("Product Config Can Not Be Empty"));
						l.setStyleName("poReqPop");
						p.showRelativeTo(t3);
						flag = false;
						//count--;
					}
					String matching = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
					if(!t4val.matches(matching)) {
						p.add(l = new Label("Please Enter in YYYY-MM-DD Format "));
						l.setStyleName("poReqPop");
						p.showRelativeTo(t4);
						flag = false;
						t4.setValue("");
						t1.setValue("");
						t2.setValue("");
						t3.setValue("");
						//count--;
					}
					else {
						flag = true;
						count++;
						proid.add(t1val);
						proname.add(t2val);
						procon.add(t3val);
						warr.add(t4val);
					}
					
					
					
					
						
						
					}
				addBtn.setEnabled(false);
				btn1.setEnabled(true);
				
				
			}
		});
	}
	

	
//to create new text box	
private void addTextBox() {


	
	t1 = new TextBox();
	t2 = new TextBox();
	t3 = new TextBox();
	t4 = new TextBox();

	
	pid.add(t1);
	pname.add(t2);
	pconfig.add(t3);
	warranty.add(t4);
	
}
}
