<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="fragments/headTag.jsp" />
</head>
<body>
	<div class="container-fluid">

		<div class="masthead">
			<jsp:include page="fragments/header.jsp" />
		</div>
		<jsp:include page="fragments/navBar.jsp" />

		<h3>Email confirmation page</h3>
		<p>
			${message} <br /> <a
				href="${pageContext.request.contextPath}/index.html">Home page</a><br />
		</p>
	</div>
</body>
</html>