package com.payroll.services;

import java.sql.Connection;

import org.springframework.stereotype.Service;

import com.payroll.dao.EmployeeSkillSetDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;

@Service
public class EmployeeSkillSetService implements IEmployeeSkillSet {

	EmployeeSkillSetDAO empSkillSetDao=null;
	public EmployeeSkillSetService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int deleteSkillSet(Connection connObj,Employee employee) throws PayrollException {
		// TODO Auto-generated method stub
		empSkillSetDao=new EmployeeSkillSetDAO();
		return empSkillSetDao.deleteSkillSet(connObj, employee);
	}

	@Override
	public int registerSkillSet(Connection connection, EmployeeSkillSet empSkill) throws PayrollException {
		// TODO Auto-generated method stub
		System.out.println("the employee id is "+empSkill);
		empSkillSetDao=new EmployeeSkillSetDAO();
		return empSkillSetDao.registerSkillSet(connection, empSkill);
	}

}
