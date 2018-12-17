package com.payroll.services;

import java.sql.Connection;
import java.util.List;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;

public interface IEmployee {
	public String updateWholeEmployee(Employee employee, String[] skills) throws PayrollException;
	public String saveWholeEmployee(Employee employee,String[] skills) throws PayrollException;
	public int deleteEmployee(Connection connection,Employee employee) throws PayrollException;
	public List<Employee> fetchAllData(Connection connection,String name);
	public int registerEmployee(Connection connection,Employee employee) throws PayrollException;
	public int updateEmployee(Connection connObj,Employee employee) throws PayrollException;
	public Employee fetchEmployeeById(Connection connection,int empId) throws PayrollException;
}
