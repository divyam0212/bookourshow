package com.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.Statement;
import com.payroll.exceptions.PayrollException;
import com.payroll.model.Address;
import com.payroll.model.Department;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeSkillSet;
import com.payroll.model.Skills;
import com.payroll.services.AddressService;
import com.payroll.services.EmployeeService;
import com.payroll.services.EmployeeSkillSetService;
import com.payroll.util.ConnectionUtil;

public class EmployeeDAO {

	public EmployeeDAO() {
		// TODO Auto-generated constructor stub
	}

	public String updateWholeEmployee(Employee employee, String[] skills) throws PayrollException {
		Connection connObj = null;
		String message="";
		
		try {
			connObj = ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			
			EmployeeService employeeService = new EmployeeService();
			employeeService.updateEmployee(connObj, employee);

			EmployeeSkillSetService dao = new EmployeeSkillSetService();
			dao.deleteSkillSet(connObj, employee);

			for (String skillId : skills) {
				Skills skill = new Skills();
				skill.setSkillId(Integer.parseInt(skillId));

				EmployeeSkillSet skillSet = new EmployeeSkillSet();
				skillSet.setSkills(skill);
				skillSet.setEmployee(employee);

				int sid = dao.registerSkillSet(connObj, skillSet);

				skillSet.setEmpSkillSetId(sid);
			}
			message="UPDATED SUCCESSFULLY";
			connObj.commit();
		} catch (PayrollException e) {
			e.printStackTrace();
		} catch (SQLException sql) {
			try {
				connObj.rollback();
			} catch (SQLException sql1) {
				sql.printStackTrace();
			}
		} finally {
			try {
				connObj.close();
			} catch (SQLException s) {
				System.out.println("Error in closing file");
			}
		}
		return message;
	}

	public String saveWholeEmployee(Employee employee, String[] skills) throws PayrollException {
		Connection connection = null;
		String message = "";
		try {
			connection = ConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			/*
			  int addressId=addressService.registerAddress(connection,
			  address); address.setAddressId(addressId);
			  employee.setAddress(address); employee.setDepartment(department);
			 */
			AddressService addressDao = new AddressService();
			int addressId = addressDao.registerAddress(connection, employee.getAddress());
			employee.getAddress().setAddressId(addressId);

			EmployeeService employeeService = new EmployeeService();
			int empId = employeeService.registerEmployee(connection, employee);
			employee.setEmpId(empId);
			EmployeeSkillSet empSkillSet = new EmployeeSkillSet();

			for (String skill : skills) {
				int skillId = Integer.parseInt(skill);
				empSkillSet.setEmpSkillSetId(skillId);

				Skills skill1 = new Skills();
				skill1.setSkillId(skillId);

				empSkillSet.setSkills(skill1);
				empSkillSet.setEmployee(employee);

				EmployeeSkillSetService skillSetDao = new EmployeeSkillSetService();
				int skillSetId = skillSetDao.registerSkillSet(connection, empSkillSet);
				empSkillSet.setEmpSkillSetId(skillSetId);
			}
			System.out.println(empSkillSet);
			System.out.println(employee);
			connection.commit();
			if (empId != 0) {
				message = "You are empid is " + empId;
				System.out.print("You are empid is " + empId);
				System.out.println("Please remember for future ");

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new PayrollException("SQL EXCEPTION");
				// e1.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new PayrollException("SQL EXCEPTION");
				// System.out.println("Error While Closing Connection");
			}
		}
		return message;
	}

