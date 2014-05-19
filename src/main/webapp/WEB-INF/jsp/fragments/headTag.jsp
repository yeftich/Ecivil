<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<!--
ecivil :: a civil protection web application
-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>E-Civil</title>
		
		
		<spring:url value="/webjars/bootstrap/2.3.0/css/bootstrap.min.css"
			var="bootstrapCss" />
		<link href="${bootstrapCss}" rel="stylesheet" />
		
		<spring:url value="/resources/css/ecivil.css" var="ecivilCss" />
		<link href="${ecivilCss}" rel="stylesheet" />
		
		<spring:url value="/webjars/jquery/1.9.1/jquery.js" var="jQuery" />
		<script src="${jQuery}"></script>
		
		<spring:url value="/webjars/jquery-validation/1.12.0/jquery.validate.js" var = "jQueryValidate" />
		<script src="${jQueryValidate}"></script>
		
		<spring:url value="/resources/js/error_messages_el.js" var = "jQueryValidateErrorMessages" />
		<script src="${jQueryValidateErrorMessages}"></script>
		
		<!-- jquery-ui.js file is really big so we only load what we need instead of loading everything -->
		<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
			var="jQueryUiCore" />
		<script src="${jQueryUiCore}"></script>
		
		<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.datepicker.js"
			var="jQueryUiDatePicker" />
		<script src="${jQueryUiDatePicker}"></script>
		
		<!-- jquery-ui.css file is not that big so we can afford to load it -->
		<spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css"
			var="jQueryUiCss" />
		<link href="${jQueryUiCss}" rel="stylesheet"></link>
	</head>