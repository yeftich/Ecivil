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
	// google map v3
	var map;
	// locations of events
	var latLonArray = new Array();
	// markers for events to draw
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

	$(document).ready(function() {

		drawAllEmergencies();
		$("#show-all-button").click(function() {
			drawAllEmergencies();
		});

		$(".map-link").click(function() {

			var event_id = $(this).attr('id').replace('eventId', '');
			showMapForEvent(event_id);
			/* 			var event_info = $(this).attr('id').split("-");
			 var event_id = null;
			 *//* 							var event_lat = null;
																																																									 var event_lon = null; */
			/* 		event_id = event_info[0].replace('eventId', '');
			 *//* 							event_lat = event_info[1].replace('lat', '');
																																																										 event_lon = event_info[2].replace('lon', ''); */

		});
	});

	function drawAllEmergencies() {
		$("#event-info-panel").hide();
		$('#map-canvas').removeClass('alert alert-info');

		var getAllEmergenciesUrl = "/ecivil/ajax/index";

		$.ajax({
			url : getAllEmergenciesUrl,
			type : "POST",
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				showMapWithEmergencies(data);
			},
			error : function() {
				alert('Error while request..');
			}
		});

	}

	function showMapForEvent(eventId) {
		var getEventUrl = "/ecivil/ajax/event/" + eventId + "/get";
		console.log("log test getEventUrl for event with id " + eventId);
		/* 		var json = {
		 "eventId" : eventId
		 }; */
		$.ajax({
			url : getEventUrl,
			type : "GET",
			/* data : JSON.stringify(json), */
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				drawMapWithEvent(data);
				populateEventTable(data);
				$("#event-info-panel").show();
			},
			error : function() {
				alert('Error while request..');
			}
		});

	}
	/* 
	 function showEventInfo() {
	 $("#event-info-panel").show(); */
	/* 		$("#event-info-table tr").hide();
	 var event = "table tr.event-info-" + eventId; */
	/* $(event).show(); */
	/* } */

	function drawMapWithEvent(mapInfoArray) {

		$('#em-header-many').hide();
		$('#em-header-one').show();

		map = null;

		if (mapInfoArray[0].lat != 0 && mapInfoArray[0].lon != 0) {

			var myEventMapOptions = {
				zoom : 10,
				center : new google.maps.LatLng(mapInfoArray[0].lat,
						mapInfoArray[0].lon),
				mapTypeId : google.maps.MapTypeId.ROADMAP
			}

			map = new google.maps.Map(document.getElementById("map-canvas"),
					myEventMapOptions);

			$.each(mapInfoArray, function(idx, mapInfo) {
				console.log(mapInfo.id + " --> latLon: " + mapInfo.lat + ", "
						+ mapInfo.lon);
				if (mapInfo.lat != 0 && mapInfo.lon != 0) {
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
				}
			});
		} else {
			$('#map-canvas')
					.html(
							'<div class="alert alert-info">Το συγκεκριμένο συμβάν δεν έχει θέση στον χάρτη.</div>');
		}
		/* 
		 eventMap.setCenter(new google.maps.LatLng(mapInfoArray[0].lat,
		 mapInfoArray[0].lon)); */

	}

	function populateEventTable(data) {
		var r = new Array(), j = -1;
		r[++j] = '<tr><td><dl class="dl-horizontal"><dt>Description</dt><dd>';
		r[++j] = data[0].description;
		r[++j] = '</dd><dt>Started</dt><dd>';
		r[++j] = data[0].started;
		r[++j] = '</dd><dt>Type</dt><dd>';
		r[++j] = data[0].emtype;
		r[++j] = '</dd>	<dt>Created by</dt><dd>';
		r[++j] = data[0].owner;
		r[++j] = '</dd>	<dt>Verified</dt><dd>';
		r[++j] = data[0].verified;
		r[++j] = '</dd>	</dl></td><tr>';
		$('#event-info-table').html(r.join(''));

		r = new Array();
		j = -1;
		r[++j] = '<tr><td colspan="3">Actions</td></tr>';
		r[++j] = '<tr><td><em>Info</em></td><td><em>Start</em></td><td><em>User</em></td></td><td><em>Help</em></td></tr>';
		for (var key = 1, size = data.length; key < size; key++) {
			r[++j] = '<tr><td>';
			r[++j] = data[key].description;
			r[++j] = '</td><td>';
			r[++j] = data[key].started;
			r[++j] = '</td><td>';
			r[++j] = data[key].owner;
			r[++j] = '</td><td>';
			r[++j] = data[key].help;
			r[++j] = '</td></tr>';
		}

		$('#actions-info-table').html(r.join(''));

		if (data.length > 1) {
			$('#actions-info-table').show();
		} else {
			$('#actions-info-table').hide();
		}

	}

	function showMapWithEmergencies(mapInfoArray) {

		$('#em-header-one').hide();
		$('#em-header-many').show();

		var centerLat = 0;
		var centerLon = 0;
		$.each(mapInfoArray, function(indx, mapInfoo) {
			if (mapInfoo.lat != 0 && mapInfoo.lon != 0) {
				centerLat = mapInfoo.lat;
				centerLon = mapInfoo.lon;
				return false;
			}
		});

		var myOptions = {
			zoom : 10,
			center : new google.maps.LatLng(centerLat, centerLon),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}

		map = null;
		map = new google.maps.Map(document.getElementById("map-canvas"));

		$.each(mapInfoArray, function(idx, mapInfo) {
			console.log(mapInfo.id);
			if (mapInfo.lat != 0 && mapInfo.lon != 0) {
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
						showMapForEvent(mapInfo.id);
						/* 							infowindow
						 .setContent('<h4>' + mapInfo.id + '</h4>');
						 infowindow.open(map, marker); */
					}
				})(marker, i));
			}

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
	<div class="container-fluid">

		<div class="masthead">
			<jsp:include page="../fragments/header.jsp" />
		</div>
		<jsp:include page="../fragments/navBar.jsp" />

		<h3 id="em-header-many">Emergencies</h3>

		<h3 id="em-header-one">Emergency</h3>

		<!-- 			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Panel title</h3>
				</div>
				<div class="panel-body">Panel content</div>
			</div>
			 -->

		<div class="row-fluid">
			<!-- span numbers must add up to 12 -->
			<div class="span6">
				<div id="map-canvas"></div>
			</div>
			<div class="span4">
				<div id="event-info-panel">

					<div class="row">
						<div class="span12">
							<table id="event-info-table" class="table">
							</table>
						</div>
					</div>

					<div class="row">
						<div class="span12">
							<div id="event-info-commands">
								<div class="btn-group">

									<a id="show-all-button" href='#' class="btn btn-mini">Show
										all</a>

								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="span12">
							<table id="actions-info-table" class="table table-condensed">
							</table>
						</div>
					</div>

				</div>
			</div>
			<div class="span2">
				<div id="admin-info-messages"></div>
			</div>
		</div>
		<%-- 						
 --%>








		<c:choose>
			<c:when test="${fn:length(itemList) > 0}">

				<datatables:table id="emergencys" data="${itemList}" cdn="true"
					row="emergency" theme="bootstrap2" scrollX="100%" cssClass="table table-striped"
					paginate="false" info="false" sort="false">

					<datatables:column title="Created date">
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

					<spring:url value="/emergencys/{emergencyId}/action/new"
						var="newActionUrl">
						<spring:param name="emergencyId" value="${emergency.id}" />
					</spring:url>
					<spring:url value="/emergencys/{emergencyId}/close"
						var="closeEmergencyUrl">
						<spring:param name="emergencyId" value="${emergency.id}" />
					</spring:url>
					<spring:url value="/emergencys/{emergencyId}/verify"
						var="verifyEmergencyUrl">
						<spring:param name="emergencyId" value="${emergency.id}" />
					</spring:url>
					<spring:url value="/emergencys/{emergencyId}/unverify"
						var="unVerifyEmergencyUrl">
						<spring:param name="emergencyId" value="${emergency.id}" />
					</spring:url>
					<spring:url value="/emergencys/{emergencyId}/edit"
						var="editEmergencyUrl">
						<spring:param name="emergencyId" value="${emergency.id}" />
					</spring:url>


					<datatables:column display="html">
						<div class="button-group">
							<c:choose>
								<c:when test="${emergency.owner.login == userLogin}">
									<a href="${fn:escapeXml(editEmergencyUrl)}"
										class="btn btn-mini">Edit</a>

									<a href="${fn:escapeXml(closeEmergencyUrl)}"
										class="btn btn-mini">Close</a>
								</c:when>
								<c:otherwise>
									<security:authorize
										access="hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTION', 'ROLE_VOLUNTEER', 'ROLE_INSTITUTIONS_ADMIN','ROLE_VOLUNTEERS_ADMIN')">

										<a href="${fn:escapeXml(newActionUrl)}" class="btn btn-mini">Participate</a>
										<c:choose>
											<c:when test="${emergency.verified}">
												<a href="${fn:escapeXml(unVerifyEmergencyUrl)}"
													class="btn btn-mini">Unverify</a>
											</c:when>
											<c:otherwise>
												<a href="${fn:escapeXml(verifyEmergencyUrl)}"
													class="btn btn-mini">Verify</a>
											</c:otherwise>
										</c:choose>
									</security:authorize>
									<security:authorize
										access="hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTION', 'ROLE_INSTITUTIONS_ADMIN')">
										<a href="${fn:escapeXml(closeEmergencyUrl)}"
											class="btn btn-mini">Close</a>
									</security:authorize>
								</c:otherwise>
							</c:choose>
							<a class="btn btn-mini map-link" id="eventId${emergency.id}"
								href='#'><i class="icon-map-marker"></i></a>
						</div>



					</datatables:column>

				</datatables:table>

			</c:when>

			<c:otherwise>
				<H4>No emergencies found</H4>
			</c:otherwise>
		</c:choose>

		<jsp:include page="../fragments/footer.jsp" />

	</div>
</body>

</html>
