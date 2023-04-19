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

<style>
	.certificates {
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>

	<div class="certificates">
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
	
	<script language = "javascript">
	let doc = new jsPDF('p','pt','a4');
	 
	 
	doc.addHTML(document.body,function() {
	    doc.save('html.pdf');
	});
	 
	</script>
	
</body>
</html>