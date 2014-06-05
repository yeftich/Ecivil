<?xml version="1.0" encoding="UTF-8" ?>
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

<jsp:include page="../fragments/headTag.jsp" />


<script type="text/javascript" charset="utf-8">
	$(document).ready(
			function() {
				
				$("#event-info-panel").hide();

				var getAllEmergenciesUrl = "/ecivil/ajax/index";
				console.log("log test 1");
				$.ajax({
					url : getAllEmergenciesUrl,
					type : "POST",
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr
								.setRequestHeader("Content-Type",
										"application/json");
					},
					success : function(data) {
						showMapWithEmergencies(data);
					},
					error : function() {
						alert('Error while request..');
					}
				});

				$(".map-link").click(
						function() {

							var event_info = $(this).attr('id').split("-");
							var event_id = null;
							var event_lat = null;
							var event_lon = null;
							showEventInfo(4);
							event_id = event_info[0].replace('eventId', '');
							event_lat = event_info[1].replace('lat', '');
							event_lon = event_info[2].replace('lon', '');
							alert('we press show map for event with id '
									+ event_id + ' latitutude ' + event_lat
									+ ' longitude' + event_lon);
						});
			});

	function showEventInfo(eventId) {
		$("#event-info-table tr").hide();
		var event = "table tr.event-info-" + eventId;
		$(event).show();
	}

	function showMapWithEmergencies(mapInfoArray) {
		var latLonArray = new Array();
		var markerArray = new Array();
		// Setup the different icons and shadows
		var iconURLPrefix = 'http://maps.google.com/mapfiles/ms/icons/';

		var icons = {
			"Accident" : iconURLPrefix + 'red-dot.png',
			"Danger" : iconURLPrefix + 'orange-dot.png',
			"actionVolIcon" : iconURLPrefix + 'blue-dot.png',
			"curLocIcon" : iconURLPrefix + 'yellow-dot.png',
			"actionInstIcon" : iconURLPrefix + 'green-dot.png'
		};

		var shadow = {
			anchor : new google.maps.Point(15, 33),
			url : iconURLPrefix + 'msmarker.shadow.png'
		};

		var infowindow = new google.maps.InfoWindow({
			maxWidth : 160
		});

		var myOptions = {
			zoom : 10,
			center : new google.maps.LatLng(mapInfoArray[0].lat,
					mapInfoArray[0].lon),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}

		var map = new google.maps.Map(document.getElementById("map-canvas"));

		$.each(mapInfoArray,
				function(idx, mapInfo) {
					console.log(mapInfo.id);
					latLonArray[idx] = new google.maps.LatLng(mapInfo.lat,
							mapInfo.lon);
					var marker = new google.maps.Marker({
						position : latLonArray[idx],
						map : map,
						shadow : shadow,
						icon : icons[mapInfo.type],
						/*shape : shape, */
						title : mapInfo.type
					/* zIndex : i */
					});
					markerArray[idx] = marker;

					google.maps.event.addListener(marker, 'click', (function(
							marker, i) {
						return function() {
							infowindow
									.setContent('<h4>' + mapInfo.id + '</h4>');
							infowindow.open(map, marker);
						}
					})(marker, i));

				});

		var latlngbounds = new google.maps.LatLngBounds();
		for (var i = 0; i < latLonArray.length; i++) {
			latlngbounds.extend(latLonArray[i]);
		}
		map.setCenter(latlngbounds.getCenter());
		map.fitBounds(latlngbounds);
	}
</script>
</head>
<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />


			<h2>Emergencies</h2>

			<div id="map-canvas"></div>


			<div id="event-info-panel">
				<div id="event-info">
					<table id="event-info-table" class="table">
						<tr>
							<td>
								<dl class="dl-horizontal">
									<dt>Description</dt>
									<dd>...</dd>
									<dt>Started</dt>
									<dd>...</dd>
									<dt>Type</dt>
									<dd>...</dd>
									<dt>Created by</dt>
									<dd>...</dd>
									<dt>Verified</dt>
									<dd>...</dd>
								</dl>
							</td>
							<td>
								<table id="actions-info-table" class="table table-condensed">
									<thead>
										<tr>
											<th>Description</th>
											<th>Started</th>
											<th>Created by</th>
										</tr>
									</thead>
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td colspan="2" id="event-info-commands"><spring:url
									value="/emergencys/{emergencyId}/action/new" var="newActionUrl">
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
				</div>

			</div>


			<c:choose>
				<c:when test="${fn:length(itemList) > 0}">

					<datatables:table id="emergencys" data="${itemList}" cdn="true"
						row="emergency" theme="bootstrap2" cssClass="table table-striped"
						paginate="false" info="false">

						<datatables:column title="Created date" sortType="date">
							<joda:format value="${emergency.createdDateTime}"
								pattern="dd/MM/yyyy HH:mm:ss" />
						</datatables:column>

						<datatables:column title="Description" cssStyle="width: 150px;">
							<c:out value="${emergency.textDescription}" />
						</datatables:column>

						<datatables:column title="Type">
							<c:out value="${emergency.type}" />
						</datatables:column>

						<datatables:column title="Created by" display="html">
							<spring:url value="/users/{userId}.html" var="ownerUrl">
								<spring:param name="userId" value="${emergency.owner.id}" />
							</spring:url>
							<a href="${fn:escapeXml(ownerUrl)}"> <c:out
									value="${emergency.owner.login}" /></a>
						</datatables:column>



						<datatables:column display="html">
							<a class="map-link"
								id="eventId${emergency.id}-lat${emergency.location.latitude}-lon${emergency.location.longitude}"
								href='#' class="btn btn-danger btn-mini">Show on map</a>
						</datatables:column>

					</datatables:table>

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
