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
</style>
<body>
	<button onclick="createForm()">FORM 생성</button>
	<button onclick="submitForms()">FORM DATA 전달</button>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<div id = "form-containers" >
	<script>
	
		var formContainer = document.getElementById("form-containers");
	
		let numForms = 0; // 생성된 form 요소의 개수
		
		var form = document.createElement("form"); // 새로운 FORM 요소 생성
		form.setAttribute("method", "post"); // 전송 방식 설정
		form.setAttribute("action", "CompleteController?action=test")
		form.id = numForms ; // id 속성 값 설정
		form.classList.add("form-container"); // 클래스 name 지정
		
		
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
		button.classList.add("remove-button");  // 클래스 name 지정
		
		button.onclick = function() {
			removeForm(this);
		};
		form.appendChild(button);
		document.getElementById("form-containers").appendChild(form); // DIV에 FORM 추가
		
		
		
		
		
		function createForm() {
			
		
			numForms++; // 생성된 form 요소 개수 증가
			
			
			// form 생성
			var form = document.createElement("form"); // 새로운 FORM 요소 생성
			form.setAttribute("method", "post"); // 전송 방식 설정
			form.setAttribute("action", "CompleteController?action=test")
			form.id = numForms ; // id 속성 값 설정
			form.classList.add("form-container"); // 클래스 name 지정
			
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
				removeForm(this);
			}; 
			form.appendChild(button);
			document.getElementById("form-containers").appendChild(form); // DIV에 FORM 추가
		}
		
		
		
		$(document).ready(function() {
			$("#remove-button").click(function() { // 폼태그 제거 버튼이 클릭되었을 때 실행되는 함수입니다.
				$("#myForm").remove(); // 폼태그를 제거합니다.
			});
		});
		function removeForm(button) {
			// 버튼이 속한 폼 컨테이너 제거
			var form = button.parentNode;
			form.parentNode.removeChild(form);;
		}
		
	</script>
	
	<script>
	function submitForms() {
	  var formContainer = document.getElementById("form-containers");
	  var forms = formContainer.getElementsByTagName("form");  // form 태그 수
	
	  var formData = new FormData();
	
	  for (var i = 0; i < forms.length; i++) {
	    var form = forms[i];
	    var formElements = form.elements;
	
	    for (var j = 0; j < formElements.length; j++) {
	      var element = formElements[j];
	      var name = element.getAttribute("name");
	      var value = element.value;
	
	      formData.append(name, value);
	    }
	  }
	
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "/Board/CompleteController?action=test");
	  xhr.onload = function() {
	    if (xhr.status === 200) {
	      console.log(xhr.response);
	    } else {
	      console.error(xhr.statusText);
	    }
	  };
	  xhr.send(formData);
	}
	</script>
	<br/>
	</div>
</body>
</html>