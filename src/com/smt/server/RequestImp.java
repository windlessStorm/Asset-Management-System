/**
 * @author sumit
 * Here we are implementing all the unimplemented
 * method in the ReqService interface.
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
import com.smt.client.requestHistory.ReqDAO;
import com.smt.client.requestHistory.ReqService;
import com.smt.connection.GetDataSource;

public class RequestImp extends RemoteServiceServlet implements ReqService {

	private static final long serialVersionUID = 1L;
	
	 Connection con=null;
	 Statement st=null;
	 ResultSet rs1 = null;
	 ReqDAO req;
	  
	 WebApplicationContext context;
	 GetDataSource ds;
	 //array list to store values from request table
	 ArrayList<String> requestId = new ArrayList<String>();
	 ArrayList<String> empId = new ArrayList<String>();
	 ArrayList<String> productName = new ArrayList<String>();
	 ArrayList<String> productConfig = new ArrayList<String>();
	 ArrayList<String> status = new ArrayList<String>();
	 ArrayList<String> managerComment = new ArrayList<String>(); 

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
	  * Here we are inserting data to the table
	  * when a new request comes we are storing it to the request table by 
	  * this method.
	  */
	@Override
	public ReqDAO insertReq(String empId, String productName, String productConfig, String managerId) {
	
		call();
		String insertQuery = "insert into requestHistory (empId,productName,productConfig,managerid) values('" 
		+ empId + "','" + productName + "','" + productConfig + "','" + managerId + "')";
		 try {
			int x = st.executeUpdate(insertQuery);
			if(x > 0) {
				System.out.println("Update Successful!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return req;
	}
	
	/**
	 * Here we are retrieving all the table info by ordering through status
	 * then we are setting all this to the ReqDAO pojo class
	 */
	@Override
	public ReqDAO ReqViewAdmin() {
		 call();

		 
		 requestId.clear();
		 empId.clear();
		 productName.clear();
		 productConfig.clear();
		 status.clear();
		 managerComment.clear();	 
			 
			 
		String viewQueryAdmin = "select * from requestHistory order by status";
		
		try {
			rs1 = st.executeQuery(viewQueryAdmin);
			while(rs1.next()) {				
				requestId.add(rs1.getString(1));
				empId.add(rs1.getString(2));
				productName.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				status.add(rs1.getString(5));
				managerComment.add(rs1.getString(7));
			}
			
			
			req = new ReqDAO();
			req.setRequestId(requestId);
			req.setEmpId(empId);
			req.setProductName(productName);
			req.setProductConfig(productConfig);
			req.setStatus(status);
			req.setManagerComment(managerComment);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return req;
	}

	/**
	 * here we are selecting entries according to the employee id.
	 * so in the employee dash board we will show all those requests made by 
	 * the particular employee.
	 */
	@Override
	public ReqDAO ReqViewEmployee(String empId) {

		call();

		 
		 requestId.clear();
		 productName.clear();
		 productConfig.clear();
		 status.clear();
			 
			 
			 
		String viewQueryAdmin = "select * from requestHistory where empid = '" + empId + "' order by reqId desc";
		
		try {
			rs1 = st.executeQuery(viewQueryAdmin);
			while(rs1.next()) {				
				requestId.add(rs1.getString(1));
				productName.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				status.add(rs1.getString(5));
			}
			
			//setting all info to the ReqDAO pojo class
			req = new ReqDAO();
			req.setRequestId(requestId);
			req.setProductName(productName);
			req.setProductConfig(productConfig);
			req.setStatus(status);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return req;
	}

	/**
	 * here we are selecting entries according to the manager id.
	 * so in the team dash board we will show all those requests made by 
	 * the particular employees who are in that particular manager's team.
	 */
	@Override
	public ReqDAO ReqViewManager(String maanagerId) {
		call();

		 
		 requestId.clear();
		 empId.clear();
		 productName.clear();
		 productConfig.clear();
		 status.clear();
			 
			 
			 
		String viewQueryAdmin = "select * from requestHistory where managerid = '" + maanagerId + "' order by reqId desc";
		
		try {
			rs1 = st.executeQuery(viewQueryAdmin);
			while(rs1.next()) {				
				requestId.add(rs1.getString(1));
				empId.add(rs1.getString(2));
				productName.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				status.add(rs1.getString(5));
			}
			//setting all the info to the ReqDAO pojo class.
			req = new ReqDAO();
			req.setRequestId(requestId);
			req.setEmpId(empId);
			req.setProductName(productName);
			req.setProductConfig(productConfig);
			req.setStatus(status);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return req;
	}
	
	/**
	 * here we are updating the manager approval status and the manager comment for a 
	 * particular request.
	 */

	@Override
	public void managerAproval(String reqId, String status, String managerCmnt) {
		call();
		String updateQuery = "update requestHistory set status = '" + status 
				+ "', managerComment = '" + managerCmnt +"' where reqId = " + reqId;
		
		try {
			int x = st.executeUpdate(updateQuery);
			if(x > 0) 
				System.out.println("Update Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * here we are updating the status to "granted" attribute when admin 
	 * clicks on the granted button for a particular request.  
	 */
	@Override
	public void adminAproval(String reqId) {
		call();
		String updateQuery = "update requestHistory set status = 'granted' where reqId = " + reqId;
		
		try {
			int x = st.executeUpdate(updateQuery);
			if(x > 0) 
				System.out.println("Update Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * here we are updating the status to "pending" attribute when admin 
	 * clicks on the pending button for a particular request 
	 * when the item is not in the stock .  
	 */
	@Override
	public void adminPending(String reqId) {
		call();
		String updateQuery = "update requestHistory set status = 'pending' where reqId = " + reqId;
		
		try {
			int x = st.executeUpdate(updateQuery);
			if(x > 0) 
				System.out.println("Update Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * here we are selecting requests according to the
	 * manager id and the status name.
	 * By that we will be able to sort the requests according 
	 * to those which are approved, not approved and so on for a team requests.
	 */
	@Override
	public ReqDAO sortStatus(String statusName, String managerId) {
		call();

		 
		 requestId.clear();
		 empId.clear();
		 productName.clear();
		 productConfig.clear();
		 status.clear();
		 managerComment.clear();	 
			 
			 
		String viewQueryAdmin = "select * from requestHistory where status = '" + statusName +
				" ' and managerid = '" +  managerId + " ' order by reqId desc";
		
		try {
			rs1 = st.executeQuery(viewQueryAdmin);
			while(rs1.next()) {				
				requestId.add(rs1.getString(1));
				empId.add(rs1.getString(2));
				productName.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				status.add(rs1.getString(5));
				managerComment.add(rs1.getString(7));
			}
			
			//setting all info to the ReqDAO pojo class
			req = new ReqDAO();
			req.setRequestId(requestId);
			req.setEmpId(empId);
			req.setProductName(productName);
			req.setProductConfig(productConfig);
			req.setStatus(status);
			req.setManagerComment(managerComment);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return req;	
	}

	/**
	 * here we are selecting requests according to the
	 * the status name.
	 * By that we will be able to sort the requests according 
	 * to those which are approved, not approved and so on .
	 */
	
	@Override
	public ReqDAO sortStatusAdmin(String statusName) {
		call();

		 
		 requestId.clear();
		 empId.clear();
		 productName.clear();
		 productConfig.clear();
		 status.clear();
		 managerComment.clear();	 
			 
			 
		String viewQueryAdmin = "select * from requestHistory where status = '" + statusName + " ' order by reqId desc";
		
		try {
			rs1 = st.executeQuery(viewQueryAdmin);
			while(rs1.next()) {				
				requestId.add(rs1.getString(1));
				empId.add(rs1.getString(2));
				productName.add(rs1.getString(3));
				productConfig.add(rs1.getString(4));
				status.add(rs1.getString(5));
				managerComment.add(rs1.getString(7));
			}
			//setting all info to the ReqDAO pojo class
	
			req = new ReqDAO();
			req.setRequestId(requestId);
			req.setEmpId(empId);
			req.setProductName(productName);
			req.setProductConfig(productConfig);
			req.setStatus(status);
			req.setManagerComment(managerComment);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return req;	
	}
}
