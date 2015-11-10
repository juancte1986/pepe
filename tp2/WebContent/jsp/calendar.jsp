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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.3.js"></script>
		<script src="${pageContext.request.contextPath}/js/lib/jquery-ui.js"></script>
		<script src="${pageContext.request.contextPath}/js/widgets/jquery.calendar.js"></script>
		<script>
			$(function() {
				$("#content").applyCalendar({
					urlContext : '${pageContext.request.contextPath}'
				});
			});
		</script>
	</head>
	<body>
		<a href="">Salir</a>
		<div id="content">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Hours</th>
						<th id="sundayId">Sunday</th>
						<th id="mondayId">Monday</th>
						<th id="tuesdayId">Tuesday</th>
						<th id="wednesdayId">Wednesday</th>
						<th id="thursdayId">Thursday</th>
						<th id="fridayId">Friday</th>
						<th id="saturdayId">Saturday</th>
					</tr>
				</thead>
				<tbody>
					 <tr>
						<td><div class="column" id="columnSunday"></div></td>
						<td><div class="column" id="columnMonday"></div></td>
						<td><div class="column" id="columnTuesday"></div></td>
						<td><div class="column" id="columnWednesday"></div></td>
						<td><div class="column" id="columnThursday"></div></td>
						<td><div class="column" id="columnFriday"></div></td>
						<td><div class="column" id="columnSaturday"></div></td>
					</tr>
				</tbody>
			</table>
		</div>
		<button id="next">Siguiente</button>
		<button id="previous">Anterior</button>
		<a href='<c:url value="/newMeeting.htm" />'>Crear reunion</a>
		<a href='<c:url value="/newPrivateEvent.htm" />'>Crear evento privado</a>
	</body> 
</html>