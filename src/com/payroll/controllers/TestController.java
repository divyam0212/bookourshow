package com.payroll.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;
import com.payroll.model.LoginDetails;
import com.payroll.services.AddressService;
import com.payroll.services.DepartmentService;
import com.payroll.services.EmployeeService;
import com.payroll.services.EmployeeSkillSetService;
import com.payroll.services.LoginService;
import com.payroll.services.SkillsService;
import com.payroll.util.ConnectionUtil;

@ComponentScan({"com.payroll"})
@SessionAttributes("loginDetails")
@Controller
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	}


	private EmployeeService employeeService;
	private EmployeeSkillSetService employeeSkillSetService;
	private DepartmentService departmentService;
	private SkillsService skillsService;
	private AddressService addressService;
	private LoginService loginService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@Autowired
	public void setEmployeeSkillSetService(EmployeeSkillSetService employeeSkillSetService) {
		this.employeeSkillSetService = employeeSkillSetService;
	}
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@Autowired
	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}
	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/home")
	public String home(HttpServletRequest request) { // any function name can be
														// provided like home()

		System.out.println("Home");
		request.getSession(true).invalidate();
		String path = "login";

		if (request.getSession().getAttribute("loginDetails") == null) {

		} else {
			path = "success";
		}

		return path; /* login is a jsp page */
	}

	@PostMapping("/LoginController")
	public ModelAndView login(LoginDetails loginDetails) {

		System.out.println(loginDetails);
		String view = "success";
		ModelAndView mav = null;
		Connection connection = null;
		try {
			boolean ch = loginService.loginCheck(loginDetails);
			if (ch) {
				connection = ConnectionUtil.getConnection();
				mav = new ModelAndView("success");
				List<Employee> employee = employeeService.fetchAllData(connection, "null");
				mav.addObject("list", employee);
				//DepartmentService departmentService=new DepartmentService();
				mav.addObject("departmentList", departmentService.fetchAllDepartment());
				//SkillsService skillsService=new SkillsService();
				mav.addObject("skillList", skillsService.fetchAllSkill());
			} else {
				view = "login";
				mav = new ModelAndView(view);
				mav.addObject("error", "UserName or Password is Wrong");
			}
		} catch (PayrollException | SQLException e) {
			view = "login";
			mav = new ModelAndView(view);
			mav.addObject("error", e.getMessage());
			e.printStackTrace();
		}

		return mav;
	}

	@GetMapping("/register")
	public String register(@ModelAttribute Employee employee) { // any function
																// name can be
																// provided like
																// home()
		return "registration"; /* login is a jsp page */
	}

	@GetMapping("/success")
	public String success() {

		return "success";
	}

	@PostMapping("/RegistrationController")
	public ModelAndView registration(Employee employee, String[] skills, ModelAndView modelAndView) {

		System.out.println(employee + "\n"/* +address+"\n"+department */);
		//EmployeeService employeeDao = new EmployeeService();
		String message = "";
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			message =employeeService.saveWholeEmployee(employee, skills);
			modelAndView.setViewName("success");
			modelAndView.addObject("message", message);
			modelAndView.addObject("list", employeeService.fetchAllData(connection, "null"));
		} catch (PayrollException e) {
			e.printStackTrace();
		} catch (SQLException sql) {
			try {
				connection.rollback();
			} catch (SQLException sql1) {
				sql.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException s) {
				System.out.println("Error in closing file");
			}
		}
		return modelAndView;
	}

	@PostMapping("/UpdateController")
	public ModelAndView update(ModelAndView modelAndView,Employee employee,String[] skills) {
		String message = "";
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			message = employeeService.updateWholeEmployee(employee, skills);

			modelAndView.setViewName("success");
			modelAndView.addObject("message", message);
			modelAndView.addObject("list", employeeService.fetchAllData(connection, "null"));

			connection.commit();
		} catch (PayrollException payExcep) {
			modelAndView.addObject("message", "EXCEPTION");
		} catch (SQLException sql) {
			try {
				connection.rollback();
			} catch (SQLException sql1) {
				sql1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException sql1) {
				sql1.printStackTrace();
			}
		}
		return modelAndView;
	}

	@GetMapping("/deletemp")
	public ModelAndView deletemp(@RequestParam int empId, ModelAndView modelAndView) 
	{
		Connection connection = null;
		String message="";
		try {
			connection = ConnectionUtil.getConnection();
			connection.setAutoCommit(false);

			Employee employee=new Employee();
			employee.setEmpId(empId);
			
			//EmployeeSkillSetService skillSetService=new EmployeeSkillSetService();
			employeeSkillSetService.deleteSkillSet(connection, employee);
			
			employeeService.deleteEmployee(connection, employee);
			
			modelAndView.setViewName("success");
			modelAndView.addObject("message", "Deleted Successfully");
			modelAndView.addObject("list", employeeService.fetchAllData(connection, "null"));

			connection.commit();

		} catch (PayrollException payExcep) {
			payExcep.printStackTrace();
		} catch (SQLException sql) {
			try {
				connection.rollback();
			} catch (SQLException sql1) {
				sql1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException sql1) {
				sql1.printStackTrace();
			}
		}
		return modelAndView;

		
	}
	@GetMapping("/getemp")
	public ModelAndView getemp(@RequestParam int empId, ModelAndView modelAndView) {

		// String message = "error";
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			connection.setAutoCommit(false);

			Employee employee = employeeService.fetchEmployeeById(connection, empId);
			System.out.println("CHECK!!" + employee);
			modelAndView.setViewName("success");

			modelAndView.addObject("employee", employee);
			modelAndView.addObject("departmentList", departmentService.fetchAllDepartment());
			modelAndView.addObject("skillList", skillsService.fetchAllSkill());

			connection.commit();

		} catch (PayrollException payExcep) {
			payExcep.printStackTrace();
		} catch (SQLException sql) {
			try {
				connection.rollback();
			} catch (SQLException sql1) {
				sql1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException sql1) {
				sql1.printStackTrace();
			}
		}
		return modelAndView;

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();

		return "login";

	}
}
