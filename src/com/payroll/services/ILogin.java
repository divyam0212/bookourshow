package com.payroll.services;

import java.sql.Connection;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.LoginDetails;

public interface ILogin 
{
	public int deleteLoginDetails(Connection connObj,LoginDetails login) throws PayrollException;
	public boolean loginCheck(LoginDetails loginDetails) throws PayrollException;
}
