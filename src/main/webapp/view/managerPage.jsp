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
<% ArrayList<BoardDTO> lists = (ArrayList<BoardDTO>) request.getAttribute("lists"); %>
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
</style>
<body>
	<h1> BOARD TABLE </h1>
	<div>
		<table class="boardtable">
				<tr>
					<th>agency</th>
					<th>education</th>
					<th>position</th>
					<th>issurer</th>
				</tr>
				<%
					for (int i=0; i<lists.size(); i++) {
				%>
					<tr>
						<td> <%= lists.get(i).getAgency() %> </td>
						<td><a href="/Board/BoardController?action=detail&agency=<%= lists.get(i).getAgency() %>&education=<%= lists.get(i).getEducation() %>"> <%= lists.get(i).getEducation() %> </a></td>
						<td> <%= lists.get(i).getPosition() %> </td>
						<td> <%= lists.get(i).getIssurer() %> </td>
					</tr>				
				<%
					}
				%>
		</table><br/>
		<input type="button" value="추가하기" onClick="location.href='view/newboard.jsp'"> &nbsp
	</div>
</body>
</html>


