package com.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Skills;
import com.payroll.util.ConnectionUtil;

public class SkillDAO {

	public SkillDAO() {
		// TODO Auto-generated constructor stub
	}
	public int deleteSkills(Connection connObj,Skills skills) throws PayrollException
	{
		String query="delete from skills where skill_id =?;";
		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			pstmt=connObj.prepareStatement(query);
			pstmt.setInt(1,skills.getSkillId());
			
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
	public int registerSkill(Connection connection,Skills skills) throws PayrollException//SQLException
	{
		int generatedId=0;
		
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="insert into skills(skill_name) values(?)";
		ResultSet result=null;

		try 
		{
			//connection=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query);
			// getting username and password from logindetails parameter
			pstmt.setString(2,skills.getSkillName());
			
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
				} */	
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return generatedId;
		
	}
	public int updateSkill(Connection connection,Skills skills) throws PayrollException//SQLException
	{
		int count=0;
		
		//Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="update skills set skill_name=? where skill_id=?";
		ResultSet result=null;

		try 
		{
			//connection=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connection.prepareStatement(query);
			// getting username and password from logindetails parameter
			pstmt.setString(1,skills.getSkillName());
			pstmt.setInt(2,skills.getSkillId());
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
				/*if (connObj != null) 
				{
					connObj.close();
				} */	
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return count;
		
	}
	public List<Skills> fetchAllSkill() throws PayrollException//SQLException
	{
		Connection connObj=null;
		PreparedStatement pstmt=null;
		String query="select * from skills;";
		ResultSet result=null;
		
		List<Skills> skillList=new ArrayList<>();
		
		try 
		{
			connObj=ConnectionUtil.getConnection();
			//use PrepareStatement because where clause is used and is used each time
			pstmt=connObj.prepareStatement(query);
			
			result=pstmt.executeQuery();
			
			while(result.next())
			{
				int skillId=result.getInt("skill_id");
				String skillName=result.getString("skill_name");
				
				Skills skills=new Skills(skillId,skillName);
				skillList.add(skills);
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
				} */	
			}
			catch (SQLException e) 
			{
					throw new PayrollException("SQL EXCEPTION");
					//System.out.println("Error While Closing Connection");
			}
		}
		return skillList;
		
	}
}
