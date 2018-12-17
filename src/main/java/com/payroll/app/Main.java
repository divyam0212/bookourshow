package com.payroll.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payroll.dao.AddressDAO;
import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.EmployeeSkillSetDAO;
import com.payroll.dao.LoginDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;
import com.payroll.model.Department;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;
import com.payroll.model.LoginDetails;
import com.payroll.model.Skills;
import com.payroll.util.ConnectionUtil;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		Connection connection=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			
			LoginDetails login=new LoginDetails("admin","admin");
			LoginDAO dao=new LoginDAO();
			boolean check=dao.loginCheck(login);
			
			if(check)
			{
				System.out.println("Logged In "+"Home Page Displayed");
			}
			else
			{
				System.out.println("Cannot Login"+"Error Page");
			}
			
			//Department d=new Department(10,"HR","Hyd");
			Department d=new Department(20,"FIN","Hyd");
			Address address=new Address(0,"RedHouse","Chennai","Tamil Nadu","India");
			//Address address=new Address(0,"LightHouse","Chennai","Tamil Nadu","India");
			
			/*Skills skill1=new Skills(1,"java");
			Skills skill2=new Skills(2,".net");*/
			
			Skills skill1=new Skills(3,"php");
			Skills skill2=new Skills(5,"cpp");
			
			List<Skills> list=new ArrayList<>();
			list.add(skill1);
			list.add(skill2);
			
			AddressDAO addressDAO=new AddressDAO();
			int addressId=addressDAO.registerAddress(connection,address);
			address.setAddressId(addressId);

			//Employee employee=new Employee(0,"raju",1234);
			Employee employee=new Employee(0,"raj",12345);
			employee.setAddress(address);
			employee.setDepartment(d);
			employee.setSkillsList(list);
			
			EmployeeDAO empDao=new EmployeeDAO();
			int empId=empDao.registerEmployee(connection,employee);
			System.out.println("Employee registered: "+empId);
			employee.setEmpId(empId);
			
			EmployeeSkillSet skillSet1=new EmployeeSkillSet(0,skill1,employee);
			//EmployeeSkillSet skillSet2=new EmployeeSkillSet(0,skill2,employee);
			EmployeeSkillSet skillSet2=new EmployeeSkillSet(0,skill2,employee);
			
			EmployeeSkillSetDAO employeeSkillSetDao1=new EmployeeSkillSetDAO();
			int sid1=employeeSkillSetDao1.registerSkillSet(connection,skillSet1);
			
			EmployeeSkillSetDAO employeeSkillSetDao2=new EmployeeSkillSetDAO();
			int sid2=employeeSkillSetDao2.registerSkillSet(connection,skillSet2);
			skillSet1.setEmpSkillSetId(sid1);
			skillSet1.setEmpSkillSetId(sid2);
			
			connection.commit();
		}
		catch(PayrollException payroll)
		{
			System.out.println("PAYROLL EXCEPTION\n"+payroll.getMessage());
		}
		catch( SQLException e)
		{
			try{
				connection.rollback();
			}
			catch(SQLException e1)
			{
				//e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
		}
		finally
		{
			try
			{
				if(connection!=null)
				{
					connection.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println("Error in closing file");
			}
		}
	}

}
