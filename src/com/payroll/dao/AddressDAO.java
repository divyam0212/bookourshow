package com.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;
import com.payroll.util.ConnectionUtil;

public class AddressDAO {

	public AddressDAO() {
		// TODO Auto-generated constructor stub
	}
	public int deleteAddress(Address address) throws PayrollException
	{
		
		String query="delete from emp_address where address_id=?";
				
		Connection connObj=null;
		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			connObj=ConnectionUtil.getConnection();
			pstmt=connObj.prepareStatement(query);
			pstmt.setInt(1, address.getAddressId());
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
		return count;
	}
	public int registerAddress(Connection connection,Address address) throws PayrollException//throws SQLException
	{
		int generatedId=0;
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="insert into emp_address(street,city,state,country) values(?,?,?,?)";
		ResultSet result=null;
		try 
		{
			//connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,address.getStreet());
			pstmt.setString(2,address.getCity());	// getting username and password from logindetails parameter
			pstmt.setString(3,address.getState());
			pstmt.setString(4,address.getCountry());
			
			pstmt.executeUpdate();
			result=pstmt.getGeneratedKeys();
			if(result.next())
			{
				generatedId=result.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			//throw new PayrollException("SQL EXCEPTION");
			System.out.println(e);
			e.printStackTrace();
		}
		finally
		{
			try{
				if (result != null) 
				{
					result.close();
				} 
				if (pstmt != null) 
				{
					pstmt.close();
				}
				/*(if (connObj != null) 
				{
					connObj.close();
				} 	*/
			}
			catch (SQLException e) 
			{
					//System.out.println("Error While Closing Connection");
					throw new PayrollException("Error While Closing Connection");
			}
		}
		return generatedId;
	}
	public int updateAddress(Connection connection,Address address) throws PayrollException//throws SQLException
	{
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="update emp_address set street=?,city=?,state=?,country=? where address_id=?";
		ResultSet result=null;
		int count=0;
		try 
		{
			//connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query);
			pstmt.setString(1,address.getStreet());
			pstmt.setString(2,address.getCity());	// getting username and password from logindetails parameter
			pstmt.setString(3,address.getState());
			pstmt.setString(4,address.getCountry());
			pstmt.setInt(5,address.getAddressId());
			count=pstmt.executeUpdate();
			
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
				if (result != null) 
				{
					result.close();
				} 
				if (pstmt != null) 
				{
					pstmt.close();
				}
				/*(if (connObj != null) 
				{
					connObj.close();
				} 	*/
			}
			catch (SQLException e) 
			{
					//System.out.println("Error While Closing Connection");
					throw new PayrollException("Error While Closing Connection");
			}
		}
		return count;
	}
}
