package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import DTO.UserDTO;
import service.UserService;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 외부에서 해달 클래스를 사용할 수 있도록 오브젝트를 생성해줍니다.
	private static UserController instance = new UserController();
	
	// 함수 실행 시 오브젝트 값 리턴
	public static UserController getInstance() {
		return instance;
	}
	
	// 서비스 클래스 사용을 위해 호출을 해줍니다.
	private static UserService userService = UserService.getInstance();
       
	// 실행함수
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		
		// 전체 회원 리스트 불러오기.
		if (action.equals("allUserData")) {
			
			ArrayList<UserDTO> lists = new ArrayList<UserDTO>();
			
			lists = userService.userDatas();
			
			System.out.println(lists);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		// request로 넘어온 파라미터값 받아서 변수에 담기
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String bday = request.getParameter("bday");
		String email = request.getParameter("email");
		
		// String 값으로 받아온 birthday 변수를 Date type으로 변경해주는 작업입니다.
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date b_day = null;
		
		try {
			b_day = df.parse(bday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/NewCertificates.jsp");  // jsp 매핑
//		dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
		
		// 회원가입
		if (action.equals("join")) {   
			
			UserDTO userDTO = new UserDTO(id, pw, name, b_day, gender, email);
			
//			System.out.println(id);
//			System.out.println(pw);
//			System.out.println(name);
//			System.out.println(gender);
//			System.out.println(bday);
//			System.out.println(email);
			
			// 유저 회원가입
			try {
				userService.joinUser(userDTO);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		// 아이디 찾기
		else if (action.equals("userinfo_id")) {
			
			String user_id = null;
			try {
				user_id = userService.userInfoId(name, b_day, email);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println(user_id);
		}
		// 로그인
		else if (action.equals("login")) {
			
			try {
				userService.logIn(id, pw);
				
				response.sendRedirect("/index.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
