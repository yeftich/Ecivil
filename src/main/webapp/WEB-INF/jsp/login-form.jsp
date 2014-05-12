<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//GR"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="el">

<jsp:include page="fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="fragments/header.jsp" />
		<div class="container">
			<jsp:include page="fragments/navBar.jsp" />

			<h1>
				<fmt:message key="page.login-form.title" />
			</h1>

			<p>
				<c:if test="${error == true}">
					<b class="error"><fmt:message
							key="page.login-form.invalid.login" /></b>
				</c:if>
			</p>

			<form method="post" action="<c:url value='j_spring_security_check'/>">
				<table>
					<tbody>
						<tr>
							<td><fmt:message key="login-form.form.label.userLogin" /></td>
							<td><input type="text" name="j_username" id="j_username"
								size="30" maxlength="40" /></td>
						</tr>
						<tr>
							<td><fmt:message key="login-form.form.label.password" /></td>
							<td><input type="password" name="j_password" id="j_password"
								size="30" maxlength="32" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit"
								value="<fmt:message key="login-form.form.button.submit" />" /></td>
						</tr>
					</tbody>
				</table>
			</form>

			<%-- 			<p>
				<a href="${pageContext.request.contextPath}/index.html">Home
					page</a><br />
			</p>
 --%>
		</div>
	</div>
</body>
</html>