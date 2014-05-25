<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />

			<h2>Team Information</h2>

			<table class="table table-striped" style="width: 600px;">
				<tr>
					<th>Name</th>
					<td><b><c:out value="${team.name}" /></b></td>
				</tr>
				<tr>
					<th>Type</th>
					<td><b><c:out value="${team.type.name}" /></b></td>
				</tr>
				<tr>
					<th>Administrator</th>
					<td><b><c:out value="${team.admin.login}" /></b></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><c:out value="${team.address}" /></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><c:out value="${team.email}" /></td>
				</tr>
				<tr>
					<th>Telephone</th>
					<td><c:out value="${team.telephone}" /></td>
				</tr>

				<tr>
					<td><spring:url value="{teamId}/edit.html" var="editUrl">
							<spring:param name="teamId" value="${team.id}" />
						</spring:url> <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit
							Team</a></td>
				</tr>
			</table>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
