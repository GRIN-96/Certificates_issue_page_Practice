<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<h1>회원가입 페이지입니다.</h1>
	<form action="/Board/UserController?action=join" method="post">
		<input type="text" name="id" placeholder="아이디를 입력해주세요" /> <br/>
		<input type="text" name="pw" placeholder="패스워드를 입력해주세요" /> <br/>
		<input type="text" name="name" placeholder="이름을 입력해주세요" /> <br/>
		<select name="gender">
		    <option value="">성별</option>
		    <option value="M">남성</option>
		    <option value="W">여성</option>
		</select>
		<input type="date" name="bday" /><br/>
		<input type="text" name="email" placeholder="e-mail 주소를 입력해주세요" /> <br/><br/>
		<button>전송</button>
	</form><br/>
	<br/>
</body>
</html>