<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Calendar</title>
		<!-- css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
	</head>
	<body>
		<a href="">Salir</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Hours</th>
					<th>Sunday</th>
					<th>Monday</th>
					<th>Tuesday</th>
					<th>Wednesday</th>
					<th>Thursday</th>
					<th>Friday</th>
					<th>Saturday</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" end="23" varStatus="i">
					 <tr>
						<td rowspan="2">${i.index == '0'? '12' : i.index}${i.index < '12'? 'am' : 'pm'}</td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
					</tr>
					<tr>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
						<td><div><ul class="list-group"></ul></div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href='<c:url value="/newEvent.htm?event=meeting" />'>Crear reunion</a>
		<a href='<c:url value="/newEvent.htm?event=privateEvent" />'>Crear evento privado</a>
	</body> 
</html>