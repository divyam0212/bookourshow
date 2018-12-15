package com.payroll.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.payroll.exceptions.UserNotFoundException;

public class ConnectionUserUtil {

	public ConnectionUserUtil() {
		
	}
	public static Connection getConnection() throws UserNotFoundException,SQLException{
		Connection connObj=null;
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		connObj = DriverManager.getConnection("jdbc:mysql://localhost/payroll", "root", "root");
		if (connObj == null) 
		{
				System.out.println("No Connection");
		} 
		else 
		{
			System.out.println("Connection is Established" + connObj);
		}
		return connObj;
	}

}
