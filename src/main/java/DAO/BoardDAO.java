package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import DTO.BoardDTO;
import DTO.UserDTO;

public class BoardDAO {
	
	private BoardDTO boardDTO;
	// sql 연결을 위한 호출 
	Connection con = null;  // Connection :: DB와 JAVA를 연결시켜주는 역할을 하는 객체입니다.
	PreparedStatement pstmt = null; // PreparedStatement :: SQL 쿼리를 작성하는데 도움을주는 객체 입니다.
	ResultSet rs = null;  // 결과값 담는 객체입니다.
	String dbURL = "jdbc:mariadb://localhost:3306/pdf";
	String dbID = "root";
	String dbPassword = "root";
	HttpSession session;
	
	public BoardDAO(){}
		
	public ArrayList<BoardDTO> boardDatas() throws SQLException, ClassNotFoundException {
		
		ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "SELECT * FROM board ORDER BY board_id DESC";
		
		pstmt = con.prepareStatement(SQL); 
		
		rs = pstmt.executeQuery(); // 모든 정보 담기
		
		while (rs.next()) {
			
			BoardDTO boardDTO = new BoardDTO(
					rs.getLong("board_id"),
					rs.getString("agency"),
					rs.getString("education"),
					rs.getString("content"),
					rs.getString("position"),
					rs.getString("issurer"));
			
			lists.add(boardDTO);  // 객체 생성 후 리스트추가
		}
		
		return lists;
	}
	
	// board insert 
	public boolean boardInsert(BoardDTO boardDTO) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "INSERT INTO BOARD(agency, education, content, position, issurer) VALUES (?, ?, ?, ?, ?)";
		
		pstmt = con.prepareStatement(SQL); 
		pstmt.setString(1, boardDTO.getAgency());
		pstmt.setString(2, boardDTO.getEducation());
		pstmt.setString(3, boardDTO.getContent());
		pstmt.setString(4, boardDTO.getPosition());
		pstmt.setString(5, boardDTO.getIssurer());
	
		// 쿼리 실행
		// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
		int result = pstmt.executeUpdate(); 
		if (result == 1) {
			System.out.println("추가가 완료되었습니다.");
			return true;
		}
		return false;
		
	}
	
	// board 상세페이지
	public BoardDTO detailBoard(String agency, String education) throws ClassNotFoundException, SQLException {
		
		BoardDTO boardDTO = new BoardDTO();
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "SELECT * FROM BOARD WHERE agency = ? AND education = ? ";
		
		pstmt = con.prepareStatement(SQL); 
		pstmt.setString(1, agency);
		pstmt.setString(2, education);
		
		rs = pstmt.executeQuery(); // 모든 정보 담기
		
		while (rs.next()) {
			
			boardDTO = new BoardDTO(
					rs.getLong("board_id"),
					rs.getString("agency"),
					rs.getString("education"),
					rs.getString("content"),
					rs.getString("position"),
					rs.getString("issurer"));
			
		}
		
		return boardDTO;
		
	}
	
	// board update 
	public boolean boardUpdate(BoardDTO boardDTO) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "UPDATE BOARD SET agency=?, education=?, content=?, position=?, issurer=? WHERE board_id = ?";
		
		pstmt = con.prepareStatement(SQL); 
		
		pstmt.setString(1, boardDTO.getAgency());
		pstmt.setString(2, boardDTO.getEducation());
		pstmt.setString(3, boardDTO.getContent());
		pstmt.setString(4, boardDTO.getPosition());
		pstmt.setString(5, boardDTO.getIssurer());
		pstmt.setLong(6, boardDTO.getBoard_id());
		
		// 쿼리 실행
		// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
		int result = pstmt.executeUpdate(); 
		if (result == 1) {
			System.out.println("수정이 완료되었습니다.");
			return true;
		}
		return false;
		
	}
	
	// board Delete
	public boolean delete(Long board_id) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "DELETE FROM BOARD WHERE board_id=?";
		
		pstmt = con.prepareStatement(SQL); 
		
		pstmt.setLong(1, board_id);
		
		
		// 쿼리 실행
		// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
		int result = pstmt.executeUpdate(); 
		if (result == 1) {
			System.out.println("삭제가 완료되었습니다.");
			return true;
		}else {
		return false;
		}
	}
	
}
