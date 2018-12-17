package com.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;

public class EmployeeSkillSetDAO {

	public EmployeeSkillSetDAO() {
		// TODO Auto-generated constructor stub
	}
	public int deleteSkillSet(Connection connObj,Employee employee) throws PayrollException
	{
		String query="delete from emp_skillset where fk_emp_id =?;";
		PreparedStatement pstmt=null;
		int count=0;
		
		try
		{
			
			pstmt=connObj.prepareStatement(query);
			pstmt.setInt(1,employee.getEmpId());
			count=pstmt.executeUpdate();
			//EmployeeDAO empDao=new EmployeeDAO();
			//empDao.deleteEmployee(connObj,employee);
			
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
	public int registerSkillSet(Connection connection,EmployeeSkillSet empSkill) throws PayrollException//SQLException
	{
		int generatedId=0;
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="insert into emp_skillset(fk_skill_id,fk_emp_id) values(?,?)";
		ResultSet result=null;
		try 
		{
			//connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,empSkill.getSkills().getSkillId());
			pstmt.setInt(2,empSkill.getEmployee().getEmpId());
			
			pstmt.executeUpdate();
			result=pstmt.getGeneratedKeys();
			if(result.next())
			{
				generatedId=result.getInt(1);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PayrollException("SQL EXCEPTION"+e);
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
				}*/	
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return generatedId;
	}
	/*public int removeSkillSet(Connection connection,int empId) throws PayrollException//SQLException
	{
		int count=0;
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="delete from emp_skillset where fk_emp_id=?";
		ResultSet result=null;
		try 
		{
			//connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query);
			pstmt.setInt(1,empId);
			
			count=pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new PayrollException("SQL EXCEPTION");
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
	}*/
	
}
