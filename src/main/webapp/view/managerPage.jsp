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
	BoardDAO boardDAO = new BoardDAO();
	ArrayList<BoardDTO> lists = (ArrayList<BoardDTO>) request.getAttribute("lists");
	int cnt = boardDAO.getListCount();
	
	// 페이징
	int pageSize = 5; 
	int currentPage = (int) request.getAttribute("page");
	int startRow = (currentPage-1)*pageSize + 1;
	int endRow= currentPage * pageSize;

	ArrayList boardList = null;
	
	if(cnt != 0){
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
						<td><a href="/Board/BoardController?action=detail&board_id=<%= lists.get(i).getBoard_id() %>" > <%= lists.get(i).getEducation() %> </a></td>
						<td> <%= lists.get(i).getPosition() %> </td>
						<td> <%= lists.get(i).getIssurer() %> </td>
					</tr>				
				<%
					}
				%>
		</table><br/>
	</div>
	<%
	if(cnt != 0){ //cnt는 전체 글 갯수
		int pageCount = cnt/pageSize + (cnt%pageSize == 0? 0:1);
		
		int pageBlock = 10; //페이지에 10개 페이지갯수 보여줌
		
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
		
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
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
	<input type="button" value="추가하기" onClick="location.href='view/newboard.jsp'"> &nbsp &nbsp
	<input type="button" value="홈으로이동" onClick="location.href='view/home.jsp'"> 
</body>
</html>


