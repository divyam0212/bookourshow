package com.payroll.services;

import java.sql.Connection;


import org.springframework.stereotype.Service;

import com.payroll.dao.AddressDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;
@Service
public class AddressService implements IAddress {

	AddressDAO addressDao=null;
	public AddressService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int deleteAddress(Address address) throws PayrollException {
		// TODO Auto-generated method stub
		addressDao=new AddressDAO();
		return addressDao.deleteAddress(address);
	}

	@Override
	public int registerAddress(Connection connection, Address address) throws PayrollException {
		// TODO Auto-generated method stub
		addressDao=new AddressDAO();
		return addressDao.registerAddress(connection, address);
	}

	@Override
	public int updateAddress(Connection connection, Address address) throws PayrollException {
		// TODO Auto-generated method stub
		addressDao=new AddressDAO();
		return addressDao.updateAddress(connection,address);
	}

}
