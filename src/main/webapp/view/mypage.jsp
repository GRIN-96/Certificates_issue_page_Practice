<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
	<h1>마이페이지 입니다.</h1>
	<br/><br/>
	<a href="/Board/view/pwChange.jsp">비밀번호변경</a> 
	<br/><br/>
	<a href="/Board/view/userDelete.jsp">회원탈퇴</a> 
	<br/><br/>
	<a href="/Board/CompleteController?action=myList">이수증발급</a>
	<br/><br/>
	<input type="button" value="홈으로이동" onClick="location.href='../view/home.jsp'"> 
</body>
</html>