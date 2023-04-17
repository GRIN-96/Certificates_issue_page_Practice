<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board UPDATE</title>
</head>
<% BoardDTO board = (BoardDTO) request.getAttribute("board"); %>
<body>
	<h1>Board UPDATE</h1>
	<form action="/Board/BoardController?action=edit_board&id=<%= board.getBoard_id() %>" method="post">
		<input type="text" name="agency" value=<%= board.getAgency() %> placeholder="agency" /> <br/>
		<input type="text" name="education" value=<%= board.getEducation() %> placeholder="education" /> <br/>
		<textarea name="content" placeholder="content" rows="7" cols="30" > <%= board.getContent() %> </textarea> <br/>
		<input type="text" name="position" value=<%= board.getPosition() %> placeholder="position" /> <br/>
		<input type="text" name="issurer" value=<%= board.getIssurer() %> placeholder="issurer" /> <br/>
		<button>수정하기</button>
	</form><br/> 
</body>
</html>