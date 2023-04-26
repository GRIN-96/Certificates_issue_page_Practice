package DTO;

import java.util.Date;

public class CertificatesDTO {
	
	private int complete_id;
	private String agency;
	private String education;
	private String user_name;
	private Date issue_date;
	private String content;
	private String position;
	private String issurer;
	private String pass_fail;
	private Date user_birthday;
	private String pdf_url;
	
	// 생성자함수 
	public CertificatesDTO() {}
	
	// db에서 join data를 가져오기위한 생성자함수
	public CertificatesDTO(int complete_id, String agency, String education, String user_name, Date issue_date,String content, String position,  String issurer, String pass_fail, Date user_birthday, String pdf_url) {
		
		this.complete_id = complete_id;
		this.agency = agency;
		this.education = education;
		this.user_name = user_name;
		this.issue_date = issue_date;
		this.content = content;
		this.position = position;
		this.issurer = issurer;
		this.pass_fail = pass_fail;
		this.user_birthday = user_birthday;
		this.pdf_url = pdf_url;
		
	}

	
	
	public String getPdf_url() {
		return pdf_url;
	}

	public void setPdf_url(String pdf_url) {
		this.pdf_url = pdf_url;
	}

	public Date getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}

	public int getComplete_id() {
		return complete_id;
	}

	public void setComplete_id(int complete_id) {
		this.complete_id = complete_id;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIssurer() {
		return issurer;
	}

	public void setIssurer(String issurer) {
		this.issurer = issurer;
	}

	public String getPass_fail() {
		return pass_fail;
	}

	public void setPass_fail(String pass_fail) {
		this.pass_fail = pass_fail;
	}

	@Override
	public String toString() {
		return "CertificatesDTO [complete_id=" + complete_id + ", agency=" + agency + ", education=" + education
				+ ", user_name=" + user_name + ", issue_date=" + issue_date + ", content=" + content + ", position="
				+ position + ", issurer=" + issurer + ", pass_fail=" + pass_fail + ", user_birthday=" + user_birthday
				+ ", pdf_url=" + pdf_url + "]";
	}



	
	
	


	
}
