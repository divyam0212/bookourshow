package com.payroll.services;

import java.sql.Connection;
import java.util.List;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Department;

public interface IDepartment {
	public int deleteDepartment(Connection connObj,Department department) throws PayrollException;
	public int registerDepartment(Connection connection,Department department) throws PayrollException;
	public int updateDepartment(Connection connection,Department department) throws PayrollException;
	public List<Department> fetchAllDepartment();
}
