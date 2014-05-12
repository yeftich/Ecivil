<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//GR"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ecivil" tagdir="/WEB-INF/tags"%>


<html lang="el">

<jsp:include page="../fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<c:choose>
				<c:when test="${user['new']}">
					<c:set var="method" value="post" />
				</c:when>
				<c:otherwise>
					<c:set var="method" value="put" />
				</c:otherwise>
			</c:choose>

			<h2>
				<c:if test="${user['new']}">New </c:if>
				User
			</h2>
			<form:form modelAttribute="user" method="${method}"
				class="form-horizontal" id="add-user-form">
				<ecivil:inputField label="Login" name="login" />
				<ecivil:passwordField label="Password" name="password" />
				<ecivil:inputField label="Google account" name="googleAccount" />
				<ecivil:inputField label="First Name" name="firstName" />
				<ecivil:inputField label="Last Name" name="lastName" />
				<ecivil:inputField label="Address" name="address" />
				<ecivil:inputField label="City" name="city" />
				<ecivil:inputField label="Telephone" name="telephone" />

				<div class="form-actions">
					<c:choose>
						<c:when test="${user['new']}">
							<button type="submit">Add user</button>
						</c:when>
						<c:otherwise>
							<button type="submit">Update user</button>
						</c:otherwise>
					</c:choose>
				</div>
			</form:form>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
