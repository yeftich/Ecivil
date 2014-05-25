<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<!--
ecivil :: a civil protection web application
-->

<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> -->
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta charset="utf-8">
<title>E-Civil</title>

<spring:url value="/webjars/bootstrap/2.3.0/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/css/ecivil.css" var="ecivilCss" />
<link href="${ecivilCss}" rel="stylesheet" />

<spring:url value="/webjars/jquery/1.9.1/jquery.js" var="jQuery" />
<script src="${jQuery}">
	
</script>

<spring:url value="/webjars/jquery-validation/1.12.0/jquery.validate.js"
	var="jQueryValidate" />
<script src="${jQueryValidate}"></script>

<spring:url value="/resources/js/error_messages_el.js"
	var="jQueryValidateErrorMessages" />
<script src="${jQueryValidateErrorMessages}"></script>

<!-- jquery-ui.js file is really big so we only load what we need instead of loading everything -->
<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
	var="jQueryUiCore" />
<script src="${jQueryUiCore}"></script>

<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.datepicker.js"
	var="jQueryUiDatePicker" />
<script src="${jQueryUiDatePicker}"></script>

<!-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false">
	
</script>
<script>
	var map;
	function initialize() {
		var mapOptions = {
			zoom : 8,
			center : new google.maps.LatLng(-34.397, 150.644)
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
 -->
<!--  Google Maps Api -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyBjlwFzXStoK96vd4xUe_7tw6d6MXqyd9E&sensor=false">
	
</script>

<script type="text/javascript">
	$(document).ready(function() {
		saveUserLocation();
	});

	var intervalId = 0;
	intervalId = setInterval(saveUserLocation, 30000);

	function saveUserLocation() {
		navigator.geolocation.getCurrentPosition(getAndSaveLanLog);
	}

	function getAndSaveLanLog(position) {
		var lat = position.coords.latitude;
		var lon = position.coords.longitude;

		if (!lat || !lon) {
			lat = 0;
			lon = 0;
		} else if (isNaN(lat) || isNaN(lon)) {
			lat = 0;
			lon = 0;
		}
		var userName = $('#loggedUser').text();
		if (userName && userName.length > 0) {
			var urlAddress = "/ecivil/users/" + userName + "/locations/lat/"
					+ lat + "/lon/" + lon + "/save";
			$.ajax({
				url : urlAddress,
				success : function(data) {
					$('#result').html(data);
				}
			});
		}
	}
</script>

<!-- <script type="text/javascript">
	this one is working
	function initializeMap() {
		 navigator.geolocation.getCurrentPosition(callback);
	}
	
	function callback(position) {
        
        var lat = position.coords.latitude;
        var lon = position.coords.longitude;
        
        document.getElementById('latitude').innerHTML = lat;
        document.getElementById('longitude').innerHTML = lon;
        
    	var myLatlng = new google.maps.LatLng(lat , lon );
		var myOptions = {
			zoom : 16,
			center : myLatlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);
		var marker = new google.maps.Marker({
			position : myLatlng,
			map : map,
			title : "Fast marker"
		});
	}

	google.maps.event.addDomListener(window, 'load', initializeMap);
	
	
</script> -->

<!-- <script type="text/javascript">
	var map = null;
	var lat = null;
	var lon = null;

	function getlocation() {
		// One-shot position request.
		navigator.geolocation.getCurrentPosition(callback);
	}

	function callback(position) {

		lat = position.coords.latitude;
		lon = position.coords.longitude;

		document.getElementById('latitude').innerHTML = lat;
		document.getElementById('longitude').innerHTML = lon;

	}

	google.maps.event.addDomListener(window, 'load', initMap);
	function initMap() {

		getlocation();
		
		var mapOptions = {
			center : new google.maps.LatLng(lat, lon),
			zoom : 8,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("map-canvas"),
				mapOptions);
		var latLong = new google.maps.LatLng(lat, lon);

		var marker = new google.maps.Marker({
			position : latLong
		});

		marker.setMap(map);
		map.setZoom(8);
		
	}
</script>
 -->
<!-- jquery-ui.css file is not that big so we can afford to load it -->
<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>