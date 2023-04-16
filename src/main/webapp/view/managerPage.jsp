<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="DTO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Page</title>
</head>

<% ArrayList<BoardDTO> lists = (ArrayList<BoardDTO>) request.getAttribute("lists"); %>
<body>
	<h1> BOARD TABLE </h1>
	<br/><br/>
	<div>
		<table>
			<thead>
				<tr style="text-align : center;">
					<th>agency</th>
					<th>education</th>
					<th>content</th>
					<th>position</th>
					<th>issurer</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i=0; i<lists.size(); i++) {
				%>
					<tr>
						<td><% lists.get(i).getAgency(); %></td>
						<td><% lists.get(i).getEducation(); %></td>
						<td><% lists.get(i).getContent(); %></td>
						<td><% lists.get(i).getPosition(); %></td>
						<td><% lists.get(i).getIssurer(); %></td>
					</tr>				
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>