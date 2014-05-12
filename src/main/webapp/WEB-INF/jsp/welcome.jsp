<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//GR"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="el">

<jsp:include page="fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="fragments/header.jsp" />
		<div class="container">
			<jsp:include page="fragments/navBar.jsp" />
			<h2>
				<fmt:message key="welcome" />
			</h2>

			<spring:url value="/resources/images/pets.png" htmlEscape="true"
				var="petsImage" />
			<img src="${petsImage}" />

			<jsp:include page="fragments/footer.jsp" />
		</div>
	</div>
</body>

</html>
