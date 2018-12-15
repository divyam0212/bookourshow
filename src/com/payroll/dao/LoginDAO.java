package com.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.LoginDetails;
import com.payroll.util.ConnectionUtil;

public class LoginDAO {

	public LoginDAO() {
	}
	public int deleteLoginDetails(Connection connObj,LoginDetails login) throws PayrollException
	{
		String query="delete from login where username =?;";
		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			pstmt=connObj.prepareStatement(query);
			pstmt.setString(1,login.getUserName());
			
			count=pstmt.executeUpdate();
			
		}
		catch (SQLException e) 
		{
			throw new PayrollException("SQL EXCEPTION");
		}
		finally
		{
			try{
				/*if (result != null) 
				{
					result.close();
				} */
				if (pstmt != null) 
				{
					pstmt.close();
				}
				/*if (connObj != null) 
				{
					connObj.close();
				}	*/
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return count;
	}
	public boolean loginCheck(LoginDetails loginDetails) throws PayrollException//SQLException
	{
			boolean flag=false;
			Connection connObj=null;
			PreparedStatement pstmt=null;
			String query="select * from login where username=? and password=?";
			ResultSet rs=null;
			try 
			{
				connObj=ConnectionUtil.getConnection();
				//use PrepareStatement because where clause is used and is used each time
				pstmt=connObj.prepareStatement(query);
				
				pstmt.setString(1,loginDetails.getUserName());	// getting username and password from logindetails parameter
				pstmt.setString(2,loginDetails.getPassword());
				
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					flag=true;
				}
			} 
			catch (SQLException e) 
			{
				throw new PayrollException("SQL EXCEPTION");
				/*System.out.println(e);
				e.printStackTrace();*/
			}
			finally
			{
				try{
					if (rs != null) 
					{
						rs.close();
					} 
					if (pstmt != null) 
					{
						pstmt.close();
					}
					if (connObj != null) 
					{
						connObj.close();
					} 	
				}
				catch (SQLException e) 
				{
					 	throw new PayrollException("SQL EXCEPTION");
						//System.out.println("Error While Closing Connection");
				}
			}
			return flag;
	}

}
