package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.fontbox.util.BoundingBox;

import DTO.BoardDTO;
import DTO.CertificatesDTO;
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
		// 마이페이지 내에 출력가능한 이수증 보기
		else if (action.equals("myList")) {
			
			ArrayList<CertificatesDTO> mylist = new ArrayList<CertificatesDTO>();
			HttpSession session = request.getSession();
			
			// 세션Id 값 가져오기
		    String sId = (String) session.getAttribute("id"); 
		    
		    mylist = completeService.allMyList(sId);
		    
		    if (mylist != null) {
		    	
		    	request.setAttribute("mylist", mylist);
		    	
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/view/certificates.jsp");  // jsp 매핑
				dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
		    	
		    }
		    
		}
		
		// 이수증 보기
		else if (action.equals("pdf_viewer")) {
			
			CertificatesDTO certificatesDTO = new CertificatesDTO();
			HttpSession session = request.getSession();
			String board_id = request.getParameter("board_id");
		
			
			// 세션Id 값 가져오기
		    String sId = (String) session.getAttribute("id"); 
		    
		    certificatesDTO = completeService.certificatesInfo(sId, board_id);
		    
		    if (certificatesDTO != null) {
		    	
		    	request.setAttribute("certificatesDTO", certificatesDTO);
		    	
		    	Date b_day = certificatesDTO.getUser_birthday();
		    	Date i_day = certificatesDTO.getIssue_date();
		    	
		    	// 포멧 생성
		    	SimpleDateFormat b_d = new SimpleDateFormat("yyyy년 MM월 dd일생");
		    	SimpleDateFormat i_d = new SimpleDateFormat("yyyy년 MM월 dd일");
		    	
		    	String u_birthday = b_d.format(b_day);
		    	String b_issue = i_d.format(i_day);
		    	
		    	request.setAttribute("u_birthday", u_birthday);
		    	request.setAttribute("b_issue", b_issue);
		    	
		    	
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/view/pdfviewer.jsp");  // jsp 매핑
				dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
		    	
		    }
			
		}
		
		// 이수증 만들기 
		else if (action.equals("pdf")) {
			
			CertificatesDTO certificatesDTO = new CertificatesDTO();
			HttpSession session = request.getSession();
			String board_id = request.getParameter("board_id");
		
			
			// 세션Id 값 가져오기
		    String sId = (String) session.getAttribute("id"); 
		    
		    certificatesDTO = completeService.certificatesInfo(sId, board_id);
		    
		    if (certificatesDTO != null) {
		    	
		    	request.setAttribute("certificatesDTO", certificatesDTO);
		    	
		    	Date b_day = certificatesDTO.getUser_birthday();
		    	Date i_day = certificatesDTO.getIssue_date();
		    	
		    	// 포멧 생성
		    	SimpleDateFormat b_d = new SimpleDateFormat("yyyy년 MM월 dd일생");
		    	SimpleDateFormat i_d = new SimpleDateFormat("yyyy년 MM월 dd일");
		    	
		    	String u_birthday = b_d.format(b_day);
		    	String b_issue = i_d.format(i_day);
		    	
		    	request.setAttribute("u_birthday", u_birthday);
		    	request.setAttribute("b_issue", b_issue);
		    	
		    	
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/view/NewCertificates.jsp");  // jsp 매핑
				dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
		    	
		    }
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8"); 
		
		String action = request.getParameter("action");
		
		
		
		if (action.equals("insert")) {
			
			System.out.println("파라미터 읽어오기");
			
			// id -> int로 형변환
			String b_id = request.getParameter("id");
			int board_id = Integer.parseInt(b_id);
			
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
		else if (action.equals("pdf_DL")) {
			
			System.out.println("데이터 전달시도");
			
			try {
			      // 받은 FormData에서 이미지 데이터 추출
			      InputStream inputStream = request.getPart("imgData").getInputStream();
			      BufferedImage bufferedImage = ImageIO.read(inputStream);

			      // PDF 생성
			      PDDocument document = new PDDocument();
			      PDPage page = new PDPage(new PDRectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));
			      document.addPage(page);
			      PDImageXObject imageXObject = LosslessFactory.createFromImage(document, bufferedImage);
			      try (PDPageContentStream contents = new PDPageContentStream(document, page)) {
			        contents.drawImage(imageXObject, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
			      }

			      // PDF 저장
			      String fileName = "myFile.pdf";
			      String filePath = getServletContext().getRealPath("pdfjs/web/" + fileName);
			      document.save(new File(filePath));
			      document.close();

			      // 저장된 PDF 파일 경로 응답
			      response.getWriter().write(filePath);
			    } catch (Exception e) {
			      e.printStackTrace();
			      response.getWriter().write("Error saving PDF file: " + e.getMessage());
			    }
	        
		}
	}
		
	}
	


