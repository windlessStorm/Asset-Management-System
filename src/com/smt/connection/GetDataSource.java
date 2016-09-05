/**
 * Here we are creating a data source instance 
 * by the setter we will get the data source from the
 * applicationContext.xml
 * and from the getter method we will get the data source
 * where it needs.
 */
package com.smt.connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * public @interface Component
   Indicates that an annotated class is a "component". 
   Such classes are considered as candidates for auto-detection
   when using annotation-based configuration and classpath scanning.
 * @author sumit
 *
 */
@Component("getDS")
public class GetDataSource {
	/**
	 *  The @Autowired annotation provides more fine-grained control
	 *  over where and how autowiring should be accomplished. 
	 *  The @Autowired annotation can be used to autowire bean on 
	 *  the setter method just like @Required annotation, constructor,
	 *  a property or methods with arbitrary names and/or multiple arguments.
	 */
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
