package com.payroll.model;

import com.payroll.exceptions.PayrollException;

public class LoginDetailsValidate {

	public LoginDetailsValidate() {
		// TODO Auto-generated constructor stub
	}
	public boolean validateLoginDetails(LoginDetails details) throws PayrollException{
		boolean flag=false;
		if (details.getUserName() == "" || details.getPassword() == "") {
			throw new PayrollException("UserName and Password must be Filled");
		}
		else
		{
			flag=true;
		}
		/*String regex = "/^[0-4]{4}$/";
		String regex2 = "/^[0-4]{4}[a-z]{4}$/";
		var regex2= /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;  

		if (details.getUserName().matches(regex)) 
		{
		} 
		else 
		{
			throw new PayrollException("Username is wrong digits"); 
		}
		if (details.getUserName().matches(regex) && details.getPassword().matches(regex2)) 
		{
			flag=true;
		} else 
		{
			throw new PayrollException("Username and Password no tmeeting required conditions");
		}*/
		return flag;
	}
}
