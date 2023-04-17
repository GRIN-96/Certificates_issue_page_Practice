package service;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.BoardDAO;
import DTO.BoardDTO;

public class BoardService {

	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static BoardService instance = new BoardService();
	BoardDAO boardDAO = new BoardDAO();  // 객체 생성
	
	private BoardService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static BoardService getInstance() {
		return instance;
	}
	
	// 모든 board 객체 반환 
	public ArrayList<BoardDTO> boardDatas() {
		
		ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
		
		try {
			lists = boardDAO.boardDatas();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	// board 생성 
	public boolean boardInsert(BoardDTO boardDTO) {
		try {
			if(boardDAO.boardInsert(boardDTO)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// board 상세페이지에 입력할 값 출력
	public BoardDTO detailBoard(String agency, String education) {
		
		BoardDTO boardDTO = new BoardDTO();
		
		try {
			boardDTO = boardDAO.detailBoard(agency, education);
			return boardDTO;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return boardDTO;
		
		
	}
	
	// board Update
	public boolean boardUpdate(BoardDTO boardDTO) {
		
		try {
			if(boardDAO.boardUpdate(boardDTO)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// board Delete
	public boolean delete(Long board_id) {
		
		try {
			if ( boardDAO.delete(board_id) ) {
				
				return true;
			}else {
			return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
