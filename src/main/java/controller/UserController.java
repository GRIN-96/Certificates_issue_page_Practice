package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
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
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		
		// 전체 회원 리스트 불러오기.
		if (action.equals("allUserData")) {
			
			ArrayList<UserDTO> lists = new ArrayList<UserDTO>();
			
			lists = userService.userDatas();
			
			System.out.println(lists);
			
			response.sendRedirect("../Board/");
		}
		
		// 로그아웃
		else if (action.equals("logout"))  {
			
			System.out.println("로그아웃 되었습니다.");
			
			session.removeAttribute("id");
			
			response.sendRedirect("../Board/");
			
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
		HttpSession session = request.getSession();
		
		
		// 회원가입
		if ("join".equals(action)) {   
			
			// String 값으로 받아온 birthday 변수를 Date type으로 변경해주는 작업입니다.
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date b_day = null;
			
			try {
				b_day = df.parse(bday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			UserDTO userDTO = new UserDTO(id, pw, name, b_day, gender, email);
			
			System.out.println(id);
			System.out.println(pw);
			System.out.println(name);
			System.out.println(gender);
			System.out.println(bday);
			System.out.println(email);
			
			// 유저 회원가입
			try {
				if (userService.joinUser(userDTO)) {
					
					response.sendRedirect("../Board/");
					
				}else {
					System.out.println("회원가입에 실패하였습니다. 다시시도해 주세요");
					
					alertAndGo(response, "이미 존재하는 아이디 입니다.", "/Board/view/joinpage.jsp");
//					response.sendRedirect("../Board/view/failpage.jsp");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		// 아이디 찾기
		else if (action.equals("userinfo_id")) {
			
			// String 값으로 받아온 birthday 변수를 Date type으로 변경해주는 작업입니다.
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date b_day = null;
			
			try {
				b_day = df.parse(bday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			String user_id = null;
			try {
				user_id = userService.userInfoId(name, b_day, email);
				
				if ( user_id != null ) {
					
					request.setAttribute("user_id", user_id);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inforesult.jsp");  // jsp 매핑
					dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
				} else {
					System.out.println("회원정보를 찾을 수 없습니다. 다시시도해 주세요");
					response.sendRedirect("../Board/view/failpage.jsp");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}
		//패스워드 찾기 1 -> 회원인증 후 패스워드 변경
		else if (action.equals("userinfo_pw")) {
			
			// String 값으로 받아온 birthday 변수를 Date type으로 변경해주는 작업입니다.
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date b_day = null;
			
			try {
				b_day = df.parse(bday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String user_id = null;
			
			try {
				user_id = userService.userInfoId(name, b_day, email);
				
				if (user_id != null) {
					System.out.println("회원인증이 완료되었습니다.");
					response.sendRedirect("../Board/view/newpw.jsp");
				}else {
					System.out.println("회원정보를 찾을 수 없습니다. 다시시도해 주세요");
					response.sendRedirect("../Board/view/failpage.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}
		//패스워드 찾기 2 -> 회원인증 후 패스워드 변경
		else if (action.equals("new_pw")) {
			
				String new_pw = request.getParameter("new_pw");
			
				if (userService.newPw(id, new_pw)) {
				
					System.out.println("비밀번호가 변경되었습니다.");
					
					session.removeAttribute("id");
					
					response.sendRedirect("../Board/");
				}else {
					System.out.println("비밀번호 변경에 실패하였습니다. 다시시도해 주세요");
					response.sendRedirect("../Board/view/failpage.jsp");
			}
			
		}
		
		// 로그인
		else if (action.equals("login")) {
			
			String user_id = null; 
			
			try {
				user_id = userService.logIn(id, pw);
				
				if (user_id != null) { 
				
				// 세션 생성 => 현재 사이트에서 어디에서든 읽을 수 있는 변수 (db메모리사용)
				session.setAttribute("id", user_id);
				
				System.out.println("로그인이 완료되었습니다.");
				
				response.sendRedirect("../Board/Home"); // home 으로 이동
				
				}else {
					System.out.println("로그인에 실패하였습니다. 다시시도해 주세요");
					response.sendRedirect("../Board/view/failpage.jsp");
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 비밀번호 변경
		else if (action.equals("pw_change")) {
			
			String new_pw = request.getParameter("new_pw");
			
			if (userService.pwChange(id, pw, new_pw)) {
				
				System.out.println("비밀번호가 변경되었습니다.");
				
				session.removeAttribute("id");
				
				response.sendRedirect("../Board/");
			}else {
				System.out.println("비밀번호 변경에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
			}
			
		}
		// 회원 탈퇴
		else if (action.equals("user_delete")) {
			
			if (userService.userDelete(id, pw)) {
				
				System.out.println("회원탈퇴가 완료되었습니다.");
				
				session.removeAttribute("id");
				
				response.sendRedirect("../Board/");
			}else {
				System.out.println("회원탈퇴에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
			}
			
		}
		
	}
	//알림창만 띄우고 이동 
	public static void alertAndGo(HttpServletResponse response, String msg, String url) {
	    try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('"+msg+"');location.href='"+url+"'</script>");
			w.flush();
			w.close();
	    } catch(Exception e) {
			e.printStackTrace();
	    }
	}
	//알림창만 띄우기
	public static void alert(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('"+msg+"');</script>");
			w.flush();
			w.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
