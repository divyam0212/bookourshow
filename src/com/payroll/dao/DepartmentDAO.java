package com.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;*/
import java.util.ArrayList;
import java.util.List;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Department;
//import com.payroll.util.ConnectionUtil;
import com.payroll.util.ConnectionUtil;

public class DepartmentDAO {

	public DepartmentDAO() {
		// TODO Auto-generated constructor stub
	}
	public int deleteDepartment(Connection connObj,Department department) throws PayrollException
	{
		String query="delete from department where dept_id =?;";
		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			pstmt=connObj.prepareStatement(query);
			pstmt.setInt(1,department.getDepartmentId());
			
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
	public int registerDepartment(Connection connection,Department department) throws PayrollException//throws SQLException
	{
		int generatedId=0;
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="insert into department(dept_name,dept_location) values(?,?)";
		ResultSet result=null;
		try 
		{
			//connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query);
			pstmt.setString(1,department.getDepartmentName());
			pstmt.setString(2,department.getDepartmentLocation());	// getting username and password from logindetails parameter
			pstmt.executeUpdate();
			
			result=pstmt.getGeneratedKeys();
			if(result.next())
			{
				generatedId=result.getInt(1);
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
				if (result != null) 
				{
					result.close();
				} 
				if (pstmt != null) 
				{
					pstmt.close();
				}
				/*if (connObj != null) 
				{
					connObj.close();
				} 	*/
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return generatedId;
	}
	public int updateDepartment(Connection connection,Department department) throws PayrollException//throws SQLException
	{
		int count=0;
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="update department set dept_name=?,dept_location=? where dept_id=?";
		ResultSet result=null;
		try 
		{
			//connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query);
			pstmt.setString(1,department.getDepartmentName());
			pstmt.setString(2,department.getDepartmentLocation());	// getting username and password from logindetails parameter
			pstmt.setInt(3,department.getDepartmentId());
			pstmt.executeUpdate();
			
			
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
				/*if (connObj != null) 
				{
					connObj.close();
				} 	*/
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return count;
	}
	public List<Department> fetchAllDepartment()
	{
		List<Department> deptList=new ArrayList<>();
		String query="select * from department;";

		PreparedStatement pstmt=null;
		ResultSet result=null;
		Connection connection=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(query);
			
			result=pstmt.executeQuery();
			
			while(result.next())
			{
				int deptId=result.getInt("dept_id");
				String deptName=result.getString("dept_name");
				String deptLoca=result.getString("dept_location");
				Department dept=new Department(deptId, deptName, deptLoca);
		
				deptList.add(dept);
				
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		} catch (PayrollException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try{
				if(result!=null){
					result.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(connection!=null)
				{
					connection.close();
				}
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
			
		}
		System.out.println(deptList);
		return deptList;
	}
}
