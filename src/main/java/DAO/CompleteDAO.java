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

import DTO.CertificatesDTO;
import DTO.CompleteDTO;
import DTO.UserListDTO;

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
	
	// 나의 이수증 불러오기
	public ArrayList<CertificatesDTO> allMyList(String user_id) throws ClassNotFoundException, SQLException {
		
		ArrayList<CertificatesDTO> lists = new ArrayList<CertificatesDTO>();
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "SELECT c.complete_id, b.agency, b.education, u.user_name, c.issue_date, b.content, b.position, b.issurer, c.pass_fail, u.user_birthday, c.pdf_url  FROM USER u INNER JOIN complete c INNER JOIN board b ON u.user_id =  c.user_id AND b.board_id  = c.board_id  WHERE c.user_id  = ? ORDER BY c.issue_date DESC";
		
		pstmt = con.prepareStatement(SQL); 
		
		pstmt.setString(1, user_id);
		
		rs = pstmt.executeQuery(); // 모든 정보 담기
		
		while (rs.next()) {
			
			CertificatesDTO certificatesDTO = new CertificatesDTO(
					rs.getInt("complete_id"),
					rs.getString("agency"),
					rs.getString("education"),
					rs.getString("user_name"),
					rs.getDate("issue_date"),
					rs.getString("content"),
					rs.getString("position"),
					rs.getString("issurer"),
					rs.getString("pass_fail"),
					rs.getDate("user_birthday"),
					rs.getString("pdf_url"));
			
			lists.add(certificatesDTO);  // 객체 생성 후 리스트추가
		}
		
		return lists;
	}
	
	// pdf 생성을 위한 이수증 가져오기
	public CertificatesDTO certificatesInfo(String user_id, String board_id) throws ClassNotFoundException, SQLException {
		
		CertificatesDTO certificatesDTO = new CertificatesDTO();
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디, 게시판 번호로 찾기.
		String SQL = "SELECT c.complete_id, b.agency, b.education, u.user_name, c.issue_date, b.content, b.position, b.issurer, c.pass_fail, u.user_birthday, c.pdf_url FROM USER u INNER JOIN complete c INNER JOIN board b ON u.user_id =  c.user_id AND b.board_id  = c.board_id  WHERE c.user_id  = ? AND  c.complete_id  = ?";
		
		pstmt = con.prepareStatement(SQL); 
		
		pstmt.setString(1, user_id);
		pstmt.setString(2, board_id);
		
		rs = pstmt.executeQuery(); // 모든 정보 담기
		
		while (rs.next()) {
			
			certificatesDTO = new CertificatesDTO(
					rs.getInt("complete_id"),
					rs.getString("agency"),
					rs.getString("education"),
					rs.getString("user_name"),
					rs.getDate("issue_date"),
					rs.getString("content"),
					rs.getString("position"),
					rs.getString("issurer"),
					rs.getString("pass_fail"),
					rs.getDate("user_birthday"),
					rs.getString("pdf_url"));
			
		}
		
		return certificatesDTO;
		
	}
	
	public ArrayList<CertificatesDTO> certificatesAll(int board_id) throws ClassNotFoundException, SQLException {
			
			ArrayList<CertificatesDTO> lists = new ArrayList<CertificatesDTO>();
			
			Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
			
			// SQL QUERY 작성  = 아이디 찾기
			String SQL = "SELECT c.complete_id , b.agency, b.education, u.user_name, c.issue_date, b.content, b.position, b.issurer, c.pass_fail, u.user_birthday, c.pdf_url  FROM user u INNER JOIN complete c INNER JOIN board b ON u.user_id =  c.user_id AND b.board_id  = c.board_id  WHERE c.board_id  = ? AND c.pass_fail = 'P'";
			
			pstmt = con.prepareStatement(SQL); 
			
			pstmt.setInt(1, board_id);
			
			rs = pstmt.executeQuery(); // 모든 정보 담기
			
			while (rs.next()) {
				
				CertificatesDTO certificatesDTO = new CertificatesDTO(
						rs.getInt("complete_id"),
						rs.getString("agency"),
						rs.getString("education"),
						rs.getString("user_name"),
						rs.getDate("issue_date"),
						rs.getString("content"),
						rs.getString("position"),
						rs.getString("issurer"),
						rs.getString("pass_fail"),
						rs.getDate("user_birthday"),
						rs.getString("pdf_url"));
				
				lists.add(certificatesDTO);  // 객체 생성 후 리스트추가
			}
			
			return lists;
	}

	public boolean updatePdfUrl(int board_id) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "UPDATE USER u INNER JOIN complete c ON u.user_id =  c.user_id SET c.pdf_url = CONCAT(u.user_name , c.complete_id,'.pdf') WHERE c.pass_fail = 'P' AND c.board_id = ? ";
		
		pstmt = con.prepareStatement(SQL); 
		pstmt.setInt(1, board_id);
	
		// 쿼리 실행
		// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
		int result = pstmt.executeUpdate(); 
		if (result == 1) {
			System.out.println("pdf 경로 입력이완료되었습니다..");
			return true;
		}else {
			return false;
		}
	}

	
}
