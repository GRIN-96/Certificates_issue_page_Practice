<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="DTO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD DETAIL</title>
</head>
<style>
	.form-container {
		display: flex;
		flex-direction: column;
		align-items: start;
		margin-top: 20px;
	}

	.form-field {
		margin-top: 10px;
	}

	.remove-button {
		margin-top: 10px;
	}
	.submit-button {
		margin-bottom: 30px;
	}
</style>
<body>
	<a href="/Board/CompleteController?action=test"> 이도오옹 </a>
	<button onclick="createForm()">FORM 생성</button>
	<div id = "form-containers" >
	<form action="/Board/CompleteController?action=test1" method="post">
		<input type="text" name="id" placeholder="회원 아이디" id="0" class="form-field">
		<select name="result" id="0" class="form-field">
			<option value="none">== 합격 / 불합격 ==</option>
			<option value="P">합격</option>
			<option value="F">불합격</option>
		</select>
		<input type="date" name="issue_date" id="0" class="form-field"/>
		<button class="submit-button" id="0">FORM 제출하기</button>
	</form>
	<script>
	
		let numForms = 0; // 생성된 form 요소의 개수
		
		var form = document.createElement("form"); // 새로운 FORM 요소 생성
		form.setAttribute("action", "/Board/CompleteController?action=test")
		form.setAttribute("method", "post");  // 전송 방식 설정
		form.id = numForms ; // id 속성 값 설정
		form.classList.add("form-container"); // 클래스 name 지정
		
		
		// 버튼 추가
		var button2 = document.createElement("button");
		button2.textContent = "FORM 제출하기";
		button2.classList.add("submit-button"); // 클래스 name 지정
		button2.id = numForms; // id 속성 값 설정
		form.appendChild(button2);
		
		
		// 입력 필드 추가
		var input1 = document.createElement("input");
		input1.setAttribute("type", "text");
		input1.setAttribute("name", "id");
		input1.setAttribute("placeholder", "회원 아이디");
		input1.id = numForms; // id 속성 값 설정
		input1.classList.add("form-field"); // 클래스 name 지정
		form.appendChild(input1);
		
		// 합 / 불 select 버튼
		const select = document.createElement("select");
		select.name = "result"; // name 지정
		select.id = numForms; // id 속성 값 설정
		select.classList.add("form-field");  // 클래스 name 지정
		
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
		issue_date.id = numForms; // id 속성 값 설정
		issue_date.classList.add("form-field");  // 클래스 name 지정
		
		form.appendChild(issue_date); // input 태그를 body 요소에 추가

		
		// 버튼 추가
		var button = document.createElement("button");
		button.setAttribute("type", "button");
		button.textContent = "제거";
		button.classList.add("remove-button"); // 클래스 name 지정
		button.id = numForms; // id 속성 값 설정
		button.onclick = function() {
			removeForm(this);
		};
		form.appendChild(button);
		document.body.appendChild(form); // body에 FORM 추가
		
		
		
		
		function createForm() {
			
		
			numForms++; // 생성된 form 요소 개수 증가
			
			// 입력 필드 추가
			var input1 = document.createElement("input");
			input1.setAttribute("type", "text");
			input1.setAttribute("name", "id");
			input1.setAttribute("placeholder", "회원 아이디");
			input1.id = numForms; // id 속성 값 설정
			input1.classList.add("form-field");  // 클래스 name 지정
			form.appendChild(input1);
			
			// 합 / 불 select 버튼
			const select = document.createElement("select");
			select.name = "result"; // name 지정
			select.id = numForms; // id 속성 값 설정
			select.classList.add("form-field");  // 클래스 name 지정
			
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
			issue_date.id = numForms ; // id 속성 값 설정
			issue_date.classList.add("form-field"); // 클래스 name 지정
			
			form.appendChild(issue_date); // input 태그를 body 요소에 추가

			
			// 버튼 추가
			var button = document.createElement("button");
			button.setAttribute("type", "button");
			button.textContent = "제거";
			button.classList.add("remove-button"); // 클래스 name 지정
			button.id = numForms; // id 속성 값 설정
			button.onclick = function() {
				removeForm(numForms);
			}; 
			form.appendChild(button);
		}
		
		
		function removeForm(numForms) {
			
			var elementsToRemove = document.querySelectorAll("#"+numForms);
			// 버튼이 속한 폼 컨테이너 제거
			for (var i = 0; i < elementsToRemove.length; i++) {
			    var element = elementsToRemove[i];
			    element.parentNode.removeChild(element);
			  }
		}
		
	</script>
	<br/>
	</div>
</body>
</html>