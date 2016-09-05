/**
 * @author sumit
 * Here we are implementing all the unimplemented
 * method in the StockService interface.
 * It extends RemoteServiceServlet so it will act like a j2ee
 * servlet. 
 */

package com.smt.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smt.client.stock.StockDao;
import com.smt.client.stock.StockService;
import com.smt.connection.GetDataSource;

public class StockImp extends RemoteServiceServlet implements StockService{

	private static final long serialVersionUID = 1L;
	
	 Connection con=null;
	 Statement st=null;
	 ResultSet rs1 = null;
	 PreparedStatement pStatement;
	 StockDao stock;
	 //array list to store values from stock table
	 ArrayList<String> productId = new ArrayList<String>();
	 ArrayList<String> productName = new ArrayList<String>();
	 ArrayList<String> productConfig = new ArrayList<String>();
	 ArrayList<String> warranty = new ArrayList<String>();
	 
	 
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
	  * Here we are selecting stock according to the product name
	  * by that we will be able to check stock according to the 
	  * request type means if a new request comes for desktop we will
	  * be able to show only available desktop stock.
	  */
	@Override
	public StockDao checkStock(String reqId, String pName) {
		call();
		 productId.clear();
		 productName.clear();
		 productConfig.clear();
		 warranty.clear();
			 
			 
			 
		String viewQueryStock = "select * from stock where productname = '" + pName + "'";
		
		try {
			rs1 = st.executeQuery(viewQueryStock);
			if(rs1 == null) {
				throw new SQLException();
			}
			while(rs1.next()) {				
				productId.add(rs1.getString(1));
				productName.add(rs1.getString(2));
				productConfig.add(rs1.getString(3));
				warranty.add(rs1.getString(4));
			}
			//setting data to the StockDao pojo class
			stock = new StockDao();
			stock.setProductId(productId);
			stock.setProductName(productName);
			stock.setProductConfig(productConfig);
			stock.setWarranty(warranty);
			stock.setReqId(reqId);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return stock;
	}

	/**
	 * here we are removing a item from the stock
	 * according to the product id.
	 * when admin will assign a product to a particular 
	 * employee we need to delete it from the stock.
	 */
	@Override
	public void deleteStock(String productId) {
		call();
		String delQuery = "delete from stock where productid = '" + productId + "'";
		try {
			int x = st.executeUpdate(delQuery);
			if(x > 0) {
				System.out.println("deleted successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 /**
	  * Here we are selecting stock according to the product name
	  * by that we will be able to check stock according to the 
	  * request type means if a new request comes for desktop we will
	  * be able to show only available desktop stock.
	  * it will work for sorting the stock according to the product.
	  */
	
	@Override
	public StockDao sortStock(String pname) {
		call();
		
		 productId.clear();
		 productName.clear();
		 productConfig.clear();
		 warranty.clear();
			 
			 
			 
		String viewQueryStock = "select * from stock where productname = '" + pname + " '";
		
		try {
			rs1 = st.executeQuery(viewQueryStock);
			while(rs1.next()) {				
				productId.add(rs1.getString(1));
				productName.add(rs1.getString(2));
				productConfig.add(rs1.getString(3));
				warranty.add(rs1.getString(4));
			}
			//setting data to StockDao pojo class
			stock = new StockDao();
			stock.setProductId(productId);
			stock.setProductName(productName);
			stock.setProductConfig(productConfig);
			stock.setWarranty(warranty);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return stock;
	}
	
	/**
	 * its to view all the item from the stock.
	 * when admin wants to see all the available 
	 * stock he/she also can see that by this method.
	 */
	@Override
	public StockDao viewStock() {

		
		call();
		
		
		 productId.clear();
		 productName.clear();
		 productConfig.clear();
		 warranty.clear();
		
		
		String viewQuery = "select * from stock";
		
		try {
			rs1 = st.executeQuery(viewQuery);
			if(rs1 == null) {
				throw new SQLException();
			}
			while(rs1.next()) {				
				productId.add(rs1.getString(1));
				productName.add(rs1.getString(2));
				productConfig.add(rs1.getString(3));
				warranty.add(rs1.getString(4));
			}
			System.out.println(productId);
			stock = new StockDao();
			stock.setProductId(productId);
			stock.setProductName(productName);
			stock.setProductConfig(productConfig);
			stock.setWarranty(warranty);
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return stock;
	}
	
	/**
	 * its to insert new item to the stock.
	 * when vendors sends some item all the 
	 * product information we need to store in here.
	 */

	@Override
	public void insertStock(ArrayList<String> pid, ArrayList<String> pname, ArrayList<String> pconfig,
			ArrayList<String> warranty) {
		
		call();
		for(int i = 0 ; i < pid.size() ; i++) {
		String insertQuery = "insert into stock values ('" + pid.get(i) + "','" + pname.get(i)
								+ "','" + pconfig.get(i) + "','" + warranty.get(i) + "')";
		System.out.println(insertQuery);

		try {
			int x = st.executeUpdate(insertQuery);
			if(x > 0) {
				System.out.println("update successful");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}
	}
}
