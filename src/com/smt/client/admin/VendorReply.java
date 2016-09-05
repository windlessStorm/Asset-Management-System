/**
 * @author sumit
 * 	here we are showing all the Vendor Reply from the database
 *  we have created a table by which we are showing all the required 
 *  info along with a grant button for granting a particular vendor rsponse. 
 */

package com.smt.client.admin;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.vendor.VendorReqDAO;

public class VendorReply {
	//instance of POServiceAsync interface
	POServiceAsync vendorReply = GWT.create(POService.class);
	VerticalPanel rid = new VerticalPanel();
	VerticalPanel ptype = new VerticalPanel();
	VerticalPanel pconfig = new VerticalPanel();
	VerticalPanel q = new VerticalPanel();
	VerticalPanel action = new VerticalPanel();
	VerticalPanel statusPanel = new VerticalPanel();
	VerticalPanel vcmnt = new VerticalPanel();
	VerticalPanel vendorID = new VerticalPanel();

	Button btn;
	HorizontalPanel main = new HorizontalPanel();
	
	public void onReply() {
		//calling the adminViewVendorReply method implemented in the server side.
		vendorReply.adminViewVendorReply(new AsyncCallback<VendorReqDAO>() {
			
			@Override
			public void onSuccess(VendorReqDAO view) {
				//arraylists to receive all the informations
				 ArrayList<String> reqId = view.getReqId();
				 ArrayList<String> venId = view.getVenId();
				 ArrayList<String> productType = view.getProductType();
				 ArrayList<String> productConfig = view.getProductConfig();
				 ArrayList<String> quantity = view.getQuantity();
				 ArrayList<String> vendorComment = view.getVendorComment();
				 ArrayList<String> status = view.getStatus();
				 
					rid.clear();
					ptype.clear();
					pconfig.clear();
					q.clear();
					statusPanel.clear();
					action.clear();
					vcmnt.clear();
					main.clear();
					vendorID.clear();
					
					rid.setSpacing(15);
					ptype.setSpacing(15);
					pconfig.setSpacing(15);
					q.setSpacing(15);
					vendorID.setSpacing(15);
					vcmnt.setSpacing(15);
					statusPanel.setSpacing(15);
					action.setSpacing(12);
					
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
					vendorID.add(l = new Label("Vendor Name"));
					l.setStyleName("poReqDialogTitle");
					vcmnt.add(l = new Label("Vendor Comment"));
					l.setStyleName("poReqDialogTitle");
					statusPanel.add(l = new Label("Status"));
					l.setStyleName("poReqDialogTitle");
					action.add((l = new Label("Reply")));
					l.setStyleName("poReqDialogTitle");
				
					//body of the table
					for(int i = 0 ; i < reqId.size() ; i++) {
						final String req = reqId.get(i);
						final String proname = productType.get(i);
						final String procon = productConfig.get(i);
						final String quan = quantity.get(i);
						final String s = status.get(i);
						final String cmnt = vendorComment.get(i);
						final String vid = venId.get(i);
						
						rid.add( (l = new Label(req)));
						ptype.add( (l = new Label(proname)));
						pconfig.add( (l = new Label(procon)));
						q.add( (l = new Label(quan)));
						vendorID.add(l = new Label(vid));
						vcmnt.add(l = new Label(cmnt));
						statusPanel.add(l = new Label(s));
						btn = new Button("grant");
						action.add(btn);
						if(cmnt.equals("Available"))
							btn.setEnabled(true);
						else
							btn.setEnabled(false);
						onGranting(req);
						
						
						
					}
					//adding all to main panel
					main.add(rid);
					main.add(ptype);
					main.add(pconfig);
					main.add(q);
					main.add(vendorID);
					main.add(vcmnt);
					main.add(statusPanel);
					main.add(action);
					main.setStyleName("poReqBody");

			}
			//if any thing goes wrong
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		//adding it to root panel
		RootPanel.get("poReply").add(main);

	}
	//grant button handler
	private void onGranting(final String reqId) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final String status = "granted";
				//sending info to server side
				vendorReply.adminReplyToVendor(reqId, status, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(Void result) {
					
					}
				});
			}
		});
	}
}
