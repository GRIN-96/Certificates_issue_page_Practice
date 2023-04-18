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
		<input type="button" value="목록으로" onclick="history.back();"> &nbsp
		<input type="button" value="수정하기" onClick="location.href='BoardController?action=edit&agency=<%= board.getAgency() %>&education=<%= board.getEducation() %>'"> &nbsp
		<input type="button" value="삭제하기" onClick="location.href='BoardController?action=delete&id=<%= board.getBoard_id() %>'"> <br/>
	</div>
	<br/><br/>
	<div>
		<input type="button" value="이수자 목록 등록하기" href="#"> &nbsp
	</div>
</body>
</html>