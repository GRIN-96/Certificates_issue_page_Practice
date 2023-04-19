package DTO;

import java.util.Date;

public class UserListDTO {
	
	private int complete_id;
	private String user_id;
	private String user_name;
	private String pass_fail;
	private Date issue_date;
	
	// 생성자함수 
	public UserListDTO() {}
	
	// db에서 join data를 가져오기위한 생성자함수
	public UserListDTO(int complete_id, String user_id, String user_name, String pass_fail, Date issue_date) {
		
		this.complete_id = complete_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.pass_fail = pass_fail;
		this.issue_date = issue_date;
	}

	public int getComplete_id() {
		return complete_id;
	}

	public void setComplete_id(int complete_id) {
		this.complete_id = complete_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_fail() {
		return pass_fail;
	}

	public void setPass_fail(String pass_fail) {
		this.pass_fail = pass_fail;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	@Override
	public String toString() {
		return "UserListDTO [complete_id=" + complete_id + ", user_id=" + user_id + ", user_name=" + user_name
				+ ", pass_fail=" + pass_fail + ", issue_date=" + issue_date + "]";
	}


	
}
