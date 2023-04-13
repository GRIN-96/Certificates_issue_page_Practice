<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<style>
	
</style>
<body>
	<h1>로그인 페이지입니다.</h1>
	<br/><br/>
	<form action="/Board/BoardController" method="post">
		<input type="text" name="id" placeholder="아이디를 입력해주세요" /> <br/>
		<input type="text" name="pw" placeholder="패스워드를 입력해주세요" /> <br/><br/> 
		<button>전송</button>
	</form><br/>
	<a href="/Board/view/joinpage.jsp"> 회원가입하기</a> &nbsp
	<a href="/Board/view/userinfo.jsp"> 아이디찾기 </a>	
</body>
</html>