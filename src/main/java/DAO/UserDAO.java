package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DTO.UserDTO;

public class UserDAO {
	
	// sql 연결을 위한 호출 
	private Connection con;  // Connection :: DB와 JAVA를 연결시켜주는 역할을 하는 객체이다.
	private Statement stmt; 
	private PreparedStatement pstmt; // PreparedStatement :: SQL문을 실행할 때 실행을 위한 SQL을 DB에 전달하는 역할을 수행하는 객체다.
	private DataSource ds;
	
	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/pool");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void joinUser(UserDTO userDTO) {
		
		
		
	}
	
}
