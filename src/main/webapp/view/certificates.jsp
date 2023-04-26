<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="DTO.*" %>
<%@ page import="DAO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Page</title>
</head>
<% ArrayList<CertificatesDTO> mylist = (ArrayList<CertificatesDTO>) request.getAttribute("mylist"); %>
<style>
	.boardtable {
		border : 1px solid black;
		border-collapse : collapse;
		text-align : center;
		height : 35px;
	}
	th,td {
		border : 1px solid black;
	}
	.pageBlock {
		text-align: center;
	}
</style>
<body>
	<h1> 나의 수료증 보기 </h1>
	
		<table class="boardtable">
				<tr>
					<th>기관명</th>
					<th>교육과정</th>
					<th>성함</th>
					<th>이수 날짜</th>
				</tr>
				<%
					for (int i=0; i<mylist.size(); i++) {
						if (mylist.get(i).getPass_fail().equals("P")) {
				%>
					<tr>
						<td> <%= mylist.get(i).getAgency() %> </td>
						<td> <%= mylist.get(i).getEducation() %> </td>
						<td> <%= mylist.get(i).getUser_name() %> </td>
						<td> <%= mylist.get(i).getIssue_date() %> </td>
						<td> <button onClick="location.href='pdfjs/web/viewer.html?file=/pdf/<%= mylist.get(i).getPdf_url() %>'">보기</button> </td>
					</tr>	
				<%
						}}
				%>
		</table><br/>
	</div>
	<input type="button" value="홈으로이동" onClick="location.href='view/home.jsp'"> 
</body>
</html>


