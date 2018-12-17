package com.payroll.services;

import java.sql.Connection;

import org.springframework.stereotype.Service;

import com.payroll.dao.LoginDAO;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.LoginDetails;

@Service
public class LoginService implements ILogin {

	public LoginService() {
		// TODO Auto-generated constructor stub
	}
	LoginDAO loginDao=null;
	@Override
	public int deleteLoginDetails(Connection connObj, LoginDetails login) throws PayrollException {
		// TODO Auto-generated method stub
		loginDao=new LoginDAO();
		return loginDao.deleteLoginDetails(connObj, login);
	}

	@Override
	public boolean loginCheck(LoginDetails loginDetails) throws PayrollException {
		// TODO Auto-generated method stub
		loginDao=new LoginDAO();
		return loginDao.loginCheck(loginDetails);
	}

}
