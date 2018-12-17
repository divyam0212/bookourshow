package com.payroll.services;

import java.sql.Connection;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;

public interface IEmployeeSkillSet {
	public int deleteSkillSet(Connection connObj,Employee employee) throws PayrollException;
	public int registerSkillSet(Connection connection,EmployeeSkillSet empSkill) throws PayrollException;
}
