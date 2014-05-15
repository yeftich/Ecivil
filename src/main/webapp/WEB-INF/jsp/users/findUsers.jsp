<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//GR"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />

			<h2>Find Users</h2>


			<a href='<spring:url value="/users/new" htmlEscape="true"/>'
				class="btn btn-success">Add User</a> <br />

			<spring:url value="/users.html" var="formUrl" />
			<form:form modelAttribute="user" action="${fn:escapeXml(formUrl)}"
				method="get" class="form-horizontal" id="search-user-form">
				<fieldset>
					<div class="control-group" id="name">
						<label class="control-label">User name </label>
						<form:input path="name" size="30" maxlength="80" />
						<span class="help-inline"><form:errors path="*" /></span>
					</div>
					<div class="form-actions">
						<button type="submit">Find user</button>
					</div>
				</fieldset>
			</form:form>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
