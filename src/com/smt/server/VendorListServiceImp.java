/**
 * @author sumit
 * Here we are implementing all the unimplemented
 * method in the VendorListService interface.
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
import com.smt.client.admin.VendorListDAO;
import com.smt.client.admin.VendorListService;
import com.smt.connection.GetDataSource;

public class VendorListServiceImp extends RemoteServiceServlet implements VendorListService {

	private static final long serialVersionUID = 1L;
	
	 Connection con=null;
	 Statement st=null;
	 ResultSet rs1 = null;
	 VendorListDAO vendor;
	 
	 WebApplicationContext context;
	 GetDataSource ds;
	 //array list to store values from vendor table
	 ArrayList<String> vendorId = new ArrayList<String>();
	 ArrayList<String> vendorName = new ArrayList<String>();
	 ArrayList<String> vendorType = new ArrayList<String>();
	
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
	 * here we are selecting all the vendors available in the vendor 
	 * table.when admin clicks on the vendor list button it comes 
	 * here and receive all the vendor information. 
	 */
	@Override
	public VendorListDAO getVendorList() {
		call();
		
		vendorId.clear();
	 	vendorName.clear();
	 	vendorType.clear();
		
		String selectQuery = "select * from vendorList";
		
		try {
			rs1 = st.executeQuery(selectQuery);
			
			while(rs1.next()) {
				vendorId.add(rs1.getString(1));
				vendorName.add(rs1.getString(2));
				vendorType.add(rs1.getString(3));
			}
			//setting data to the VendorLisDAO pojo class
			vendor = new VendorListDAO();
			vendor.setVendorId(vendorId);
			vendor.setVendorName(vendorName);
			vendor.setVendorType(vendorType);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return vendor;
	}

}
