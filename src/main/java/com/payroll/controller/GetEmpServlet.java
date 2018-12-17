package com.payroll.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Department;
import com.payroll.model.Employee;
import com.payroll.model.Skills;
import com.payroll.services.DepartmentService;
import com.payroll.services.EmployeeService;
import com.payroll.services.SkillsService;

/**
 * Servlet implementation class GetEmpServlet
 */
@WebServlet("/GetEmpServlet")
public class GetEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String eid=request.getParameter("empId");
		int empId=Integer.parseInt(eid);
		
		EmployeeService employeeDAO=new EmployeeService();
		DepartmentService departmentDAO=new DepartmentService();
		SkillsService skillDAO=new SkillsService();
		Connection connection=null;
		try{
			Employee employee1=new Employee();
			employee1.setEmpId(empId);
			Employee employee=employeeDAO.fetchEmployeeById(connection,employee1.getEmpId());
			request.setAttribute("employee",employee);
			
			List<Department> departmentList=departmentDAO.fetchAllDepartment();
			request.setAttribute("departmentList", departmentList);
			
			List<Skills> skillsList=skillDAO.fetchAllSkill();
			request.setAttribute("skillsList", skillsList);
		}
		catch(PayrollException p)
		{
			p.printStackTrace();
			request.setAttribute("error", "some internal db error");
		}
		
		request.getRequestDispatcher("showemp.jsp").forward(request, response);
	}

}
