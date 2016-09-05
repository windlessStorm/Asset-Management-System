/**
 * its to see all the available stocks
 * and it also has the sorting capabilities
 */
package com.smt.client.home;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StockView {
	private VerticalPanel vp = new VerticalPanel();
	StockViewerHelper helper = new StockViewerHelper();
	public VerticalPanel stockViewer(final String reqId, final String productName) {
		RootPanel.get("menu").clear();
		RootPanel.get("stock").clear();
		RootPanel.get("DashBoard").clear();
    	RootPanel.get("adminDashboard").clear();
		RootPanel.get("vendorManagement").clear();
		RootPanel.get("poReply").clear();;
		vp.clear();
		//stock title bar
		vp.add(helper.stockMenu(reqId));
		vp.setStyleName("TableTitle");
		//stock table body
		helper.stockBody(reqId, productName);
		RootPanel.get("menu").add(vp);
		return vp;
	}
}
