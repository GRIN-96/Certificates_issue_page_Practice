<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Insert</title>
</head>
<body>
	<h1>Board Insert</h1>
	<br/><br/>
	<form action="/Board/BoardController?action=new_board" method="post">
		<input type="text" name="agency" placeholder="agency" /> <br/>
		<input type="text" name="education" placeholder="education" /> <br/>
		<textarea name="content" placeholder="content" rows="7" cols="30" ></textarea> <br/>
		<input type="text" name="position" placeholder="position" /> <br/>
		<input type="text" name="issurer" placeholder="issurer" /> <br/>
		<button>추가하기</button>
	</form><br/> 
</body>
</html>