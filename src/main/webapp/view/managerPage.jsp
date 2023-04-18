<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="DTO.*" %>
<%@ page import="DAO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Page</title>
</head>

<%
	//1. 디비에서 전체 글목록을 읽어서 가져오기
	BoardDAO boardDAO = new BoardDAO();
	//2. 디비에 글이 있는지 확인 후 있으면 글 모두 가져오기 
	ArrayList<BoardDTO> lists = (ArrayList<BoardDTO>) request.getAttribute("lists");
	//3. 디비에 글이 있는지 확인 후 있으면 글 모두 가져오기,없으면 가져오지않기 : getBoardCount()
	int cnt = boardDAO.getListCount();
	
	//7. 페이징처리 (이미 유명한 알고리즘 공식, 사용법만 알면 됨)
	//7-1. 한 페이지에서 보여줄 글의 개수 설정(5개, 변경가능)
	int pageSize = 5; 
	//7-2. 지금 내가 몇페이지에 있는 확인
	//페이지번호는 숫자인데 왜 String으로 하는지 ? => 연산을 할 것이 아니라서 String이 편함
	int pageNum = (int) request.getAttribute("page");
	//7-4. 시작행번호계산
	//10개씩 컬럼 나누고 2페이지에서 시작행이 11이되고 3페이지에서 시작행이 21이 되게끔 만들기
	int currentPage = pageNum; //String을 integer로 변환
	int startRow = (currentPage-1)*pageSize + 1;
	//currentPage가 2인경우, (2-1)x10+1 = 11
	//currentPage가 3인경우, (3-1)x10+1 = 21
	
	//7-5. 끝 행번호계산
	int endRow= currentPage * pageSize;
	//currentPage가 2인경우, 2*10 = 20
	//currentPage가 3인경우, 3*10 = 30
	
	
	//4. 게시판 글의 수를 화면에 데이터 출력
	//게시판 총 글의 수 : cnt개
	//5. getBoardList() 메서드생성
	
	ArrayList boardList = null;
	
	if(cnt != 0){
		//일반적인 리스트호출방법,  아래는 페이징처리한 리스트호출방법
		//7-6. 페이징 처리한 리스트 호출 => getBoardList()메서드만들기(메서드 오버로딩)
		boardList = boardDAO.getBoardList(startRow, pageSize);
} 

%>

<style>
	.boardtable {
		border : 1px solid black;
		border-collapse : collapse;
		text-align : center;
		height : 35px;
	}
	th,td {
		border : 1px solid black;
	}
	.pageBlock {
		text-align: center;
	}
</style>
<body>
	<h1> BOARD TABLE </h1>
	<div>
		<form action="/Board/BoardController?action=search" method="post">
			<select name="search" >
			    <option value="none">=== 검색 ===</option>
			    <option value="agency">기관명</option>
			    <option value="education">교육과정</option>
			  </select> &nbsp
			<input type="text" name="searchbar" placeholder="입력해주세요" /> &nbsp &nbsp
			<button>검색하기</button>
		</form><br/>
	</div>
	<div>
		<table class="boardtable">
				<tr>
					<th>기관명</th>
					<th>교육과정</th>
					<th>직책</th>
					<th>발급자</th>
				</tr>
				<%
					for (int i=0; i<lists.size(); i++) {
				%>
					<tr>
						<td> <%= lists.get(i).getAgency() %> </td>
						<td><a href="/Board/BoardController?action=detail&agency=<%= lists.get(i).getAgency() %>&education=<%= lists.get(i).getEducation() %>"> <%= lists.get(i).getEducation() %> </a></td>
						<td> <%= lists.get(i).getPosition() %> </td>
						<td> <%= lists.get(i).getIssurer() %> </td>
					</tr>				
				<%
					}
				%>
		</table><br/>
	</div>
	<%
	//8. 페이지 이동버튼
	if(cnt != 0){ //cnt는 전체 글 갯수
		//8-1. 페이지갯수처리
		//전체페이지 50개이고 화면에 10개씩 출력 => 5페이지만 있으면됨
		//전체페이지 57개이고 화면에 10개씩 출력 => 6페이지만 있으면됨	
		//삼항연산자로 처리
		int pageCount = cnt/pageSize + (cnt%pageSize == 0? 0:1);
		
		//8-2. 화면에 보여줄 페이지번호의 갯수(페이지블럭)
		int pageBlock = 10; //페이지에 10개 페이지갯수 보여줌
		
		//8-3. 페이지블럭의 시작페이지번호
		//현재 11페이지면, (11-1)/10 * 10 + 1 = 11
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		
		//8-4. 페이지블럭의 끝페이지번호
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		//8-5. 이전, 숫자, 다음처리
		// 이전
		%>
		<div class="pageBlock">
		<%
		if(startPage > pageBlock){
			%>
			<a href="/Board/BoardController?action=home&page=<%=startPage-pageBlock%>">   이전   </a>
			<%
		}
		
		//숫자
		for(int i=startPage; i<=endPage; i++){
			%>
			<a href ="/Board/BoardController?action=home&page=<%=i%>">   <%=i%>   </a>
			<%
		}
		//다음
		if(endPage < pageCount){
			%>
			<a href ="/Board/BoardController?action=home&page=<%=startPage+pageBlock%>">   다음   </a>
			<%
		}
		%>
		</div>
		<%
		
	}
	%>
	<input type="button" value="추가하기" onClick="location.href='view/newboard.jsp'"> &nbsp
</body>
</html>


