package DTO;

import java.sql.Timestamp;
import java.util.Date;

public class UserDTO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private Date user_birthday;
	private String user_gender;
	private String user_email;
	private Timestamp user_joindate;
	
	
	public UserDTO() {}
	
	
	// 생성자 함수
	public UserDTO(String user_id, String user_pw, String user_name, java.sql.Date b_day, String user_gender, String user_email, Timestamp user_joindate) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_birthday = b_day;
		this.user_gender = user_gender;
		this.user_joindate = user_joindate;
	}
	
	// insert DB를 위한 생성자함수
	public UserDTO(String user_id, String user_pw, String user_name, Date b_day, String user_gender, String user_email) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_birthday = b_day;
		this.user_gender = user_gender;
		this.user_email = user_email;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Timestamp getUser_joindate() {
		return user_joindate;
	}

	public void setUser_joindate(Timestamp user_joindate) {
		this.user_joindate = user_joindate;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_birthday="
				+ user_birthday + ", user_gender=" + user_gender + ", user_email=" + user_email + ", user_joindate="
				+ user_joindate + "]";
	}
	
	
	
}
