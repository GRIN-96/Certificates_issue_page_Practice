<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.*" %>
<%@ page import="DAO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이수증 PDF 발급</title>
</head>
<% CertificatesDTO certificatesDTO = (CertificatesDTO) request.getAttribute("certificatesDTO"); %>
<% String b_id = request.getParameter("board_id"); %>
<% int board_id = Integer.parseInt(b_id); %>
<style>
	#certificates {
		width : 600px;
		height : 900px;
		border : 1px solid black;
		padding : 75px 113px 57px 113px; 
		text-align : center;
		font-weight: bold;
		font-size : 24px;
	}
	.agency {
		text-align : start; 
		letter-spacing : 3px;
		font-size : 15px;
	}
	.education {
		text-align : start; 
		font-size : 15px;
	}
	.title {
		font-size : 60px;
	}
	.name {
		letter-spacing : 30px;
		text-align : end;
		font-size : 23px;
		line-height : 160%;
	}
	.birthday {
		text-align : end;
		font-size : 23px;
		line-height : 160%;
	}
	.content {
		text-align : start; 
		line-height : 250%;
		letter-spacing : 2px;
	}
	.issued{
		position : relative;
	}
	.position {
		font-size : 27px;
		letter-spacing : 3px;
		display: inline-block;
		position : relative;
	}
	.issurer {
		letter-spacing : 12px;
		font-size : 32px;
		display: inline-block;
		position : relative;
		z-index: 2;
	}
	.image {
		position : absolute;
		top : -30px;
		left : 470px;
		z-index: 1;
	}
</style>
<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
	<div id="certificates">
		<div class="agency">
			<a><%= certificatesDTO.getAgency() %></a><br/>
		</div>
		<div class="education">
			<a><%= certificatesDTO.getEducation() %></a><br/>
		</div><br/><br/>
		<div class="title">
			<a>이 수 증</a>
		</div><br/><br/>
		<div class="name">
			<a><%= certificatesDTO.getUser_name() %></a>
		</div>
		<div class="birthday">
			<a><%= request.getAttribute("u_birthday") %></a><br/>
		</div><br/><br/>
		<div class="content">
			&nbsp;&nbsp;<a><%= certificatesDTO.getContent() %></a>
		</div><br/><br/>
		<div class="issueDate">
			<a><%= request.getAttribute("b_issue") %></a><br/>
		</div><br/><br/>
		<div class="issued">
			<div class="position">
				<a><%= certificatesDTO.getPosition() %></a>
			</div>
			<div class="issurer">
				<a><%= certificatesDTO.getIssurer() %></a>
			</div>
			<img class="image" style="height:100px; width:100px;" src="img/도장1.png"></img>
		</div>
	</div>
	<script>
	
	function screenShot() {
	html2canvas(document.querySelector("#certificates")).then(function(canvas) {
        // Convert canvas to Blob object
        canvas.toBlob(function(blob) {
          // Create FormData object and append Blob object
          var formData = new FormData();
          formData.append('pdf', blob);
          console.log(blob)

          // Send AJAX request to server
          $.ajax({
            url: 'CompleteController?action=pdf_DL',
            method: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
              console.log(response);
            },
            error: function(xhr, status, error) {
              console.log(xhr.responseText);
            }
          });
        }, 'application/pdf');
		})	
	}
	
	
	// 페이지가 로드되면 convertAndRender 함수 호출
    window.onload = function() {
    	screenShot();
    };
    
    
    // viewer로 저장된 pdf 열어보기.
    //location.href = 'pdfjs/web/viewer.html?file=/file/html.pdf';
	
  	</script>
	
</body>
</html>