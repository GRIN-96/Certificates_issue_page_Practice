<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기</title>
</head>
<body>
	<h1>아이디 찾기</h1>
	<form action="/Board/UserController?action=userinfo_id" method="post">
		이름 : <input type="text" name="name" placeholder="이름을 입력해주세요" /> <br/>
		생년월일 : <input type="date" name="bday" /><br/>
		이메일 : <input type="text" name="email" placeholder="e-mail 주소를 입력해주세요" /> <br/><br/>
		<button>전송</button>
	</form><br/>
	<br/>
</body>
</html>