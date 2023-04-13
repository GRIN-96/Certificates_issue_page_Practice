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

import DTO.UserDTO;
import crypt.BCrypt;

public class UserDAO {
	
//	// sql 연결을 위한 호출 
	Connection con = null;  // Connection :: DB와 JAVA를 연결시켜주는 역할을 하는 객체입니다.
	PreparedStatement pstmt = null; // PreparedStatement :: SQL 쿼리를 작성하는데 도움을주는 객체 입니다.
	ResultSet rs = null;  // 결과값 담는 객체입니다.
	String dbURL = "jdbc:mariadb://localhost:3306/pdf";
	String dbID = "root";
	String dbPassword = "root";
	HttpSession session = null;
	
	public UserDAO(){}
	
	// 회원가입
	public boolean joinUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {

		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성
		String SQL = "INSERT INTO USER(user_id, user_pw, user_name, user_birthday, user_gender, user_email) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			// 쿼리 내 입력값 할당
			pstmt = con.prepareStatement(SQL);  // 쿼리문 적용
			pstmt.setString(1, userDTO.getUser_id());
			pstmt.setString(2, BCrypt.hashpw(userDTO.getUser_pw(), BCrypt.gensalt(10)));  // DEFAULT VALUE - 10
			pstmt.setString(3, userDTO.getUser_name());
			pstmt.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(userDTO.getUser_birthday()));
			pstmt.setString(5, userDTO.getUser_gender());
			pstmt.setString(6, userDTO.getUser_email());
			
			// 쿼리 실행
			// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
			int result = pstmt.executeUpdate(); 
			if (result == 1) {
				return true;
			}
			
			
		}  finally {  // sql 쿼리 실행 성공 시 문구 호출
			System.out.println("회원가입에 성공하셨습니다..");
		} return false;
	}
	
	// 이름, 생년월일, 이메일로 아이디 검색
	public String userInfoId(String name, Date b_day, String email) throws ClassNotFoundException, SQLException {

		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// 리턴할 객체 생성
		String user_id = null;  
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "SELECT user_id FROM user WHERE user_name = ? and user_birthday = ? and user_email = ? ";

		try {
			// 쿼리문 적용
			pstmt = con.prepareStatement(SQL); 
			pstmt.setString(1, name);
			pstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(b_day));
			pstmt.setString(3, email);
			
			rs = pstmt.executeQuery();  // 쿼리 수행 결과를 받아와 저장하는 객체입니다.
			
			// .next() : 수행결과로 ResultSet객체에서 하나의 Row를 반환한다. 더이상 결과가 없을 경우 false를 반환하면서 리턴합니다.
			while(rs.next()) {
				user_id = rs.getString("user_id");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
		return user_id;
	}
	
	//
	public ArrayList<UserDTO> userDatas() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// 리턴할 객체 생성
		ArrayList<UserDTO> lists = new ArrayList<UserDTO>();  
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "SELECT * FROM user";
		
		pstmt = con.prepareStatement(SQL); 
		
		rs = pstmt.executeQuery(); // 모든 정보 담기
		
		while (rs.next()) {
			
			UserDTO userDTO = new UserDTO(
					rs.getString("user_id"),
					rs.getString("user_pw"),
					rs.getString("user_name"),
					rs.getDate("user_birthday"),
					rs.getString("user_gender"),
					rs.getString("user_email"),
					rs.getTimestamp("user_joindate"));
			
			lists.add(userDTO);  // 객체 생성 후 리스트추가
		}
		
		return lists;
		
	}
	// 로그인 서비스
	public void logIn(String id, String pw) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성  = 아이디 찾기
		String SQL = "SELECT * FROM user WHERE user_id = ?";
		
		pstmt = con.prepareStatement(SQL); 
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery(); // 모든 정보 담기
		
		if (rs.next()) {
			
			if (BCrypt.checkpw(pw, rs.getString("user_pw")));  {  // 암호화된 패스워드를 비교
				
				// 세션 생성 => 현재 사이트에서 어디에서든 읽을 수 있는 변수 (db메모리사용)
				session.setAttribute("id", rs.getString("user_id"));
									// 변수명            // DB필드명
				
			}
		}
	}
	
}
