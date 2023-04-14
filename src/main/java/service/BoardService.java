package service;

import java.util.ArrayList;

import DAO.UserDAO;
import DTO.BoardDTO;

public class BoardService {

	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static BoardService instance = new BoardService();
//	UserDAO userDAO = new UserDAO();  // 객체 생성
	
	private BoardService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static BoardService getInstance() {
		return instance;
	}

	//
	public ArrayList<BoardDTO> boardDatas() {
		
		ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
		
		return lists;
	}

	
}
