package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.BoardDTO;
import service.BoardService;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 외부에서 해달 클래스를 사용할 수 있도록 오브젝트를 생성해줍니다.
	private static BoardController instance = new BoardController();
	
	// 함수 실행 시 오브젝트 값 리턴
	public static BoardController getInstance() {
		return instance;
	}
	
	// 서비스 클래스 사용을 위해 호출을 해줍니다.
	private static BoardService boardService = BoardService.getInstance();
	
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equals("home")) {
			
			ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
			
			lists = boardService.boardDatas();
			
			request.setAttribute("lists", lists);
			request.setAttribute("lists_size", lists.size());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/managerPage.jsp");  // jsp 매핑
			dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
