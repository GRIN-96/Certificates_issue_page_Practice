package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompleteController")
public class CompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 외부에서 해달 클래스를 사용할 수 있도록 오브젝트를 생성해줍니다.
	private static CompleteController instance = new CompleteController();
	
	// 함수 실행 시 오브젝트 값 리턴
	public static CompleteController getInstance() {
		return instance;
	}
       
    public CompleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("하이하이");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		String action = request.getParameter("action");
		
		System.out.println("action");
		System.out.println("gdgd");
		if (action.equals("test1")) {
			String id = request.getParameter("id");
			System.out.println(id);
		}
		if (action.equals("test")) {
			
			System.out.println("파라미터 읽어오기");
			
			String[] id = request.getParameterValues("id");
			String[] result = request.getParameterValues("result");
			String[] issue_date = request.getParameterValues("issue_date");
			
			for (int i = 0; i < id.length; i++) {
	            System.out.println(id[i]);
	        }
			
			System.out.println(id);
			System.out.println(result);
			System.out.println(issue_date);
			
		}
	
	}

}
