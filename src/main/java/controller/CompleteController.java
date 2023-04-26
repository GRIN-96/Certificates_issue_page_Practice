package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

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
			String url = request.getParameter("url");
			
			
			// 형변환
			int complete_id = Integer.parseInt(com_id);
			int id = Integer.parseInt(board_id);
			
			if (completeService.deleteCom(complete_id)) {
				
				/* TABLE 삭제 성공 시 PDF 파일도 같이 삭제 */
				String filePath = "C:\\DEV\\spring\\Board\\src\\main\\webapp\\pdf";
				File file = new File(filePath + "/" + url);
				
				System.out.println(file);
				// 지정한 경로에 파일이 존재하는 지 확인
			    // 지정한 경로에 파일이 존재하는 경우 
			    if (file.exists()){

			      // 파일 삭제 성공시
			      if (file.delete()){

			        System.out.println("파일을 삭제 성공");

			      //파일 삭제 실패시
			      }else{
			        System.out.println("파일 삭제 실패");
			      }

			    // 지정한 경로에 파일이 존재안하는 경우 
			    }else{
			      System.out.println("파일이 없습니다.");
			    }
				
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
				
				response.sendRedirect("../Board/BoardController?action=detail&board_id="+board_id);
				
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
				
				
				
				/* 등록된 이수자들의 이수증.pdf 생성 */
				
				ArrayList<CertificatesDTO> certificates = new ArrayList<CertificatesDTO>();
				
				try {
					certificates = completeService.certificatesAll(board_id);
//					System.out.println(certificates);
					
					// db에 pdf 경로 등록
					completeService.updatePdfUrl(board_id);
					
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("certificates", certificates);
				request.setAttribute("board_id", board_id);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/NewCertificates.jsp");  // jsp 매핑
				dispatcher.forward(request, response);  // 위 페이지로 제어 전달.
//				response.sendRedirect("../Board/BoardController?action=detail&board_id="+board_id);
			}
			else {
				
				// 실패 시
				System.out.println("등록에 실패하였습니다. 다시시도해 주세요");
				response.sendRedirect("../Board/view/failpage.jsp");
				
			}
			
		}
		else if ("pdf_DL".equals(action)) {
			
		
		  System.out.println("pdf 생성 시도중");
		  String imgurl = request.getParameter("imgurl");
		  String name = request.getParameter("name");
		  String c_id = request.getParameter("c_id");
		  
//		  System.out.println(imgurl.split(",")[1]);
//		  System.out.println(imgurl);
		  
		  
		  
		  /* 전달받은 imgurl(base64 인코딩된)로 png 파일생성 */
		  
		  
		  String filePath = "C:\\DEV\\spring\\Board\\src\\main\\webapp\\pdf";
		  
		  String imageDataBytes = imgurl.substring(imgurl.indexOf(",")+1);
		  InputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(imageDataBytes.getBytes()));
			
		  File file = new File(filePath);
		  if(file.exists() == false) file.mkdirs();
		  
		  file = new File(filePath + "/test.png");
	      OutputStream out = null;
	        try {
	            out = new FileOutputStream(file);
	            int read = 0;
	            byte[] bytes = new byte[1024];

	            while ((read = stream.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (out != null) {
	                out.close();
	            }
	            if (stream != null) {
	            	stream.close();
	            }
	        }
	        
		  
		  /* 전달받은 imgurl(base64 인코딩된)로 PDF 파일생성 */
		  
		  Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		  try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath + "/" + name + c_id + ".pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		  document.open();
		  Image image = null;
		try {
			image = Image.getInstance(filePath + "/test.png");
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		}
		  try {
			image.scalePercent(50f);	// 이미지 크기 조절 (%)
			document.add(image);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		  document.close();
		  file.delete();
		  
		}
//		else if ( "pdf_url".equals(action) ) {
//			
//			System.out.println("pdf주소 db에 넣는 중");
//			
//			String id = request.getParameter("board_id");
//			int board_id = Integer.parseInt(id);
//			
//			try {
//				if (completeService.updatePdfUrl(board_id)) {
//					
//					response.sendRedirect("../Board/BoardController?action=detail&board_id="+board_id);
//				}
//			} catch (ClassNotFoundException | SQLException | IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
		
	}
	


