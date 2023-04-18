package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		String action = request.getParameter("action");
		
		if (action.equals("home")) {
			
			// page start
			int page = 1; // 처음엔 무조건 1페이지 실행
			int limit = 5; // 한 페이지에 보이는 최대 게시글 수 
			
			// 값이 넘어오면 null이 아니고, 입력값이 없으면 null -> 처음 정해준 page값 적용.
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page")); 
			}
			
			ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
			
			lists = boardService.getBoardList(page, limit);
			
			request.setAttribute("lists", lists);
			request.setAttribute("page", page);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/managerPage.jsp");  // jsp 매핑
			dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
			
		}
		// board 상세 페이지
		else if (action.equals("detail")) {
			
			String agency = request.getParameter("agency");
			String education = request.getParameter("education");
			BoardDTO boardDTO = new BoardDTO();
			
			boardDTO = boardService.detailBoard(agency, education);
			
			request.setAttribute("board", boardDTO);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/detailview.jsp");  // jsp 매핑
			dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
			
		}
		
		// complete table insert
		else if (action.equals("insert_complete")) {
			String id = request.getParameter("id");
			long board_id = Long.parseLong(id); 
			
			request.setAttribute("board_id", board_id);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/insertcomplete.jsp");  // jsp 매핑
			dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
		}
		
		// 수정페이지
		else if (action.equals("edit")) {
			
			String agency = request.getParameter("agency");
			String education = request.getParameter("education");
			BoardDTO boardDTO = new BoardDTO();
			
			boardDTO = boardService.detailBoard(agency, education);
			
			request.setAttribute("board", boardDTO);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/editboard.jsp");  // jsp 매핑
			dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
			
		}
		
		// 삭제 페이지
		else if (action.equals("delete")) {
			
			String id = request.getParameter("id");
			long board_id = Long.parseLong(id);  
			
			if (boardService.delete(board_id)) {
				
				// 추가에 성공하면 페이지이동
				response.sendRedirect("../Board/BoardController?action=home");
				
			}else {
				// 실패 시 페이지 이동
				System.out.println("board 삭제에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		String action = request.getParameter("action");
		String agency = request.getParameter("agency");
		String education = request.getParameter("education");
		String content = request.getParameter("content");
		String position = request.getParameter("position");
		String issurer = request.getParameter("issurer");
		HttpSession session = request.getSession();
		
		if (action.equals("new_board")) {
			
			// insert를 위해 boardDTO 객체 생성
			BoardDTO boardDTO = new BoardDTO(agency, education, content, position, issurer);
			
			if (boardService.boardInsert(boardDTO)) {
				
				// 추가에 성공하면 페이지이동
				response.sendRedirect("../Board/BoardController?action=home");
				
			}else {
				
				// 실패 시 페이지 이동
				System.out.println("board 추가에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
				
			}
			
		}
		
		// 수정하기
		else if ( action.equals("edit_board") ) {
			
			String id = request.getParameter("id");
			long board_id = Long.parseLong(id); 
			
			// insert를 위해 boardDTO 객체 생성
			BoardDTO boardDTO = new BoardDTO(board_id, agency, education, content, position, issurer);
			
			if (boardService.boardUpdate(boardDTO)) {
				
				// 수정에 성공하면 페이지이동
				response.sendRedirect("../Board/BoardController?action=home");
				
			}else {
				// 실패 시 페이지 이동
				System.out.println("board 수정에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
			}
		}
		
		// 검색기능
		else if ( action.equals("search") ) {
			
			String search = request.getParameter("search");
			String searchbar = request.getParameter("searchbar");
			
			// page start
			int page = 1; // 처음엔 무조건 1페이지 실행
			int limit = 5; // 한 페이지에 보이는 최대 게시글 수 
			
			// 값이 넘어오면 null이 아니고, 입력값이 없으면 null -> 처음 정해준 page값 적용.
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page")); 
			}
			
			ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
			
			request.setAttribute("page", page);
			
			if (search.equals("agency")) {
				lists = boardService.searchAgency(searchbar, page, limit);
				request.setAttribute("lists", lists);
			}
			else if (search.equals("education")) {
				lists = boardService.searchEducation(searchbar, page, limit);
				request.setAttribute("lists", lists);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/managerPage.jsp");  // jsp 매핑
			dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
			
		}
		
	}

}
