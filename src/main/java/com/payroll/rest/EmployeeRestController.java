package com.payroll.rest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.model.ResponseTransfer;
import com.payroll.services.EmployeeService;
import com.payroll.util.ConnectionUtil;

//@RequestMapping("/emp/data")
@RestController
public class EmployeeRestController {

	@Autowired(required=true)
	EmployeeService employeeService;

	/*@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}*/

	@GetMapping("/all")
	public List<Employee> getAllEmp() {

		Connection connection = null;
		List<Employee> list = null;
		try {
			connection = ConnectionUtil.getConnection();
			list = employeeService.fetchAllData(connection, "null");
		} catch (PayrollException | SQLException e) {
			list.add(new Employee(0, e.getMessage(), 0));
		}

		return list;
	}

	@GetMapping("/getemp/{empId}")
	public Employee getEmp(@PathVariable int empId) {
		Connection connection = null;
		Employee employee = new Employee();
		try {
			connection = ConnectionUtil.getConnection();
			employee = employeeService.fetchEmployeeById(connection, empId);
		} catch (PayrollException | SQLException e) {
			System.out.println("PAYROLL EXCEPTION");
		}

		return employee;
	}

	@PostMapping("/save/wholeemp")
	public ResponseTransfer postWholeEmp(@RequestBody Employee employee) {
		
		ResponseTransfer responseTransfer = new ResponseTransfer();
		String[] skills=employee.getSkillStr().split(",");
		String msg = "";
		int count = 0;
		try {
			msg = employeeService.saveWholeEmployee(employee, skills);
			if (count > 0) {
				msg += "success" + count;
			}
		} catch (PayrollException e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		responseTransfer.setMesssage(msg);
		return responseTransfer;
	}

	@PostMapping("/save/emp")
	public ResponseTransfer postEmp(@RequestBody Employee employee) {
		Connection connObj = null;
		ResponseTransfer responseTransfer = new ResponseTransfer();
		String msg = "";
		int count = 0;
		try {
			connObj = ConnectionUtil.getConnection();
			count = employeeService.registerEmployee(connObj, employee);
			if (count > 0) {
				msg = "success" + count;
			}
		} catch (PayrollException e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		responseTransfer.setMesssage(msg);
		return responseTransfer;
	}
	
	@PutMapping("/update/allemp")
	public String updateAllEmp(@RequestBody Employee employee){
		
		String msg = "";
		String[] skills=employee.getSkillStr().split(",");
		try {
			msg = employeeService.updateWholeEmployee(employee, skills);
		} 
		catch (PayrollException e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		return msg;
	}
	@DeleteMapping("/delete/{empId}")
	public String deleteEmp(@PathVariable int empId){
		String msg="";
		Employee employee=new Employee();
		employee.setEmpId(empId);
		Connection connection=null;
		
		try{
			connection=ConnectionUtil.getConnection();
			int count=employeeService.deleteEmployee(connection, employee);
			if(count>0){
				msg="Deleted Successfully";
			}
		}
		catch(PayrollException | SQLException s){
			System.out.println(s.getMessage());
		}
		finally{
			try{
				connection.close();
			}
			catch(SQLException s){
				System.out.println(s.getMessage());
			}
		}
		return msg;
	}
	/*@PutMapping("/update/emp")
	public String putEmp(@RequestBody Employee employee) {
		Connection connObj = null;
		String msg = "";
		int count = 0;
		try 
		{
			connObj = ConnectionUtil.getConnection();
			count = employeeService.updateEmployee(connObj, employee);
			if (count > 0) {

				msg = "success";
			}
		} catch (PayrollException e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		return msg;

	}*/
}
