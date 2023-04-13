package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.UserDTO;

public class UserDAO {
	
//	// sql 연결을 위한 호출 
	private Connection con;  // Connection :: DB와 JAVA를 연결시켜주는 역할을 하는 객체입니다.
	private PreparedStatement pstmt; // PreparedStatement :: SQL 쿼리를 작성하는데 도움을주는 객체 입니다.
	private ResultSet rs;  // 결과값 담는 객체입니다.
	
	public UserDAO(){}
	
	public boolean joinUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {

		String dbURL = "jdbc:mariadb://localhost:3306/pdf";
		String dbID = "root";
		String dbPassword = "root";
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);  // JDBC Driver 클래스를 로드하기 위해 사용됩니다. = jdbc 접근을 도와줍니다.
		con = DriverManager.getConnection(dbURL, dbID, dbPassword);  // DB에 연결
		
		// SQL QUERY 작성
		String SQL = "INSERT INTO USER(user_id, user_pw, user_name, user_birthday, user_gender, user_email) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			// 쿼리 내 입력값 할당
			pstmt = con.prepareStatement(SQL);  // 쿼리문 적용
			pstmt.setNString(1, userDTO.getUser_id());
			pstmt.setNString(2, userDTO.getUser_pw());
			pstmt.setNString(3, userDTO.getUser_name());
			pstmt.setDate(4, (Date) userDTO.getUser_birthday());
			pstmt.setNString(5, userDTO.getUser_id());
			pstmt.setNString(6, userDTO.getUser_id());
			
			// 쿼리 실행
			// compile된 DML문을 실행시켜 성공적으로 수행될 경우 1, 실패의 경우 0을 반환합니다.
			int result = pstmt.executeUpdate(); 
			if (result == 1) {
				return true;
			}
			
//			rs = pstmt.executeQuery();  // 쿼리 수행 결과를 받아와 저장하는 객체입니다.
			
			
		}  finally {  // sql 쿼리 실행 성공 시 문구 호출
			System.out.println("회원가입에 성공하셨습니다..");
		} return false;
	}
	
}
