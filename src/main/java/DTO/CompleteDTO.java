package DTO;

import java.util.Date;

public class CompleteDTO {

	private Long complete_id;
	private String user_id;
	private int board_id;
	private Date issue_date;
	private String pass_fail;
	private String pdf_url;
	
	public CompleteDTO() {}
	
	
	public CompleteDTO(Long complete_id, String user_id, int board_id, Date issue_date, String pass_fail) {
		
		this.complete_id = complete_id;
		this.user_id = user_id;
		this.board_id = board_id;
		this.issue_date = issue_date;
		this.pass_fail = pass_fail;
		
	}
	
	public CompleteDTO(String user_id, int board_id, Date issue_date, String pass_fail) {
		
		this.user_id = user_id;
		this.board_id = board_id;
		this.issue_date = issue_date;
		this.pass_fail = pass_fail;
		
	}
	
	

	public String getPdf_url() {
		return pdf_url;
	}


	public void setPdf_url(String pdf_url) {
		this.pdf_url = pdf_url;
	}


	public Long getComplete_id() {
		return complete_id;
	}


	public void setComplete_id(Long complete_id) {
		this.complete_id = complete_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public int getBoard_id() {
		return board_id;
	}


	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	public Date getIssue_date() {
		return issue_date;
	}


	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}


	public String getPass_fail() {
		return pass_fail;
	}


	public void setPass_fail(String pass_fail) {
		this.pass_fail = pass_fail;
	}


	@Override
	public String toString() {
		return "CompleteDTO [complete_id=" + complete_id + ", user_id=" + user_id + ", board_id=" + board_id
				+ ", issue_date=" + issue_date + ", pass_fail=" + pass_fail + "]";
	}
	
	
}
