<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//GR"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="el">

<jsp:include page="fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="fragments/header.jsp" />
		<div class="container">
			<jsp:include page="fragments/navBar.jsp" />
			<h1>Welcome page</h1>
			<p>
				You have successfully logged in.<br /> <a
					href="${pageContext.request.contextPath}/index.html">Home page</a><br />
			</p>
		</div>
	</div>
</body>
</html>