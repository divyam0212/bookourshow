package com.payroll.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.payroll.dao.EmployeeDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.services.EmployeeService;
import com.payroll.util.ConnectionUtil;


/**
 * Servlet implementation class ListEmployeeServlet
 */
@WebServlet("/ListEmployeeServlet")
public class ListEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 	EmployeeService dao=new EmployeeService();
		Connection connObj=null;
		PrintWriter out=response.getWriter();
		
		try{
			connObj=ConnectionUtil.getConnection();
			/*List<Employee> empList=dao.fetchAllData("null");*/
			
			/*System.out.println(empList);*/
			
			
			/*empList.forEach(out::println);*/
		}
		catch(PayrollException se)
		{
			se.printStackTrace();
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
		/*ServletConfig config=this.getServletConfig();
		String value=config.getInitParameter("url");
		String userName=config.getInitParameter("username");
		System.out.println(value+" "+userName);*/
	}
}
