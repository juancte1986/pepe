<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>Private event</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datetimepicker.css"type="text/css" />
		<!-- js -->
		<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.3.js"></script>
		<script src="${pageContext.request.contextPath}/js/lib/jquery-ui.js"></script>
		<script src="${pageContext.request.contextPath}/js/lib/jquery.datetimepicker.full.js"></script>
		<script src="${pageContext.request.contextPath}/js/widgets/jquery.event.js"></script>
		<script>
			$(function() {
				$("#content").applyEvent({
					urlContext : '${pageContext.request.contextPath}',
					isPrivateEvent : true
					
				});
			});
		</script>
	</head>
	<body>
		<div id="content">
			<form:form commandName="formPrivateEvent" action='${pageContext.request.contextPath}/savePrivateEvent.htm' method="POST">
				<!--nombre del evento -->
				<form:label path="name">
					<fmt:message key="label.name" />
				</form:label>
				<form:input path="name"/>
				<form:errors path="name" cssStyle="color: red" />
				<br/>
				<!--Hora de inicio -->
				<form:label path="startTime">
					<fmt:message key="label.startTime" />
				</form:label>
				<form:input id="startTimepicker" path="startTime"/>
				<form:errors path="startTime" cssStyle="color: red" />
				<br/>
				<!--Hora de finalizacion-->
				<form:label path="endTime">
					<fmt:message key="label.endTime" />
				</form:label>
				<form:input id="endTimepicker" path="endTime"/>
				<form:errors path="endTime" cssStyle="color: red" />
				<br/>
				<!--Descripcion -->
				<form:label path="description">
					<fmt:message key="label.description" />
				</form:label>
				<form:input path="description"/>
				<br/>
				<!--Direccion -->
				<form:label path="address">
					<fmt:message key="label.address" />
				</form:label>
				<form:input path="address"/>
				<br/>
				<form:button><fmt:message key="label.save"/></form:button>
			</form:form>
		</div>
	</body>
</html>