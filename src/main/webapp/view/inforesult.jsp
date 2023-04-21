<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE PAGE</title>
</head>
<% String user_id = (String) request.getAttribute("user_id"); %>
<body>
	<h1>아이디 입니다.</h1>
	<br/><br/>
	<h3><%= request.getAttribute("user_id") %></h3>
	<br/><br/>
	<input type="button" value="로그인페이지" onClick="location.href='/Board/'"> 
</body>
</html>