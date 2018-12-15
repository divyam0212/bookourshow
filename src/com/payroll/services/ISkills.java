package com.payroll.services;

import java.sql.Connection;
import java.util.List;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Skills;

public interface ISkills {
	public int deleteSkills(Connection connObj,Skills skills) throws PayrollException;
	public int registerSkill(Connection connection,Skills skills) throws PayrollException;
	public int updateSkill(Connection connection,Skills skills) throws PayrollException;
	public List<Skills> fetchAllSkill() throws PayrollException;
}
