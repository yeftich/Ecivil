<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

			<h2>Manage Users</h2>

			<c:choose>
				<c:when test="${fn:length(userTeams) > 0}">
					<datatables:table id="users" data="${userTeams}" cdn="true"
						row="userTeam" theme="bootstrap2" cssClass="table table-striped"
						paginate="false" info="false">

						<datatables:column title="Login">
							<c:out value="${userTeam.user.login}" />
						</datatables:column>

						<datatables:column title="Name" cssStyle="width: 150px;"
							display="html">
							<spring:url value="/users/{userId}.html" var="userUrl">
								<spring:param name="userId" value="${userTeam.user.id}" />
							</spring:url>
							<a href="${fn:escapeXml(userUrl)}"><c:out
									value="${userTeam.user.firstName} ${userTeam.user.lastName}" /></a>
						</datatables:column>

						<datatables:column title="Role">
							<c:out value="${userTeam.user.role.role}" />
						</datatables:column>

						<datatables:column title="Team" display="html">
							<spring:url value="/teams/{teamId}.html" var="teamUrl">
								<spring:param name="teamId" value="${userTeam.team.id}" />
							</spring:url>
							<a href="${fn:escapeXml(teamUrl)}"><c:out
									value="${userTeam.team.name}" /></a>
						</datatables:column>

						<datatables:column title="Responsibility" display="html">
							<c:out value="${userTeam.responsibility}" />  
							<spring:url
								value="/users/{userId}/teams/{teamId}/responsibility/add.html"
								var="addResponsibilityUrl">
								<spring:param name="userId" value="${userTeam.user.id}" />
								<spring:param name="teamId" value="${userTeam.team.id}" />
							</spring:url>
							<a href="${fn:escapeXml(addResponsibilityUrl)}"
								class="btn btn-mini btn-info">Edit</a>
						</datatables:column>


						<c:choose>
							<c:when test="${userTeam.verified}">
								<datatables:column title="Status" property="status" />
							</c:when>
							<c:otherwise>
								<datatables:column title="Status" display="html">
									<spring:url value="/users/{userId}/teams/{teamId}/verify.html"
										var="verifyUserUrl">
										<spring:param name="userId" value="${userTeam.user.id}" />
										<spring:param name="teamId" value="${userTeam.team.id}" />
									</spring:url>
									<a href="${fn:escapeXml(verifyUserUrl)}"
										class="btn btn-mini btn-primary">Verify User</a>
								</datatables:column>
							</c:otherwise>
						</c:choose>

						<datatables:column display="html">
							<spring:url value="/users/{userId}/teams/{teamId}/remove.html"
								var="userRemoveUrl">
								<spring:param name="userId" value="${userTeam.user.id}" />
								<spring:param name="teamId" value="${userTeam.team.id}" />
							</spring:url>
							<a href='${fn:escapeXml(userRemoveUrl)}'
								class="btn btn-danger btn-mini">Remove User</a>
						</datatables:column>

					</datatables:table>
				</c:when>

				<c:otherwise>
					<H4>No users found</H4>
				</c:otherwise>
			</c:choose>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
