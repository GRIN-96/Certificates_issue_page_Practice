package DTO;

import java.util.Date;

public class CompleteDTO {

	private Long complete_id;
	private String user_id;
	private int board_id;
	private Date content;
	private String pass_fail;
	
	public CompleteDTO() {}
	
	
	public CompleteDTO(Long complete_id, String user_id, int board_id, Date content, String pass_fail) {
		
		this.complete_id = complete_id;
		this.user_id = user_id;
		this.board_id = board_id;
		this.content = content;
		this.pass_fail = pass_fail;
		
	}
	
	public CompleteDTO(String user_id, int board_id, Date content, String pass_fail) {
		
		this.user_id = user_id;
		this.board_id = board_id;
		this.content = content;
		this.pass_fail = pass_fail;
		
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


	public Date getContent() {
		return content;
	}


	public void setContent(Date content) {
		this.content = content;
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
				+ ", content=" + content + ", pass_fail=" + pass_fail + "]";
	}
	
	
}
