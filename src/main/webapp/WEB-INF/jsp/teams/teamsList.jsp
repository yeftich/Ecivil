<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>

<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<h2>Teams</h2>

			<a href='<spring:url value="/teams/new" htmlEscape="true"/>'
				class="btn btn-success">Add Team</a> <br />

			<c:choose>
				<c:when test="${fn:length(itemList) > 0}">
					<datatables:table id="teams" data="${itemList}" cdn="true"
						row="team" theme="bootstrap2" cssClass="table table-striped"
						paginate="false" info="false">

						<datatables:column title="Name" cssStyle="width: 150px;"
							display="html">
							<spring:url value="/teams/{teamId}.html" var="teamUrl">
								<spring:param name="teamId" value="${team.id}" />
							</spring:url>
							<a href="${fn:escapeXml(teamUrl)}"><c:out
									value="${team.name}" /></a>
						</datatables:column>
						<datatables:column title="Type">
							<c:out value="${team.type.name}" />
						</datatables:column>
						<datatables:column title="Administrator">
							<c:out value="${team.admin.login}" />
						</datatables:column>
						<datatables:column title="Address" property="address"
							cssStyle="width: 200px;" />
						<datatables:column title="Email" property="email" />
						<datatables:column title="Telephone" property="telephone" />
						<datatables:column display="html">
							<spring:url value="/teams/{teamId}/delete.html"
								var="teamDeleteUrl">
								<spring:param name="teamId" value="${team.id}" />
							</spring:url>
							<a href='${fn:escapeXml(teamDeleteUrl)}'
								class="btn btn-danger btn-mini">Delete Team</a>
						</datatables:column>
					</datatables:table>

				</c:when>

				<c:otherwise>
					<H4>No teams found</H4>
				</c:otherwise>
			</c:choose>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
