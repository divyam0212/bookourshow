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

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;
import com.payroll.model.Department;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;
import com.payroll.model.Skills;
import com.payroll.services.AddressService;
import com.payroll.services.EmployeeService;
import com.payroll.services.EmployeeSkillSetService;
import com.payroll.util.ConnectionUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			String eid=request.getParameter("empId");
			int empId=Integer.parseInt(eid);
			
			String empName=request.getParameter("empName");
			String salary=request.getParameter("empSalary");
			Double empSalary=Double.parseDouble(salary);
			
			Employee employee=new Employee(empId,empName,empSalary);
			
			String did=request.getParameter("departmentId");
			int departmentId=Integer.parseInt(did);
			
			Department department=new Department();
			
			//String[] did=request.getParameterValues("department");
			department.setDepartmentId(departmentId);
			employee.setDepartment(department);
			String aid=request.getParameter("addressId");
			int addressId=Integer.parseInt(aid);
			String street=request.getParameter("street");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String country=request.getParameter("country");
			
			Address address=new Address(addressId, street, city, state, country);
			employee.setAddress(address);
			
			String[] skills=request.getParameterValues("skills");
			Connection connObj=null;
			EmployeeSkillSetService dao=new EmployeeSkillSetService();
			
			
			try{
				EmployeeService employeeDao=new EmployeeService();
				connObj=ConnectionUtil.getConnection();
				connObj.setAutoCommit(false);
				
				
				int count=employeeDao.updateEmployee(connObj, employee);
				
				AddressService addressDao=new AddressService();
				int count1=addressDao.updateAddress(connObj, address);
				
				dao.deleteSkillSet(connObj, employee);
				
				for(String skillId:skills)
				{
					Skills skill=new Skills();
					skill.setSkillId(Integer.parseInt(skillId));
					
					EmployeeSkillSet skillSet=new EmployeeSkillSet();
					skillSet.setSkills(skill);
					skillSet.setEmployee(employee);
					
					int sid=dao.registerSkillSet(connObj, skillSet);
					
					skillSet.setEmpSkillSetId(sid);
				}
				PrintWriter out=response.getWriter();
				connObj.commit();
				out.print("UPDATED SUCCESSFULLY");
				/*
				List<Employee> list=employeeDao.fetchAllData(null);
				
				HttpSession session=request.getSession();
				session.setAttribute("list", list);
				response.sendRedirect("success.jsp");
				*/
				
			}
			catch (PayrollException e) {
				// TODO Auto-generated catch block
				/*e.printStackTrace();*/
				request.setAttribute("error", "Some Internal DataBase Error");
			}
			catch( SQLException e)
			{
				try{
					connObj.rollback();
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
					if(connObj!=null)
					{
						connObj.close();
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
