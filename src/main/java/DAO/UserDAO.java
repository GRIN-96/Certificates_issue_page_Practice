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
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
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
