package service;

import java.sql.SQLException;

import DAO.UserDAO;
import DTO.UserDTO;

public class UserService {
	
	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static UserService instance = new UserService();
	
	private UserService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static UserService getInstance() {
		return instance;
	}
	
	
	public void joinUser(UserDTO userDTO) throws ClassNotFoundException {
		
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.joinUser(userDTO);
			
		} catch (SQLException e) {
			e.printStackTrace();
			// pk(id) 중복값이 있는 경우
			System.out.println("존재하는 아이디 입니다. 다시 입력해주세요");
		
		}
	
	}
}
