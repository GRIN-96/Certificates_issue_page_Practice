package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		HttpSession session = request.getSession();
		
		request.setAttribute("id", session.getAttribute("id"));  
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/home.jsp");  // jsp 매핑
		dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
