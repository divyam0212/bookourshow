package com.payroll.services;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.dao.DepartmentDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Department;

@Service
public class DepartmentService implements IDepartment {

	DepartmentDAO deptDao=new DepartmentDAO();
	public DepartmentService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int deleteDepartment(Connection connObj, Department department) throws PayrollException {
		// TODO Auto-generated method stub
		deptDao=new DepartmentDAO();
		return deptDao.deleteDepartment(connObj, department);
	}

	@Override
	public int registerDepartment(Connection connection, Department department) throws PayrollException {
		// TODO Auto-generated method stub
		deptDao=new DepartmentDAO();
		return deptDao.registerDepartment(connection, department);
	}

	@Override
	public int updateDepartment(Connection connection, Department department) throws PayrollException {
		// TODO Auto-generated method stub
		deptDao=new DepartmentDAO();
		return deptDao.updateDepartment(connection, department);
	}

	@Override
	public List<Department> fetchAllDepartment() {
		// TODO Auto-generated method stub
		deptDao=new DepartmentDAO();
		return deptDao.fetchAllDepartment();
	}

}
