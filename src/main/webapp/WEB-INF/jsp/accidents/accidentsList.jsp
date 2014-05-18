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
	<script>
		window.onload = getLocation;
		var x = document.getElementById("demo");
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition,
						showError);
			} else {
				x.innerHTML = "Geolocation is not supported by this browser.";
			}
		}

		function showPosition(position) {
			var latlon = position.coords.latitude + ","
					+ position.coords.longitude;

			var img_url = "http://maps.googleapis.com/maps/api/staticmap?center="
					+ latlon + "&zoom=14&size=400x300&sensor=false";
			document.getElementById("mapholder").innerHTML = "<img src='"+img_url+"'>";
		}

		function showError(error) {
			switch (error.code) {
			case error.PERMISSION_DENIED:
				x.innerHTML = "User denied the request for Geolocation."
				break;
			case error.POSITION_UNAVAILABLE:
				x.innerHTML = "Location information is unavailable."
				break;
			case error.TIMEOUT:
				x.innerHTML = "The request to get user location timed out."
				break;
			case error.UNKNOWN_ERROR:
				x.innerHTML = "An unknown error occurred."
				break;
			}
		}
	</script>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<h2>Accidents</h2>

			<a href='<spring:url value="/accidents/new" htmlEscape="true"/>'
				class="btn btn-success">Add Accident</a> <br />

			<p id="demo"></p>
			<div id="mapholder"></div>
			<c:choose>
				<c:when test="${fn:length(itemList) > 0}">
					<datatables:table id="accidents" data="${itemList}" cdn="true"
						row="accident" theme="bootstrap2" cssClass="table table-striped"
						paginate="false" info="false">

						<datatables:column title="Description" cssStyle="width: 150px;"
							display="html">
							<spring:url value="/accidents/{accidentId}.html"
								var="accidentUrl">
								<spring:param name="accidentId" value="${accident.id}" />
							</spring:url>
							<a href="${fn:escapeXml(accidentUrl)}"><c:out
									value="${accident.textDescription}" /></a>
						</datatables:column>

						<datatables:column title="Type">
							<c:out value="${accident.type}" />
						</datatables:column>

						<datatables:column title="Created by" display="html">
							<spring:url value="/users/{userId}.html" var="ownerUrl">
								<spring:param name="userId" value="${accident.owner.id}" />
							</spring:url>
							<a href="${fn:escapeXml(ownerUrl)}"> <c:out
									value="${accident.owner.login}" /></a>
						</datatables:column>

						<datatables:column title="Created date">
							<joda:format value="${accident.createdDateTime}"
								pattern="MM/dd/yyyy HH:mm:ss" />
						</datatables:column>

						<datatables:column title="Place">
							<c:out value="${accident.place}" />
						</datatables:column>


						<datatables:column display="html">
							<security:authorize access="hasAnyRole('ROLE_ADMIN')">
								<spring:url value="/accidents/{accidentId}/delete.html"
									var="accidentDeleteUrl">
									<spring:param name="accidentId" value="${accident.id}" />
								</spring:url>
								<a href='${fn:escapeXml(accidentDeleteUrl)}'
									class="btn btn-danger btn-mini">Delete Accident</a>
							</security:authorize>
						</datatables:column>

					</datatables:table>

				</c:when>

				<c:otherwise>
					<H4>No accidents found</H4>
				</c:otherwise>
			</c:choose>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
