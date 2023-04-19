<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE PAGE</title>
</head>
<body>
	<h1>회원탈퇴 페이지입니다.</h1>
	<br/><br/>
	<form action="/Board/UserController?action=user_delete" method="post">
		<input type="text" name="id" placeholder="아이디 입력" /> <br/>
		<input type="text" name="pw" placeholder="패스워드 입력" /> <br/><br/>
		<button>탈퇴</button>
	</form><br/> 
	<br/><br/>
	<input type="button" value="홈으로이동" onClick="location.href='../view/home.jsp'"> 
</body>
</html>