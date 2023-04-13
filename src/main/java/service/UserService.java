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
	public void joinUser(UserDTO userDTO) throws ClassNotFoundException {
		
		try {
			userDAO.joinUser(userDTO);
			
		} catch (SQLException e) {
			e.printStackTrace();
			// pk(id) 중복값이 있는 경우
			System.out.println("존재하는 아이디 입니다. 다시 입력해주세요");
		
		}
	
	}
	
	// 아이디 찾기
	public String userInfoId(String name, Date b_day, String email) throws ClassNotFoundException, SQLException {
		String user_id =userDAO.userInfoId(name, b_day, email);
		return user_id;
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
	public void logIn(String id, String pw) throws ClassNotFoundException, SQLException {
		
		userDAO.logIn(id, pw);
	}
	

}
