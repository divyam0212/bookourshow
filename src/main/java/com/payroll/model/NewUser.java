package com.payroll.model;

public class NewUser {

	private int userId;
	private String userName;
	private String emailId;
	private String password;
	
	public NewUser() {
		// TODO Auto-generated constructor stub
	}
	
	public NewUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public NewUser(String userName, String emailId, String password) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
	}

	public NewUser(int userId, String userName, String emailId, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "NewUser [userId=" + userId + ", userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + "]";
	}
	

}
