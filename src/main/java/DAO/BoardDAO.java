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
		String SQL = "SELECT * FROM board";
		
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
	
}
