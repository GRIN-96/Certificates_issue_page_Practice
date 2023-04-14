<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Change</title>
</head>
<body>
	<h1>계정 비밀번호 변경 페이지입니다.</h1>
	<br/><br/>
	<form action="/Board/UserController?action=pw_change" method="post">
		<input type="text" name="id" placeholder="아이디 입력" /> <br/>
		<input type="text" name="pw" placeholder="기존 패스워드 입력" /> <br/><br/> 
		<input type="text" name="new_pw" placeholder="새로운 패스워드 입력" /> <br/><br/> 
		<button>변경</button>
	</form><br/> 
</body>
</html>