package com.payroll.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Employee {

	private int empId;
	private String empName;
	private double empSalary;
	private Department department;
	private Address address;
	private List<Skills> skillsList;
	private String skillStr;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int empId, String empName, double empSalary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
	}
	public Employee(int empId, String empName, double empSalary, Department department, Address address,List<Skills> skillsList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.department = department;
		this.address = address;
		this.skillsList = skillsList;
	}
	
	public String getSkillStr() {
		return skillStr;
	}

	public void setSkillStr(String skillStr) {
		this.skillStr = skillStr;
	}

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	public Department getDepartment() {
		return department;
	}
	@Autowired
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Autowired
	public Address getAddress() {
		return address;
	}
	@Autowired
	public void setAddress(Address address) {
		this.address = address;
	}
	@Autowired
	public List<Skills> getSkillsList() {
		return skillsList;
	}
	@Autowired
	public void setSkillsList(List<Skills> skillsList) {
		this.skillsList = skillsList;
	}

	
	public String toString() {
		return "\nEmployee \n[empId=" + empId + ", \nempName=" + empName + ", \nempSalary=" + empSalary + ", \ndepartment="
				+ department + ", \naddress=" + address + ", \nskillsList=" + skillsList + ", \nskillStr=" +"{"+ skillStr +"}"+"]";
		
		/*return "\n" + empId + "\n" + empName + "\n" + empSalary + "\n"
		+ department + "\n" + address + "\n" + skillsList + "\n" + skillStr;*/
	}
	
	
	
}
