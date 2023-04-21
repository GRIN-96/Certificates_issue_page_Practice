package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DTO.UserDTO;

public class UserService {
	
	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static UserService instance = new UserService();
	UserDAO userDAO = new UserDAO();  // 객체 생성
	
	private UserService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static UserService getInstance() {
		return instance;
	}
	
	// 회원가입
	public boolean joinUser(UserDTO userDTO) throws ClassNotFoundException {
		
		try {
			if (userDAO.joinUser(userDTO)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	
	}
	
	// 아이디 찾기
	public String userInfoId(String name, Date b_day, String email) throws ClassNotFoundException, SQLException {
		String user_id =userDAO.userInfoId(name, b_day, email);
		return user_id;
	}
	
	// 비밀번호 찾기 -> 비밀번호 변경
	public boolean newPw(String id, String new_pw) {
		
		try {
			if (userDAO.newPw(id, new_pw)) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	// 유저 리스트 가져오기
	public ArrayList<UserDTO> userDatas() {
		
		ArrayList<UserDTO> lists = new ArrayList<UserDTO>();
		
		try {
			lists = userDAO.userDatas();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
		
	}
	
	// 로그인
	public String logIn(String id, String pw) throws ClassNotFoundException, SQLException {
		
		return userDAO.logIn(id, pw);
	}
	
	
	// 비밀번호 변경
	public boolean pwChange(String id, String pw, String new_pw) {
		
		try {
			if (userDAO.pwChange(id, pw, new_pw)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	// 회원 탈퇴
	public boolean userDelete(String id, String pw) {

		try {
			if (userDAO.userDelete(id, pw)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
