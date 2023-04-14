package DTO;

public class BoardDTO {
	
	private Long board_id;
	private String agency;
	private String education;
	private String content;
	private String position;
	private String issurer;
	
	// 생성자함수 
	public BoardDTO() {}
	
	// db insert를 위한 생성자함수
	public BoardDTO(Long board_id, String agency, String education, String content, String position, String issurer) {
		this.board_id = board_id;
		this.agency = agency;
		this.education = education;
		this.content = content;
		this.position = position;
		this.issurer = issurer;
	}
	
	// db insert를 위한 생성자함수
	public BoardDTO(String agency, String education, String content, String position, String issurer) {
		this.agency = agency;
		this.education = education;
		this.content = content;
		this.position = position;
		this.issurer = issurer;
	}

	public Long getBoard_id() {
		return board_id;
	}

	public void setBoard_id(Long board_id) {
		this.board_id = board_id;
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

	@Override
	public String toString() {
		return "BoardDTO [board_id=" + board_id + ", agency=" + agency + ", education=" + education + ", content="
				+ content + ", position=" + position + ", issurer=" + issurer + "]";
	}
	
	
	
}
