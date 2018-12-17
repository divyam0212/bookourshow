package com.payroll.services;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.dao.EmployeeDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;

@Service

public class EmployeeService implements IEmployee {
	EmployeeDAO employeeDao=null;
	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String saveWholeEmployee(Employee employee,String[] skills) throws PayrollException{
		employeeDao=new EmployeeDAO();
		return employeeDao.saveWholeEmployee(employee, skills);
	}
	@Override
	public int deleteEmployee(Connection connection,Employee employee) throws PayrollException {
		// TODO Auto-generated method stub
		employeeDao=new EmployeeDAO();
		return employeeDao.deleteEmployee(connection,employee);
	}

	@Override
	public List<Employee> fetchAllData(Connection connection,String name) {
		// TODO Auto-generated method stub
		employeeDao=new EmployeeDAO();
		List<Employee> employee=employeeDao.fetchAllData(connection,"null");
		return employee;
	}

	@Override
	public int registerEmployee(Connection connection,Employee employee) throws PayrollException {
		// TODO Auto-generated method stub
		employeeDao=new EmployeeDAO();
		int empId=employeeDao.registerEmployee(connection,employee);
		return empId;
	}

	@Override
	public int updateEmployee(Connection connObj, Employee employee) throws PayrollException {
		// TODO Auto-generated method stub
		employeeDao=new EmployeeDAO();
		int count=employeeDao.updateEmployee(connObj, employee);
		return count;
	}

	@Override
	public Employee fetchEmployeeById(Connection connection,int empId) throws PayrollException {
		// TODO Auto-generated method stub
		employeeDao=new EmployeeDAO();
		Employee employee=employeeDao.fetchEmployeeById(connection,empId);
		return employee;
	}

	@Override
	public String updateWholeEmployee(Employee employee, String[] skills) throws PayrollException {
		// TODO Auto-generated method stub
		employeeDao=new EmployeeDAO();
		return employeeDao.updateWholeEmployee(employee, skills);
	}

}
