/**
 * @author sumit
 * VendorList.java : Here we have created a table where we are showing
 * 					 all the vendors that are in the database table
 * 					 'vendor list'. we are showing vendorId,Vendor Name, 
 * 					 'Vendor Type' means which product that vendor sells
 * 					  as a attribute of the table.		
 */


package com.smt.client.admin;

import java.util.ArrayList;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VendorList {
	  /**
	   * Here we are creating the the instance of the Asynchronous interface VendorListServiceAsync
	   * by vendorList instance we will call the respective service methods here.
	   */
	  VendorListServiceAsync vendorList = GWT.create(VendorListService.class);
	  //initializing all the required GWT widgets
	  VerticalPanel main = new VerticalPanel();
	  Button btn = new Button("PO Request");
	  ArrayList<String> vendorId ;
	  ArrayList<String> vendorName ;
	  ArrayList<String> vendorType;
	  CheckBox cbox;
	  //Arraylists to store to store all selected item from the check box 
	  ArrayList<String> checked = new ArrayList<String>();
	  ArrayList<String> checkedPname = new ArrayList<String>();
	  
	  /**
	   * Here we are creating the the instance of the Asynchronous interface POServiceAsync
	   * by poReq instance we will call the respective service methods here.
	   */
	  
	  
	  POServiceAsync poreq = GWT.create(POService.class);
	  
	  
	  /**
	   * Here We are creating a Custom Dialog Box which will send 
	   * the Purchase Request to  vendor. 
	   * when admin checks a checkbox and click on  po request
	   * this dialog box appears
	   *
	   *
	   */
	  private  class MyDialog extends DialogBox {
		  
		  //all widget component required for the MyDialog class

		  VerticalPanel vp = new VerticalPanel();
		  HorizontalPanel hp = new HorizontalPanel();
		  HorizontalPanel hp1 = new HorizontalPanel();
		  HorizontalPanel hp2 = new HorizontalPanel();
		  HorizontalPanel hp3 = new HorizontalPanel();
		  HorizontalPanel hp4 = new HorizontalPanel();

		  Label l;
		  TextArea t = new TextArea();
		  Button b;
		  TextBox tb = new TextBox();
		  
	      public MyDialog() {
	    	  	  
	    	  //here we are making the all the vendor that admin
	    	  //has selected to uppercase
	    	  String vnames = checked.get(0).toUpperCase();
	    	  
	    	  for(int i = 1 ; i < checked.size() ; i++) {
	    		  vnames = vnames + " , " + checked.get(i).toUpperCase();
	    	  }
	    	  
	    	  
	    	  
	         // Set the dialog box's caption.
	         //setText("Manager Approval");
	         
	         // Enable animation.
	         setAnimationEnabled(true);

	         // Enable glass background.
	         setGlassEnabled(true);
	         
	         //setting properties to the glass effect.
				Style glassStyle = getGlassElement().getStyle();
				glassStyle.setProperty("width", "100%");
				glassStyle.setProperty("height", "100%");
				glassStyle.setProperty("backgroundColor", "#e8edea");
				glassStyle.setProperty("opacity", "0.45");
				glassStyle.setProperty("mozOpacity", "0.45");
				glassStyle.setProperty("filter", " alpha(opacity=45)");
				//clearing all the panels first
				vp.clear();
	            hp.clear();
	            hp1.clear();
	    		hp2.clear();
				hp3.clear();
				hp4.clear();
				
				hp.add(l = new Label("Vendor Name : " + vnames ));
				l.setStyleName("dialogLabel");
				
					
					vp.setStyleName("dialogPanel");
					hp1.add(l = new Label(checkedPname.get(0) +" Config"));
					l.setStyleName("dialogLabel");
					t.setStyleName("dialogText");
					hp2.add(t);
					
					hp3.add(l = new Label("Quantity"));
					l.setStyleName("dialogLabel");
					hp3.add(tb);
			
					b = new Button("Submit");
					b.setStyleName("dialogBtn");
					hp4.add(b);
					
					vp.add(hp);
					vp.add(hp1);
					vp.add(hp2);
					vp.add(hp3);
					vp.add(hp4);
					
					//Button click event when admin submit the request to vendor
					b.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
					

							String tval = t.getText();
							PopupPanel p = new PopupPanel(true);
							PopupPanel p1 = new PopupPanel(true);
							
							boolean flag = true;
							int val = 0;
							//here we are validating some fields like which can not be empty
							if(tval.isEmpty()) {
								p.add(l = new Label("Product Configuation cannot be Empty"));
								l.setStyleName("poReqPop");
								p.showRelativeTo(t);
								flag = false;
								t.setValue("");
								
							}
							String tbval = tb.getValue();
							try {
								val = Integer.parseInt(tbval);
							}
							//here we are catching whether the entered no of quantity is integer only or not
							catch(NumberFormatException nf){
								p1.add(l = new Label("Enter Number Only"));
								l.setStyleName("poReqPop");
								tb.setValue("");
								p1.showRelativeTo(hp3);
								flag = false;
							}
							//if every thing is fine we will send the request to the server
							if(flag) {
								//sending the PORequest 
								poreq.PORequest(checked, checkedPname.get(0), tval, val, new AsyncCallback() {
									//if the procedure fails it will go here
									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getLocalizedMessage());
									}
									//if every thing is correct it will go inside here
									@Override
									public void onSuccess(Object result) {
										MyDialog.this.hide();

									}
								});
							}
						}
					});
					t.setText("");
					
					//its the button for when the admin don't want to do any thing
					Button nothing = new Button("Exit");
					nothing.setStyleName("dialogBtn1");
					hp4.add(nothing);
					nothing.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							MyDialog.this.hide();

						}
					});

	         setWidget(vp);
	      }
	   }
	  
	  
	  
	  
	  
	  public void onVendorList() {
		//here we are accesssing all the vendor list from the database
		vendorList.getVendorList(new AsyncCallback<VendorListDAO>() {
			
			@Override
			public void onSuccess(VendorListDAO vendor) {
				  //here we are getting all the information from the vendorListDAO class's getter method	
				  vendorId = vendor.getVendorId();
				  vendorName = vendor.getVendorName();
				  vendorType = vendor.getVendorType();
				  HorizontalPanel title = new HorizontalPanel();
					
				  //widgets for the class
					VerticalPanel vcheck = new VerticalPanel();
					VerticalPanel vid = new VerticalPanel();
					VerticalPanel vname = new VerticalPanel();
					VerticalPanel vtype = new VerticalPanel();
					//clearing all first
					vcheck.clear();
					vid.clear();
					vname.clear();
					vtype.clear();
					main.clear();
					
					vcheck.setSpacing(13);
					vid.setSpacing(15);
					vname.setSpacing(15);
					vtype.setSpacing(15);
					
					Label l;
					//defining title of the table
					vcheck.add(l = new Label("Select Vendor"));
					l.setStyleName("poReqDialogTitle");
					vid.add(l = new Label("Vendor ID"));
					l.setStyleName("poReqDialogTitle");
					vname.add(l = new Label("Vendor Name"));
					l.setStyleName("poReqDialogTitle");
					vtype.add(l = new Label("Vendor Type"));
					l.setStyleName("poReqDialogTitle");

					//now we will show all this in label just one by one
					for(int i =0 ; i < vendorId.size() ; i++) {
						final String vidi = vendorId.get(i);
						String vnamei = vendorName.get(i);
						final String vtypei = vendorType.get(i);
						
						cbox = new CheckBox();
						vcheck.add(cbox);
						
						vid.add(new Label(vidi));
						vname.add(new Label(vnamei));
						vtype.add(new Label(vtypei));
						
						
						checkBoxHandler(vidi, vtypei);
						
						
					}
					//here we are adding all the vertical panel to a horizontal panel
					HorizontalPanel body = new HorizontalPanel();
					body.add(vcheck);
					body.add(vid);
					body.add(vname);
					body.add(vtype);
					
					main.add(title);
					main.add(body);
					main.setStyleName("poReqBody");
					main.add(btn);
					main.setSize("500px", "100%");
					btn.setStyleName("poReqBtn");
					//click handler for PO Request
					btn.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							
							boolean flag = true;
							Label l;
						
							
					    	  for(int i = 0 ; i < checkedPname.size() - 1 ; i++) {
					    		  for(int j = i + 1 ; j < checkedPname.size() ; j++) {
					    			  if(!(checkedPname.get(i).equals(checkedPname.get(j)))) {
					    				  PopupPanel p = new PopupPanel(true);
					    				  p.add(l = new Label("Product Type Should Be Same"));
					    				  l.setStyleName("poReqPop");
					    				  p.showRelativeTo(btn);
					    				  flag = false;
					    			  }
					    		  }
					    	  }
					    		  
							
							//if all is fine it will call the dialog box mentioned above
							if(flag) {
							    MyDialog myDialog = new MyDialog();

					            int left = Window.getClientWidth()/ 3;
					            int top = Window.getClientHeight()/ 3; 
					            myDialog.setPopupPosition(left, top);
					            myDialog.show();
						}
						}	
					});
				
					  
			}
			//if anything goes wrong
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fails..");
				Window.alert(caught.getLocalizedMessage());
			}
		});
		//adding it to the root panel
		RootPanel.get("vendorManagement").add(main); 
	}
	
	//check box handler
	private void checkBoxHandler(final String vendorId, final String pname) {
		checked.clear(); 
		checkedPname.clear();
		cbox.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				  CheckBox checkBox = (CheckBox)event.getSource();

				  
				 boolean flag = checkBox.getValue();
				 if(flag) {
					 checked.add(vendorId);
				 	 checkedPname.add(pname);
				 }
				 else {
					 checked.remove(vendorId);
				 	 checkedPname.remove(pname);

				 }
				 }
		});
	}
}
