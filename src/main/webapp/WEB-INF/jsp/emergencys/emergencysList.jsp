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
			<h2>Emergencies</h2>

			<c:choose>
				<c:when test="${danger['new']}">
					<c:set var="method" value="post" />
				</c:when>
				<c:otherwise>
					<c:set var="method" value="put" />
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${fn:length(itemList) > 0}">

					<c:forEach items="${itemList}" var="emergency">
						<table id="emergencys" class="table" style="width: 600px;">
							<tr>
								<td valign="top" style="width: 120px;">
									<dl class="dl-horizontal">
										<dt>Info</dt>
										<dd>
											<c:out value="${emergency.textDescription}" />
										</dd>
										<dt>Created by</dt>
										<dd>
											<c:out value="${emergency.owner.login}" />
										</dd>
										<dt>Created Date</dt>
										<dd>
											<joda:format value="${emergency.createdDateTime}"
												pattern="dd/MM/yyyy HH:mm:ss" />
										</dd>
										<dt>Type</dt>
										<dd>
											<c:out value="${emergency.type}" />
										</dd>
										<dt>Freshness</dt>
										<dd>
											<c:out value="${emergency.freshness}" />
										</dd>
										<dt>Certification</dt>
										<dd>
											<c:out value="${emergency.certification}" />
										</dd>
										<dt>Place</dt>
										<dd>
											<c:out value="${emergency.place}" />
										</dd>
									</dl>
								</td>
								<td valign="top"><c:if
										test="${fn:length(emergency.actions) > 0}">
										<table class="table-condensed">
											<thead>
												<tr>
													<th>Description</th>
													<th>Action Date</th>
													<th>Actor</th>
												</tr>
											</thead>

											<c:forEach var="action" items="${emergency.actions}">
												<tr>
													<td><c:out value="${action.textDescription}" /></td>
													<td><joda:format value="${action.createdDateTime}"
															pattern="dd/MM/yyyy HH:mm:ss" /></td>
													<td><c:out value="${action.owner.login}" /></td>
												</tr>
											</c:forEach>

										</table>
									</c:if></td>
							</tr>
							<tr>
								<td colspan="2"><spring:url
										value="/emergencys/{emergencyId}/action/new"
										var="newActionUrl">
										<spring:param name="emergencyId" value="${emergency.id}" />
									</spring:url> <spring:url value="/emergencys/{emergencyId}/close"
										var="closeEmergencyUrl">
										<spring:param name="emergencyId" value="${emergency.id}" />
									</spring:url> <spring:url value="/emergencys/{emergencyId}/verify"
										var="verifyEmergencyUrl">
										<spring:param name="emergencyId" value="${emergency.id}" />
									</spring:url><spring:url value="/emergencys/{emergencyId}/edit"
										var="editEmergencyUrl">
										<spring:param name="emergencyId" value="${emergency.id}" />
									</spring:url>
									 <c:choose>
										<c:when test="${emergency.owner.login == userLogin}">
										<a href="${fn:escapeXml(editEmergencyUrl)}">Edit</a>
										 | 
										<a href="${fn:escapeXml(closeEmergencyUrl)}">Close</a>
										</c:when>
										<c:otherwise>
											<security:authorize access="isAuthenticated()">
												<a href="${fn:escapeXml(newActionUrl)}">Participate</a>
											</security:authorize>
											<security:authorize
												access="hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTION', 'ROLE_VOLUNTEER', 'ROLE_INSTITUTIONS_ADMIN','ROLE_VOLUNTEERS_ADMIN')">
										  |  
									<a href="${fn:escapeXml(verifyEmergencyUrl)}">Verify</a>
										  | 
									<a href="${fn:escapeXml(closeEmergencyUrl)}">Close</a>
											</security:authorize>
										</c:otherwise>
									</c:choose></td>

							</tr>

						</table>
					</c:forEach>

				</c:when>

				<c:otherwise>
					<H4>No emergencies found</H4>
				</c:otherwise>
			</c:choose>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
