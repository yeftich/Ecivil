<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<jsp:include page="../fragments/headTag.jsp" />


<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<h2>Dangers</h2>

			<a href='<spring:url value="/dangers/new" htmlEscape="true"/>'
				class="btn btn-success">Add Danger</a> <br />

			<c:choose>
				<c:when test="${fn:length(itemList) > 0}">
					<datatables:table id="dangers" data="${itemList}" cdn="true"
						row="danger" theme="bootstrap2" cssClass="table table-striped"
						paginate="false" info="false">

						<datatables:column title="Description" cssStyle="width: 150px;"
							display="html">
							<spring:url value="/dangers/{dangerId}.html" var="dangerUrl">
								<spring:param name="dangerId" value="${danger.id}" />
							</spring:url>
							<a href="${fn:escapeXml(dangerUrl)}"><c:out
									value="${danger.textDescription}" /></a>
						</datatables:column>

						<datatables:column title="Type">
							<c:out value="${danger.type}" />
						</datatables:column>

						<datatables:column title="Created by" display="html">
							<spring:url value="/users/{userId}.html" var="ownerUrl">
								<spring:param name="userId" value="${danger.owner.id}" />
							</spring:url>
							<a href="${fn:escapeXml(ownerUrl)}"> <c:out
									value="${danger.owner.login}" /></a>
						</datatables:column>

						<datatables:column title="Created date">
							<joda:format value="${danger.createdDateTime}"
								pattern="MM/dd/yyyy HH:mm:ss" />
						</datatables:column>

						<datatables:column title="Place">
							<c:out value="${danger.place}" />
						</datatables:column>


						<datatables:column display="html">
							<security:authorize access="hasAnyRole('ROLE_ADMIN')">
								<spring:url value="/dangers/{dangerId}/delete.html"
									var="dangerDeleteUrl">
									<spring:param name="dangerId" value="${danger.id}" />
								</spring:url>
								<a href='${fn:escapeXml(dangerDeleteUrl)}'
									class="btn btn-danger btn-mini">Delete Danger</a>
							</security:authorize>
						</datatables:column>


					</datatables:table>

				</c:when>

				<c:otherwise>
					<H4>No dangers found</H4>
				</c:otherwise>
			</c:choose>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
