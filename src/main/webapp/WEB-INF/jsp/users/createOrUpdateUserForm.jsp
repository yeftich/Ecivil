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
				<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>
				<table>
					<tr>
						<td>Login:</td>
						<td><form:input path="login" /></td>
						<td><form:errors path="login" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:password path="password" /></td>
						<td><form:errors path="password" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Google account:</td>
						<td><form:input path="googleAccount" /></td>
						<td><form:errors path="googleAccount" cssClass="error" /></td>
					</tr>
					<tr>
						<td>First Name:</td>
						<td><form:input path="firstName" /></td>
						<td><form:errors path="firstName" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><form:input path="lastName" /></td>
						<td><form:errors path="lastName" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Address:</td>
						<td><form:input path="address" /></td>
						<td><form:errors path="address" cssClass="error" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><form:input path="city" /></td>
						<td><form:errors path="city" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Telephone:</td>
						<td><form:input path="telephone" /></td>
						<td><form:errors path="telephone" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Teams :</td>
						<td><form:select path="userTeams" items="${teamList}"
								multiple="true" /></td>
						<td><form:errors path="userTeams" cssClass="error" /></td>
					</tr>
				</table>


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
