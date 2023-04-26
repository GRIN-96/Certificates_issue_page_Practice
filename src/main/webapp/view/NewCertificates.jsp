<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.*" %>
<%@ page import="DAO.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이수증 PDF 발급</title>
</head>
<% // 포멧 생성
	SimpleDateFormat b_d = new SimpleDateFormat("yyyy년 MM월 dd일생");
	SimpleDateFormat i_d = new SimpleDateFormat("yyyy년 MM월 dd일");  %>
<% ArrayList<CertificatesDTO> certificates = (ArrayList<CertificatesDTO>) request.getAttribute("certificates"); %>
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
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
	<% for (int i=0; i < certificates.size(); i++) { %>
	<style>
		#certificates<%= i %> {
		width : 600px;
		height : 900px;
		border : 1px solid black;
		padding : 75px 113px 57px 113px; 
		text-align : center;
		font-weight: bold;
		font-size : 24px;
	}
	</style>
	<div id="certificates<%= i %>">
		<div class="agency">
			<a><%= certificates.get(i).getAgency() %></a><br/>
		</div>
		<div class="education">
			<a><%= certificates.get(i).getEducation() %></a><br/>
		</div><br/><br/>
		<div class="title">
			<a>이 수 증</a>
		</div><br/><br/>
		<div class="name">
			<a><%= certificates.get(i).getUser_name() %></a>
		</div>
		<div class="birthday">
			<a> <%= b_d.format(certificates.get(i).getUser_birthday()) %></a><br/>
		</div><br/><br/>
		<div class="content">
			&nbsp;&nbsp;<a><%= certificates.get(i).getContent() %></a>
		</div><br/><br/>
		<div class="issueDate">
			<a><%= i_d.format(certificates.get(i).getIssue_date()) %></a><br/>
		</div><br/><br/>
		<div class="issued">
			<div class="position">
				<a><%= certificates.get(i).getPosition() %></a>
			</div>
			<div class="issurer">
				<a><%= certificates.get(i).getIssurer() %></a>
			</div>
			<img class="image" style="height:100px; width:100px;" src="img/도장1.png"></img>
		</div>
	</div>
	<script>
	
	
	
	
	html2canvas(document.querySelector("#certificates"+ <%= i %>)).then(canvas => {
		let dataURL = canvas.toDataURL("image/png");
		
		$.ajax({
		    type: "POST",
		    url: "CompleteController?action=pdf_DL",
		    data: { "imgurl" : dataURL, "name" : "<%= certificates.get(i).getUser_name() %>" ,
		    	"c_id" : "<%= certificates.get(i).getComplete_id() %>"} ,
		    success: function(response) {
		        console.log(response);
		    },
		    error: function(xhr, status, error) {
		        console.log(xhr.responseText);
		    }
		});
		});
	
	
	
	
    
    // viewer로 저장된 pdf 열어보기.
    //location.href = 'pdfjs/web/viewer.html?file=/file/html.pdf';
	
  	</script>
	<% } %>
</body>
</html>