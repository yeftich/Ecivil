<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<jsp:include page="../fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />

			<h2>Accident Information</h2>

			<table class="table table-striped" style="width: 600px;">
				<tr>
					<th>Created date</th>
					<td><b><joda:format value="${accident.createdDateTime}" pattern="MM/dd/yyyy HH:mm:ss" /></b></td>
				</tr>
				<tr>
					<th>Place</th>
					<td><b><c:out value="${accident.place}" /></b></td>
				</tr>
				<tr>
					<th>Description</th>
					<td><c:out value="${accident.textDescription}" /></td>
				</tr>
				<tr>
					<th>Created by user</th>
					<td><c:out value="${accident.owner.login}" /></td>
				</tr>
				<tr>
					<th>Type</th>
					<td><c:out value="${accident.type}" /></td>
				</tr>

				<tr>
					<td><spring:url value="{accidentId}/edit.html" var="editUrl">
							<spring:param name="accidentId" value="${accident.id}" />
						</spring:url> <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit
							Accident</a></td>
				</tr>
			</table>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
