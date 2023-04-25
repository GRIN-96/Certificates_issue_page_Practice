<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="DTO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD DETAIL</title>
</head>
<% BoardDTO board = (BoardDTO) request.getAttribute("board"); %>
<% ArrayList<UserListDTO> userList = (ArrayList<UserListDTO>) request.getAttribute("userList"); %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	.boardtable {
		border : 1px solid black;
		border-collapse : collapse;
		text-align : center;
		height : 35px;
	}
	th {
		border : 1px solid black;
		width : 100px;
	}
	td {
		border : 1px solid black;
		width : 500px;
	}
	
</style>
<body>
	<h1> BOARD DETAIL </h1>
	<div>
		<table class="boardtable">
				<tr>
					<th scope="col">기관명</th>
					<td><%= board.getAgency() %></td>
				</tr>
				<tr>
					<th scope="col">교육과정</th>
					<td><%= board.getEducation() %></td>
				</tr>
				<tr>
					<th scope="col">내용</th>
					<td><%= board.getContent() %></td>
				</tr>
				<tr>
					<th scope="col">직책</th>
					<td><%= board.getPosition() %></td>
				</tr>
				<tr>
					<th scope="col">발급자</th>
					<td><%= board.getIssurer() %></td>
				</tr>
		</table><br/>
		<input type="button" value="목록으로" onClick="location.href='BoardController?action=home'"> &nbsp
		<input type="button" value="수정하기" onClick="location.href='BoardController?action=edit&board_id=<%= board.getBoard_id()%>'"> &nbsp
		<input type="button" value="삭제하기" onClick="location.href='BoardController?action=delete&id=<%= board.getBoard_id() %>'"> <br/>
	</div>
	<br/><br/>
	<div>
		<input type="button" value="이수자 목록 등록하기" onClick="location.href='BoardController?action=insert_complete&id=<%= board.getBoard_id() %>'"> &nbsp
		<input type="button" value="전체삭제" onClick="location.href='#'">
	</div>
	<br/><br/>
	<div>
		<table class="boardtable">
				<tr>
					<th>회원 아이디</th>
					<th>성함</th>
					<th>이수 여부</th>
					<th>이수 날짜</th>
				</tr>
				<%
					for (int i=0; i<userList.size(); i++) {
				%>
					<tr>
						<td> <%= userList.get(i).getUser_id() %> </td>
						<td> <%= userList.get(i).getUser_name() %> </td>
						<td> <%= "P".equals(userList.get(i).getPass_fail()) ? "합격" : "불합격" %>  </td>
						<td> <%= userList.get(i).getIssue_date() %> </td>
						<td> <button onclick="location.href='/Board/CompleteController?action=delete&com_id=<%= userList.get(i).getComplete_id() %>&board_id=<%= board.getBoard_id() %>'">삭제</button> </td>
					</tr>				
				<%
					}
				%>
		</table><br/>
	</div>
	
</body>
</html>