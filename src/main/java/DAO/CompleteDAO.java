package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import DTO.CompleteDTO;

public class CompleteDAO {

	private CompleteDTO completeDTO;
	// sql 연결을 위한 호출 
	Connection con = null;  // Connection :: DB와 JAVA를 연결시켜주는 역할을 하는 객체입니다.
	PreparedStatement pstmt = null; // PreparedStatement :: SQL 쿼리를 작성하는데 도움을주는 객체 입니다.
	ResultSet rs = null;  // 결과값 담는 객체입니다.
	String dbURL = "jdbc:mariadb://localhost:3306/pdf";
	String dbID = "root";
	String dbPassword = "root";
	HttpSession session;
	
	public CompleteDAO(){}
	
	// insert complete
	public boolean insertComplete(ArrayList<CompleteDTO> lists) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "INSERT INTO COMPLETE(user_id, board_id, issue_date, pass_fail) VALUES (?, ?, ?, ?)";
		
		for (int i =0; i < lists.size(); i++) {
			
			pstmt = con.prepareStatement(SQL); 
			pstmt.setString(1, lists.get(i).getUser_id());
			pstmt.setInt(2, lists.get(i).getBoard_id());
			pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(lists.get(i).getIssue_date()));
			pstmt.setString(4, lists.get(i).getPass_fail());
			pstmt.executeUpdate(); 
			
			if (i == lists.size()-1) {
				System.out.println("추가가 완료되었습니다.");
				return true;
			}
		}
		return false;

	}
	
	// 이수자 정보 삭제
	public boolean deleteCom( int complete_id ) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "DELETE FROM complete WHERE complete_id = ?";
		
		pstmt = con.prepareStatement(SQL); 
		pstmt.setInt(1, complete_id);
	
		// 쿼리 실행
		// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
		int result = pstmt.executeUpdate(); 
		if (result == 1) {
			System.out.println("이수자 정보 삭제가 완료되었습니다.");
			return true;
		}else {
			return false;
		}
	}
	
}
