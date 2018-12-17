package com.payroll.app;

import java.util.List;

import com.payroll.dao.EmployeeDAO;
import com.payroll.model.Employee;

public class ListEmployees {

	public ListEmployees() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeDAO empDao=new EmployeeDAO();
		
		/*	List<Employee> emp=empDao.fetchAllData("raju");
			emp.forEach(System.out::println);
		*/
		
	}

}