	public int deleteEmployee(Connection connObj, Employee employee) throws PayrollException {
		String query = "delete from employee where emp_id=?;";

		// Connection connObj=null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			// connObj=ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, employee.getEmpId());
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new PayrollException("SQL EXCEPTION");
		} finally {
			try {
				/*
				 * if (result != null) { result.close(); }
				 */
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw new PayrollException("SQL EXCEPTION");
				// System.out.println("Error While Closing Connection");
			}
		}
		return count;
	}

	public List<Employee> fetchAllData(Connection connection, String name) {
		List<Employee> employees = new ArrayList<>();
		String query = "select e.*,ed.*,d.*,s.*,group_concat(s.skill_name) as skill "
				+ "from employee e inner join emp_address ed " + "inner join department d " + "inner join skills s "
				+ "inner join emp_skillset sk " + "on ed.address_id=e.fk_address_id " + "and e.emp_id=sk.fk_emp_id "
				+ "and e.fk_dept_id=d.dept_id " + "and s.skill_id=sk.fk_skill_id " + "group by e.emp_id;";

		String query1 = "select e.*,ed.*,d.*,s.*,group_concat(s.skill_name) as skill "
				+ "from employee e inner join emp_address ed " + "inner join department d " + "inner join skills s "
				+ "inner join emp_skillset sk " + "on ed.address_id=e.fk_address_id " + "and e.emp_id=sk.fk_emp_id "
				+ "and e.fk_dept_id=d.dept_id " + "and s.skill_id=sk.fk_skill_id " + "where e.emp_name like ? "
				+ "group by e.emp_id;";

		PreparedStatement pstmt = null;
		ResultSet result = null;
		// Connection connection=null;
		try {
			// connection=ConnectionUtil.getConnection();

			if (name.equals("null")) {
				pstmt = connection.prepareStatement(query);
			} else {

				pstmt = connection.prepareStatement(query1);
				pstmt.setString(1, "%" + name + "%");
				System.out.println(query1);
			}
			result = pstmt.executeQuery();
			while (result.next()) {
				int eId = result.getInt("emp_id");
				String eName = result.getString("emp_name");
				double salary = result.getDouble("emp_salary");

				int aId = result.getInt("address_id");
				String street = result.getString("street");
				String city = result.getString("city");
				String state = result.getString("state");
				String country = result.getString("country");
				Address address = new Address(aId, street, city, state, country);

				int deptId = result.getInt("dept_id");
				String deptName = result.getString("dept_name");
				String deptLoca = result.getString("dept_location");
				Department dept = new Department(deptId, deptName, deptLoca);

				/*
				 * int skillId=result.getInt("skill_id"); String
				 * skillName=result.getString("skill_name");
				 */

				String skill = result.getString("skill");

				Employee employee = new Employee(eId, eName, salary);
				employee.setAddress(address);
				employee.setDepartment(dept);
				employee.setSkillStr(skill);

				employees.add(employee);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				/*
				 * if(connection!=null) { connection.close(); }
				 */
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}

		}
		System.out.println(employees);
		return employees;
	}

	public int registerEmployee(Connection connObj, Employee employee) throws PayrollException// SQLException
	{
		int generatedId = 0;
		// Connection connObj=null;
		PreparedStatement pstmt = null;
		String query = "insert into employee(emp_name,emp_salary,fk_address_id,fk_dept_id) values(?,?,?,?)";
		ResultSet result = null;
		try {
			// connObj=ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			// use PrepareStatement because where clause is used and is used
			// each time

			pstmt = connObj.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, employee.getEmpName()); // getting username and
														// password from
														// logindetails
														// parameter
			pstmt.setDouble(2, employee.getEmpSalary());
			pstmt.setInt(3, employee.getAddress().getAddressId());
			pstmt.setInt(4, employee.getDepartment().getDepartmentId());
			// System.out.println(100/0); to check whether

			pstmt.executeUpdate();
			result = pstmt.getGeneratedKeys();
			if (result.next()) {
				generatedId = result.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			try {
				connObj.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new PayrollException("SQL EXCEPTION");
				// e1.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				/*
				 * if (connObj != null) { connObj.close(); }
				 */
			} catch (SQLException e) {
				throw new PayrollException("SQL EXCEPTION");
				// System.out.println("Error While Closing Connection");
			}
		}
		return generatedId;
	}

	public int updateEmployee(Connection connObj, Employee employee) throws PayrollException// SQLException
	{
		int count = 0;
		// Connection connObj=null;
		PreparedStatement pstmt = null;
		String query = "update employee set emp_name=?,emp_salary=?,fk_dept_id=? where emp_id=?";
		ResultSet result = null;
		try {
			// connObj=ConnectionUtil.getConnection();
			// connObj.setAutoCommit(false);
			// use PrepareStatement because where clause is used and is used
			// each time

			pstmt = connObj.prepareStatement(query);
			pstmt.setString(1, employee.getEmpName()); // getting username and
														// password from
														// logindetails
														// parameter
			pstmt.setDouble(2, employee.getEmpSalary());
			pstmt.setInt(3, employee.getDepartment().getDepartmentId());
			pstmt.setInt(4, employee.getEmpId());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			try {
				connObj.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new PayrollException("SQL EXCEPTION");
				// e1.printStackTrace();
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				/*
				 * if (connObj != null) { connObj.close(); }
				 */
			} catch (SQLException e) {
				throw new PayrollException("SQL EXCEPTION");
				// System.out.println("Error While Closing Connection");
			}
		}
		return count;
	}

	public Employee fetchEmployeeById(Connection connection, int empId) throws PayrollException {
		// TODO Auto-generated method stub
		String query = "select e.*,ed.*,d.*,s.* " + "from employee e inner join emp_address ed "
				+ "inner join department d " + "inner join skills s " + "inner join emp_skillset sk "
				+ "on ed.address_id=e.fk_address_id " + "and e.emp_id=sk.fk_emp_id " + "and e.fk_dept_id=d.dept_id "
				+ "and s.skill_id=sk.fk_skill_id " + "where e.emp_id=? ";

		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		// Connection connection=null;
		try {

			// connection=ConnectionUtil.getConnection();

			if (empId == 0) {
				throw new PayrollException("EmpID is Zero");
			} else {
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, empId);
			}
			result = pstmt.executeQuery();
			List<Skills> listSkills = new ArrayList<>();
			String skillStr = "";
			while (result.next()) {
				int eId = result.getInt("emp_id");
				String eName = result.getString("emp_name");
				double salary = result.getDouble("emp_salary");

				int aId = result.getInt("address_id");
				String street = result.getString("street");
				String city = result.getString("city");
				String state = result.getString("state");
				String country = result.getString("country");
				Address address = new Address(aId, street, city, state, country);

				int deptId = result.getInt("dept_id");
				String deptName = result.getString("dept_name");
				String deptLoca = result.getString("dept_location");
				Department dept = new Department(deptId, deptName, deptLoca);

				int skillId = result.getInt("skill_id");
				String skillName = result.getString("skill_name");
				skillStr += skillName;
				Skills skill = new Skills(skillId, skillName);

				listSkills.add(skill);

				emp = new Employee(eId, eName, salary);
				emp.setAddress(address);
				emp.setDepartment(dept);

			}
			emp.setSkillsList(listSkills);
			emp.setSkillStr(skillStr);
		} catch (SQLException se) {
			se.printStackTrace();
			throw new PayrollException("empDAO error");
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				/*
				 * if(connection!=null) { connection.close(); }
				 */
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}

		}
		System.out.println(emp);
		return emp;
	}
}
