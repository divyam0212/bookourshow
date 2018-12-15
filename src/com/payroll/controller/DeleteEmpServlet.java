package com.payroll.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.payroll.dao.EmployeeSkillSetDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.services.EmployeeSkillSetService;
import com.payroll.util.ConnectionUtil;

/**
 * Servlet implementation class DeleteEmpServlet
 */
@WebServlet("/DeleteEmpServlet")
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eId=request.getParameter("empId");
		int empId=Integer.parseInt(eId);
		
		EmployeeSkillSetService skillSetDAO=new EmployeeSkillSetService();
		Connection connObj=null;
		try{
			connObj=ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			Employee employee=new Employee();
			employee.setEmpId(empId);
			skillSetDAO.deleteSkillSet(connObj, employee);
			connObj.commit();
			request.getRequestDispatcher("success.jsp").forward(request, response);
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
