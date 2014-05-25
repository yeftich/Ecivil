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

			<h2>Danger Information</h2>

			<table class="table table-striped" style="width: 600px;">
				<tr>
					<th>Created date</th>
					<td><b><joda:format value="${danger.createdDateTime}" pattern="dd/MM/yyyy HH:mm:ss" /></b></td>
				</tr>
				<tr>
					<th>Place</th>
					<td><b><c:out value="${danger.place}" /></b></td>
				</tr>
				<tr>
					<th>Description</th>
					<td><c:out value="${danger.textDescription}" /></td>
				</tr>
				<tr>
					<th>Created by user</th>
					<td><c:out value="${danger.owner.login}" /></td>
				</tr>
				<tr>
					<th>Type</th>
					<td><c:out value="${danger.type}" /></td>
				</tr>

				<tr>
					<td><spring:url value="{dangerId}/edit.html" var="editUrl">
							<spring:param name="dangerId" value="${danger.id}" />
						</spring:url> <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit
							Danger</a></td>
				</tr>
			</table>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
