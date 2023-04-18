<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="DTO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD DETAIL</title>
</head>
<% String id = String.valueOf(request.getAttribute("board_id")); %>
<% long board_id = Long.parseLong(id);  %>
<style>
	.form-container {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 20px;
	}

	.form-field {
		margin-top: 10px;
	}

	.remove-button {
		margin-top: 10px;
	}
</style>
<body>
	<script>
		function createForm() {
			var form = document.createElement("form"); // 새로운 FORM 요소 생성
			form.setAttribute("method", "post"); // 전송 방식 설정
			form.setAttribute("action", "submit.php"); // 전송 대상 URL 설정
			
			
			// 입력 필드 추가
			var input1 = document.createElement("input");
			input1.setAttribute("type", "text");
			input1.setAttribute("name", "id");
			input1.setAttribute("placeholder", "회원 아이디");
			form.appendChild(input1);
			
			// 합 / 불 select 버튼
			const select = document.createElement("select");
			select.name = "result"; // name 지정
			
			const option1 = document.createElement("option");
			option1.value = "none";  // 실제 입력값
			option1.text = "== 합격 / 불합격 ==";  // 보이는 값
			select.appendChild(option1);
			
			const option2 = document.createElement("option");
			option2.value = "P";  // 실제 입력값
			option2.text = "합격";  // 보이는 값
			select.appendChild(option2);
			
			const option3 = document.createElement("option");
			option3.value = "F";  // 실제 입력값
			option3.text = "불합격";  // 보이는 값
			select.appendChild(option3);
			
			form.appendChild(select);
			
			// 이수 날짜
			const issue_date = document.createElement("input"); // input 태그 생성
			issue_date.type = "date"; // type 속성 지정
			issue_date.name = "issue_date"; // name 지정
			
			form.appendChild(issue_date); // input 태그를 body 요소에 추가

			
			// 버튼 추가
			var button = document.createElement("button");
			button.setAttribute("type", "button");
			button.textContent = "제거";
			button.classList.add("remove-button"); // 클래스 name 지정
			button.onclick = function() {
				removeForm(this);
			};
			form.appendChild(button);
			document.body.appendChild(form); // BODY에 FORM 추가
		}
		
		
		$(document).ready(function() {
			$(".removeFormBtn").click(function() { // 폼태그 제거 버튼이 클릭되었을 때 실행되는 함수입니다.
				$("#myForm").remove(); // 폼태그를 제거합니다.
			});
		});
		function removeForm(button) {
			// 버튼이 속한 폼 컨테이너 제거
			var form = button.parentNode;
			form.parentNode.removeChild(form);
		}
	</script>
	
	<button onclick="createForm()">FORM 생성</button>
	<button onclick="createForm()">FORM DATA 전달</button>
</body>
</html>