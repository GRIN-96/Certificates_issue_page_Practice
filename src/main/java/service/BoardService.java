package service;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.BoardDAO;
import DAO.CompleteDAO;
import DTO.BoardDTO;
import DTO.UserListDTO;

public class BoardService {

	// 서비스를 외부에서도 사용할 수 있도록 오브젝트를 생성해줍니다.
	public static BoardService instance = new BoardService();
	BoardDAO boardDAO = new BoardDAO();  // 객체 생성
	
	private BoardService() {}
	
	// 해당클래스의 오브젝트를 불러와 사용하기 위해 쓰이는 함수입니다.
	public static BoardService getInstance() {
		return instance;
	}
	
	public int getListCount() {
		int x = 0;
		try {
			x = boardDAO.getListCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	// 모든 board 객체 반환 
	public ArrayList<BoardDTO> getBoardList(int page, int limit) {
		
		ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
		
		try {
			lists = boardDAO.getBoardList(page, limit);
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
	// board id search
	public BoardDTO searchId( int board_id ) {
		
		BoardDTO boardDTO = new BoardDTO();
		
		try {
			boardDTO = boardDAO.searchId(board_id);
			
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
	
	// board serch - agency
	public ArrayList<BoardDTO> searchAgency(String search, int page, int limit) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			list = boardDAO.searchAgency(search, page, limit);
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// board serch - education
	public ArrayList<BoardDTO> searchEducation(String search, int page, int limit) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			list = boardDAO.searchEducation(search, page, limit);
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 해당 교육 이수자 목록 불러오기
	public ArrayList<UserListDTO> userInfo(int board_id) {
		
		ArrayList<UserListDTO> lists = new ArrayList<UserListDTO>();
		
		try {
			lists = boardDAO.userInfo( board_id );
			
			return lists;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	
}
