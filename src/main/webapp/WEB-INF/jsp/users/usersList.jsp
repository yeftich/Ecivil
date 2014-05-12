<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//GR"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<h2>Users</h2>

			<datatables:table id="users" data="${selections}" cdn="true"
				row="user" theme="bootstrap2" cssClass="table table-striped"
				paginate="false" info="false">
				<datatables:column title="Login" property="login" />
				<datatables:column title="Name" cssStyle="width: 150px;"
					display="html">
					<spring:url value="/users/{userId}.html" var="userUrl">
						<spring:param name="userId" value="${user.id}" />
					</spring:url>
					<a href="${fn:escapeXml(userUrl)}"><c:out
							value="${user.firstName} ${user.lastName}" /></a>
				</datatables:column>
				<datatables:column title="Address" property="address"
					cssStyle="width: 200px;" />
				<datatables:column title="City" property="city" />
				<datatables:column title="Telephone" property="telephone" />
			</datatables:table>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
