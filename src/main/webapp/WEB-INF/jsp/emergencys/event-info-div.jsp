<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

			<h4>Event info</h4>

				<c:if test="${fn:length(itemList) > 0}">

					<c:forEach items="${itemList}" var="event">
						<table id="event-info-table" class="table" style="width: 480px;">
							<tr class= "event-info-${event.id}" >
								<td valign="top" style="width: 100px;">
									<dl class="dl-horizontal">
										<dt>Info</dt>
										<dd>
											<c:out value="${event.textDescription}" />
										</dd>
										<dt>Created by</dt>
										<dd>
											<c:out value="${event.owner.login}" />
										</dd>
										<dt>Created Date</dt>
										<dd>
											<joda:format value="${event.createdDateTime}"
												pattern="dd/MM/yyyy HH:mm:ss" />
										</dd>
										<dt>Type</dt>
										<dd>
											<c:out value="${event.type}" />
										</dd>
										<dt>Freshness</dt>
										<dd>
											<c:out value="${event.freshness}" />
										</dd>
										<dt>Certification</dt>
										<dd>
											<c:out value="${event.certification}" />
										</dd>
										<dt>Place</dt>
										<dd>
											<c:out value="${event.place}" />
										</dd>
									</dl>
								</td>
								<td valign="top"><c:if
										test="${fn:length(event.actions) > 0}">
										<table class="table-condensed">
											<thead>
												<tr>
													<th>Description</th>
													<th>Action Date</th>
													<th>Actor</th>
												</tr>
											</thead>

											<c:forEach var="action" items="${event.actions}">
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
							<tr class= "event-info-${event.id}">
								<td colspan="2"><spring:url
										value="/emergencys/{emergencyId}/action/new"
										var="newActionUrl">
										<spring:param name="emergencyId" value="${event.id}" />
									</spring:url> <spring:url value="/emergencys/{emergencyId}/close"
										var="closeEmergencyUrl">
										<spring:param name="emergencyId" value="${event.id}" />
									</spring:url> <spring:url value="/emergencys/{emergencyId}/verify"
										var="verifyEmergencyUrl">
										<spring:param name="emergencyId" value="${event.id}" />
									</spring:url> <spring:url value="/emergencys/{emergencyId}/edit"
										var="editEmergencyUrl">
										<spring:param name="emergencyId" value="${event.id}" />
									</spring:url> <c:choose>
										<c:when test="${event.owner.login == userLogin}">
											<a href="${fn:escapeXml(editEmergencyUrl)}">Edit</a>
										 | 
										<a href="${fn:escapeXml(closeEmergencyUrl)}">Close</a>
										</c:when>
										<c:otherwise>
											<security:authorize
												access="hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTION', 'ROLE_VOLUNTEER', 'ROLE_INSTITUTIONS_ADMIN','ROLE_VOLUNTEERS_ADMIN')">
												<a href="${fn:escapeXml(newActionUrl)}">Participate</a>
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

				</c:if>

