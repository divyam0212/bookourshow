package com.payroll.services;

import java.sql.Connection;

import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;

public interface IAddress 
{
	public int deleteAddress(Address address) throws PayrollException;
	public int registerAddress(Connection connection,Address address) throws PayrollException;
	public int updateAddress(Connection connection,Address address) throws PayrollException;
}
