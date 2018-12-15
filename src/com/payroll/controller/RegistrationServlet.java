package com.payroll.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.payroll.dao.AddressDAO;
import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.EmployeeSkillSetDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;
import com.payroll.model.Department;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;
import com.payroll.model.Skills;
import com.payroll.services.EmployeeService;
import com.payroll.services.EmployeeSkillSetService;
import com.payroll.util.ConnectionUtil;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empName=request.getParameter("empName");
		String salary=request.getParameter("empSalary");
		Double empSalary=Double.parseDouble(salary);
		
		String did=request.getParameter("departmentId");
		int departmentId=Integer.parseInt(did);
		
		String street=request.getParameter("street");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		
		String[] skills=request.getParameterValues("skills");
		
		Employee employee=new Employee(0,empName,empSalary);
		Department department=new Department();
		department.setDepartmentId(departmentId);
		
		Address address=new Address(0,street,city,state,country);
		
		AddressDAO addressDao=new AddressDAO();
		
		PrintWriter out=response.getWriter();
		
		Connection connObj=null;
		try
		{
			connObj=ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			
			// getting empid from dao register()
			int addressId=addressDao.registerAddress(connObj, address);
			address.setAddressId(addressId);
			
			employee.setAddress(address);
			employee.setDepartment(department);
			EmployeeService dao=new EmployeeService();
			int empId=dao.registerEmployee(connObj,employee);
			employee.setEmpId(empId);
			// getting address id and setting to address
			
		
		
			EmployeeSkillSet empSkillSet=new EmployeeSkillSet();
			
			for(String string:skills){
				
				int skillId=Integer.parseInt(string);
				
				empSkillSet.setEmpSkillSetId(skillId);
				
				Skills skill1=new Skills();
				skill1.setSkillId(skillId);
				
				empSkillSet.setSkills(skill1);
				empSkillSet.setEmployee(employee);
				
				EmployeeSkillSetService skillSetDao=new EmployeeSkillSetService();
				skillSetDao.registerSkillSet(connObj, empSkillSet);
			}
			
			if(empId!=0)
			{
				out.println("YOUR EMPLOYEE ID: "+empId);
				out.println("PLEASE REMEMBER FOR FUTURE!!");
			}
			connObj.commit();
		}
		catch(PayrollException payExcep){
			payExcep.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try{
				
				if(connObj!=null)
				{
					connObj.close();
				}
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
			
		}
		
	}

}
