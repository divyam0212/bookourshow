/**
 * 
 */
package com.payroll.model;

/**
 * @author 736909
 *
 */
public class LoginDetails implements Comparable<LoginDetails>{

	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
	
	public LoginDetails() {
		// TODO Auto-generated constructor stub
	}

	public LoginDetails(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDetails [userName=" + userName + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDetails other = (LoginDetails) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public int compareTo(LoginDetails o) {
		// TODO Auto-generated method stub
		int flag=this.getUserName().compareTo(o.getUserName());
		return flag;
	}

}
