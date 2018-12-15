package com.payroll.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.LoginDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.model.LoginDetails;
import com.payroll.model.LoginDetailsValidate;
import com.payroll.services.EmployeeService;
import com.payroll.util.ConnectionUtil;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Login Servlet for Login Check", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("LoginServlet Initialization");
	}
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy LoginServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post of LoginServlet");
		String username=request.getParameter("userName");
		String password=request.getParameter("password");
		//PrintWriter out=response.getWriter();			//prints on ui
		
		String page="login.jsp";
		
		Connection connObj=null;
		try
		{
			connObj	=ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			
			LoginDetails login=new LoginDetails(username,password);
			LoginDAO dao=new LoginDAO();
			/*boolean check=dao.loginCheck(connObj,login);*/
			LoginDetailsValidate validate=new LoginDetailsValidate();
			boolean checkLogin=validate.validateLoginDetails(login);
			if(checkLogin){
				boolean check=dao.loginCheck(login);
				if(check)
				{
				//out.println("Welcome to Servlets "+username);
				HttpSession session=request.getSession();	// creates a session
				session.setAttribute("user", login);     // sets username and password to session if we getattr and it is true then user is still logged in
				System.out.println("Logged In "+"Home Page Displayed");
				
				page="success.jsp";
				
				EmployeeService empDao=new EmployeeService();
				List<Employee> employees=empDao.fetchAllData(connObj,"null");
				request.setAttribute("list", employees);
				}
				else
				{
				//out.println("Cannot Login"+"Error Page");
				System.out.println("Cannot Login"+"Error Page");
				//RequestDispatcher rd=request.getRequestDispatcher("login2.html");
				page="login.jsp";
				request.setAttribute("error","UserName or Password is Wrong");
				
				}
				RequestDispatcher rd=request.getRequestDispatcher(page);
			
				rd.forward(request, response);
			}
			
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
		
		/*RequestDispatcher rd=request.getRequestDispatcher(page);
		
		rd.forward(request, response);*/
		
		
	}

}

