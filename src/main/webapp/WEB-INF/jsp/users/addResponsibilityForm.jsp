<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ecivil" tagdir="/WEB-INF/tags"%>

<jsp:include page="../fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />


			<h2>Add Responsibility</h2>

			<spring:url value="/users/responsibility/add.html" var="formUrl" />
			<form:form modelAttribute="aUserTeam" action="${fn:escapeXml(formUrl)}"
			method="POST" class="form-horizontal" id="add-responsibility-form">
				<table>
					<tr>
						<td>Login:</td>
						<td>${aUserTeam.user.login}</td>
					</tr>
					<tr>
						<td>Team:</td>
						<td>${aUserTeam.team.name}</td>
					</tr>
					<tr>
						<td>Status:</td>
						<td>${aUserTeam.status}</td>
					</tr>
					<tr>
						<td>Responsibility:</td>
						<td><form:input path="responsibility" /></td>
						<td><form:errors path="responsibility" cssClass="error" /></td>
					</tr>
				</table>

				<div class="form-actions">
					<button type="submit">Update</button>
				</div>

			</form:form>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>