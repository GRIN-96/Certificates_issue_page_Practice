package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
}
