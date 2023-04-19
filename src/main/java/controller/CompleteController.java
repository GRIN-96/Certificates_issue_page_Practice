package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.BoardDTO;
import DTO.CompleteDTO;
import DTO.UserListDTO;
import service.BoardService;
import service.CompleteService;

@WebServlet("/CompleteController")
public class CompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 외부에서 해달 클래스를 사용할 수 있도록 오브젝트를 생성해줍니다.
	private static CompleteController instance = new CompleteController();
	
	// 함수 실행 시 오브젝트 값 리턴
	public static CompleteController getInstance() {
		return instance;
	}
	
	// 서비스 클래스 사용을 위해 호출을 해줍니다.
	private static CompleteService completeService = CompleteService.getInstance();
	
	// 서비스 클래스 사용을 위해 호출을 해줍니다.
	private static BoardService boardService = BoardService.getInstance();
       
    public CompleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		String action = request.getParameter("action");
		
		if (action.equals("delete")) {
			
			String com_id = request.getParameter("com_id");
			String board_id = request.getParameter("board_id");
			
			// 형변환
			int complete_id = Integer.parseInt(com_id);
			int id = Integer.parseInt(board_id);
			
			if (completeService.deleteCom(complete_id)) {
				
				//삭제 완료 시 - > 다시 board detail view로 
				BoardDTO boardDTO = new BoardDTO();
				ArrayList<UserListDTO> userList = new ArrayList<UserListDTO>();
				
				boardDTO = boardService.searchId(id);
				
				// long -> int 형변환
				int board_id1 = boardDTO.getBoard_id().intValue();
				
				
				if (boardDTO != null) {
					
					// 해당 교육의 이수자목록 가져오기.
					userList = boardService.userInfo(board_id1);
					
					if ( userList != null ) {
						
						request.setAttribute("userList", userList);
						
					}
				}
				
				request.setAttribute("board", boardDTO);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/detailview.jsp");  // jsp 매핑
				dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
				
			}else {
				
				// 작업 실패 시
				System.out.println("삭제에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
				
			}
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		response.setCharacterEncoding("UTF-8"); 
		
		String action = request.getParameter("action");
		
		// id -> int로 형변환
		String b_id = request.getParameter("id");
		int board_id = Integer.parseInt(b_id);
		
		if (action.equals("insert")) {
			
			System.out.println("파라미터 읽어오기");
			
			String[] user_id = request.getParameterValues("user_id");
			String[] result = request.getParameterValues("result");
			String[] dates = request.getParameterValues("issue_date");
			
			// 여러 입력값을 담아줄 배열 생성
			ArrayList<CompleteDTO> lists = new ArrayList<CompleteDTO>();
			
			// String 값으로 받아온 issue_date 변수를 Date type으로 변경해주는 작업입니다.
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date issue_date = null;
			
			// 하나씩 데이터 생성
			for (int i = 0; i < user_id.length; i++) {
				
				
				try {
					issue_date = df.parse(dates[i]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					issue_date = df.parse(dates[i]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				CompleteDTO completeDTO = new CompleteDTO(user_id[i], board_id, issue_date, result[i]);
				
				lists.add(completeDTO);
				
	        }
			
			if(completeService.insertComplete(lists)) {
				
				// 성공 시 
				BoardDTO boardDTO = new BoardDTO();
				ArrayList<UserListDTO> userList = new ArrayList<UserListDTO>();
				
				boardDTO = boardService.searchId(board_id);
				
				// long -> int 형변환
				int board_id1 = boardDTO.getBoard_id().intValue();
				
				
				if (boardDTO != null) {
					
					// 해당 교육의 이수자목록 가져오기.
					userList = boardService.userInfo(board_id1);
					
					if ( userList != null ) {
						
						request.setAttribute("userList", userList);
						
					}
				}
				
				request.setAttribute("board", boardDTO);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/detailview.jsp");  // jsp 매핑
				dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
			}
			else {
				
				// 실패 시
				System.out.println("등록에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
				
			}
			
		}
	
	}

}
