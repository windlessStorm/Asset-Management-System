/**
 * Here we are showing all the purchase order request 
 * come from the admin to a particular vendor
 * vendor will be able to see the requests as a form of a 
 * table and will be able to take action according to
 * the request basis.
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.admin.POService;
import com.smt.client.admin.POServiceAsync;

public class PORequestsView {
	POServiceAsync vendor = GWT.create(POService.class);
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	VerticalPanel rid = new VerticalPanel();
	VerticalPanel ptype = new VerticalPanel();
	VerticalPanel pconfig = new VerticalPanel();
	VerticalPanel q = new VerticalPanel();
	VerticalPanel action = new VerticalPanel();
	VerticalPanel statusPanel = new VerticalPanel();
	VerticalPanel poForm = new VerticalPanel();
	Button btn,btn1;
	 /*
		 * take the HoriZontal class and create the object of that class
		 * the horizontal panel is used to add the GWT widgets in the line Horizontally
		 * 
		 * 
		 * */
	HorizontalPanel main = new HorizontalPanel();
	POForm form = new POForm();
	
	
	  private  class MyDialog extends DialogBox {

		  VerticalPanel vp = new VerticalPanel();
		  HorizontalPanel hp = new HorizontalPanel();
		  HorizontalPanel hp1 = new HorizontalPanel();
		  HorizontalPanel hp2 = new HorizontalPanel();
		  /*here we are taking the Label
			 * The Label can contains only arbitrary text 
			 * and it can not be interpreted as HTML. 
			 * This widget uses a <div> element, 
			 * causing it to be displayed with block layout.
			 * */
		  Label l;
		  /* here we are  taking the Listbox class of the gwt
			 * to add the list of the items
			 *  the Listbox in the gwt is the
			 * The ListBox widget represents a list of choices
			 *  to the user, either as a list box or as a drop-down list.
			 *  */
		  ListBox list = new ListBox();
		  /* here we are defining the button widget
			 *  to add to perform click operation
			 *  The Button class, for example, publishes click events.
			 *   The associated handler interface is ClickHandler.
			 **/
		  Button b;
		
		  //custom dialog box with glass effect
	      public MyDialog(final String req) {
	    	  	  
	    	
	    	  
	    	  
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
				
				vp.clear();
	            hp.clear();
	            hp1.clear();
	    		hp2.clear();

				//vendor reply message
				hp.add(l = new Label("Reply Message :" ));
				l.setStyleName("dialogLabel");
				/*
				 * here we are setting the style for the 
				 * panels and widgets
				 * set Style is the provide the shape and color to the component
				 * so it becomes the more interesting and
				 * screen friendly
				 * we define the styling in the .css file and access that 
				 * simply calling the method setStyleName()
				 * and pass that name in the method*/	
				vp.setStyleName("dialogPanel");
				//list items
				list.addItem("Available");
				list.addItem("Not Available");
				hp.add(list);
					
			
					b = new Button("Submit");
					b.setStyleName("dialogBtn");
					hp2.add(b);
					
					vp.add(hp);
					vp.add(hp1);
					vp.add(hp2);
					
					//button handler
					b.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
					

							String lval = list.getSelectedItemText();
							PopupPanel p = new PopupPanel(true);
							
							boolean flag = true;
							
							//to validate properly
							if(lval.isEmpty()) {
								p.add(l = new Label("Please Choose Some Item From Listbox"));
								l.setStyleName("poReqPop");
								p.showRelativeTo(list);
								flag = false;
								
							}
							
							
							
							//if every thing is correct
							if(flag) {
								//send the vendor comment to admin
								vendor.vendorComment(lval, req, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getLocalizedMessage());
									}

									@Override
									public void onSuccess(Void result) {
										MyDialog.this.hide();
									}
								});
									
								
							}
						}
					});
					

					Button nothing = new Button("Exit");
					nothing.setStyleName("dialogBtn1");
					hp2.add(nothing);
					nothing.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							MyDialog.this.hide();

						}
					});

	         setWidget(vp);
	      }
	   }
	  
	
	
	
		public void onPORequests(final String vid) {
			vendor.vendorPODashboard(vid, new AsyncCallback<VendorReqDAO>() {
				
				@Override
				public void onSuccess(VendorReqDAO view) {
					//arraylists for receiving all the items
					 ArrayList<String> reqId = view.getReqId();
					 ArrayList<String> productType = view.getProductType();
					 ArrayList<String> productConfig = view.getProductConfig();
					 ArrayList<String> quantity = view.getQuantity();
					 ArrayList<String> status = view.getStatus();
					 
					
						
						
						rid.clear();
						ptype.clear();
						pconfig.clear();
						q.clear();
						action.clear();
						statusPanel.clear();
						poForm.clear();
						main.clear();
						
						rid.setSpacing(15);
						ptype.setSpacing(15);
						pconfig.setSpacing(15);
						q.setSpacing(15);
						statusPanel.setSpacing(15);
						action.setSpacing(15);
						poForm.setSpacing(15);
						Label l;
						//table title
						rid.add( (l = new Label("Request ID")));
						l.setStyleName("poReqDialogTitle");
						ptype.add( (l = new Label("Product Name")));
						l.setStyleName("poReqDialogTitle");
						pconfig.add((l = new Label("Product Configuation")));
						l.setStyleName("poReqDialogTitle");
						q.add( (l = new Label("quantity")));
						l.setStyleName("poReqDialogTitle");
						statusPanel.add(l = new Label("Status"));
						l.setStyleName("poReqDialogTitle");
						action.add((l = new Label("Reply")));
						l.setStyleName("poReqDialogTitle");
						poForm.add(l = new Label("PO Form"));
						l.setStyleName("poReqDialogTitle");
						//table body
						for(int i = 0 ; i < reqId.size() ; i++) {
							final String req = reqId.get(i);
							final String proname = productType.get(i);
							final String procon = productConfig.get(i);
							final String quan = quantity.get(i);
							final String s = status.get(i);
							
							rid.add( (l = new Label(req)));
							ptype.add( (l = new Label(proname)));
							pconfig.add( (l = new Label(procon)));
							q.add( (l = new Label(quan)));
							statusPanel.add(l = new Label(s));
							btn = new Button("reply");
							if(s.equals("granted") || s.equals("submitted"))
								btn.setEnabled(false);
							
							action.add(btn);
							replyButtonHandler(req);
							
							btn1 = new Button("po form");
							if(!s.equals("granted"))
								btn1.setEnabled(false);
							poForm.add(btn1);
							btn1.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									form.onPOForm(quan,req);
								}
							});
							
							
							
						}
					
					main.add(rid);
					main.add(ptype);
					main.add(pconfig);
					main.add(q);
					main.add(statusPanel);
					main.add(action);
					main.add(poForm);
					main.setStyleName("poReqBody");
					RootPanel.get("poReqView").add(main);

				}
				
				@Override
				public void onFailure(Throwable caught) {
					
				}
			});
			
		}
		//reply button handler
		private void replyButtonHandler(final String req) {
			btn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					MyDialog myDialog = new MyDialog(req);

		            int left = Window.getClientWidth()/ 3;
		            int top = Window.getClientHeight()/ 3; 
		            myDialog.setPopupPosition(left, top);
		            myDialog.show();
				}
			});
		}
	
}
