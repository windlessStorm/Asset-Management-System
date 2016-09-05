/**
 * @author sumit
 * Here we are implementing all the unimplemented
 * method in the POService interface.
 * It extends RemoteServiceServlet so it will act like a j2ee
 * servlet. 
 */

package com.smt.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smt.client.admin.POService;
import com.smt.client.vendor.VendorReqDAO;
import com.smt.connection.GetDataSource;

public class POServiceImp extends RemoteServiceServlet implements POService {

	private static final long serialVersionUID = 1L;

	Connection con=null;
	 Statement st=null;
	 ResultSet rs1 = null;
	 VendorReqDAO vendor;
	 
	 //array list to store values from stock table
	 	private ArrayList<String> reqId = new ArrayList<String>();
		private ArrayList<String> venId = new ArrayList<String>();
		private ArrayList<String> productType = new ArrayList<String>();
		private ArrayList<String> productConfig = new ArrayList<String>();
		private ArrayList<String> quantity = new ArrayList<String>();
		private ArrayList<String> vendorComment = new ArrayList<String>();
		private ArrayList<String> status = new ArrayList<String>();

	 WebApplicationContext context;
	 GetDataSource ds;
	 //creating jdbc connection by taking data source from the getDataSource class
	 public void call()
	 {
			context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ds=context.getBean("getDS",GetDataSource.class);
			DataSource dataSource=ds.getDataSource();
			
			try {
				con=dataSource.getConnection();
				st = con.createStatement();
				System.out.println("connection successful!!");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	 }
	
	 /**
	  * here we are inserting all the purchase request info 
	  * to the porequest table.
	  * like the vendor whom admin is requesting 
	  * which product vendor is requesting
	  * the product configuration
	  * number of quantity vendor is requesting 
	  */
	 
	@Override
	public void PORequest(ArrayList<String> vendorid, String productType, String productConfig, int quantity) {
		call();
		for(int i = 0 ; i < vendorid.size() ; i++) {
			
		String insertQuery = "insert into porequest (vendorid, producttype, productconfig , quantity) values('" 
				+ vendorid.get(i) + "','" + productType + "','" + productConfig + "'," + quantity + ")";
		System.out.println(insertQuery);
				 try {
					int x = st.executeUpdate(insertQuery);
					if(x > 0) {
						System.out.println("Update Successful!!!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * here we are showing all the purchase order request comes for 
	 * the particular vendor.
	 * when a vendor will login vendor will see all the request comes.
	 */
	@Override
	public VendorReqDAO vendorPODashboard(String vid) {
		call();
		
		reqId.clear();
		productType.clear();
		productConfig.clear();
		quantity.clear();
		status.clear();
		
		String selectQuery = "select * from porequest where vendorid = '" + vid + "'";
		
		try {
			rs1 = st.executeQuery(selectQuery);
			while(rs1.next()) {
				reqId.add(rs1.getString(1));
				productType.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				quantity.add(rs1.getString(6));
				status.add(rs1.getString(7));
			}
			//setting data to the VendorReqDAO pojo class
			vendor = new VendorReqDAO();
			vendor.setReqId(reqId);
			vendor.setProductType(productType);
			vendor.setProductConfig(productConfig);
			vendor.setQuantity(quantity);
			vendor.setStatus(status);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return vendor;
	}
	
	/**
	 * here we are updating the table when a vendor 
	 * comments something about the request.
	 */

	@Override
	public void vendorComment(String vendorComment, String reqId) {
		call();
		String updateQuery = "update porequest set vendorcomment = '" + vendorComment + 
				"' where reqid = " + reqId  ;
		
		try {
			int x = st.executeUpdate(updateQuery);
			if(x > 0) {
				System.out.println("update successful!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * its to show all the PO requests made by the admin 
	 * along with the vendor comment and current status.
	 */
	
	@Override
	public VendorReqDAO adminViewVendorReply() {
		
		call();
		
		reqId.clear();
		productType.clear();
		productConfig.clear();
		quantity.clear();
		vendorComment.clear();
		status.clear();
		venId.clear();
		String selectQuery = "select * from porequest ";
		
		try {
			rs1 = st.executeQuery(selectQuery);
			while(rs1.next()) {
				reqId.add(rs1.getString(1));
				productType.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				quantity.add(rs1.getString(6));
				String cmnt = rs1.getString(5);
				if(cmnt.isEmpty() || cmnt.equals("null")) {
					cmnt = "Not Replyed Till Now";
				}
				System.out.println(cmnt);
				vendorComment.add(cmnt);
				status.add(rs1.getString(7));
				venId.add(rs1.getString(2));

			}
			vendor = new VendorReqDAO();
			vendor.setReqId(reqId);
			vendor.setProductType(productType);
			vendor.setProductConfig(productConfig);
			vendor.setQuantity(quantity);
			vendor.setVendorComment(vendorComment);
			vendor.setStatus(status);
			vendor.setVenId(venId);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return vendor;
	}

	/**
	 * by seeing vendor comment when admin grant a vendor
	 * here that status change is happening.
	 */
	@Override
	public void adminReplyToVendor(String reqId , String status) {
		call();
		String updateQuery = "update porequest set status = '" + status + 
				"' where reqid = " + reqId  ;
		
		try {
			int x = st.executeUpdate(updateQuery);
			if(x > 0) {
				System.out.println("update successful!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
