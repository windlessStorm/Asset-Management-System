/**
 * @author sumit
 * here we are implementing the unimplemented login method 
 * in the service interface.
 * It extends RemoteServiceServlet so it will act like a j2ee
 * servlet.
 */
package com.smt.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

//import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smt.client.login.user.UserDAO;
import com.smt.connection.GetDataSource;

public class ServiceImp extends RemoteServiceServlet implements com.smt.client.login.user.Service{
	//for logging
	//Logger log = Logger.getLogger(ServiceImp.class);
	
	private static final long serialVersionUID = 1L;
	 Connection con=null;
	 Statement st=null;
	 UserDAO usr;
	 ResultSet rs=null;

	 WebApplicationContext context;
	 GetDataSource ds;
	 
	 //calling the jdbc connection from GetDatasource.
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
	
	@Override
	public UserDAO login(String u, String p) {
		 call();
		 
		 //to validate input userid and password with the data base.

		 try
		 {

		   rs = st.executeQuery("select * from usertable where userid='" + u + "' and password='" + p+"'" );
		   
		   if(rs == null) {
			   throw new SQLException();
		   }
		   
		   if(rs.next())
		   {
			   //if login successful
			   //set all the info to the UserDAO pojo class.
			   usr=new UserDAO();
			   usr.setUserName(u);
			   usr.setRole(rs.getString(4));
			   usr.setName(rs.getString(3));
			   usr.setManagerId(rs.getString(5));
			  // log.info("login successful!!");
		   }
		   else {
			   Window.alert("error");
			   //if incorrect throw exception
			   System.out.println("pasword username incorrect");
			   throw new SQLException();
			   
			   
			   
			   
			   
			   
		   }
		   
		 }
		 catch(SQLException e)
		 {
			 //log.error(e.getLocalizedMessage());
			 Window.alert("Error" + e);

		 }
		 

		return usr;		
	}
}
