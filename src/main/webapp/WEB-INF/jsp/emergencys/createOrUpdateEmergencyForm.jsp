<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ecivil" tagdir="/WEB-INF/tags"%>

<jsp:include page="../fragments/headTag.jsp" />

<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<c:choose>
				<c:when test="${emergency['new']}">
					<c:set var="method" value="post" />
				</c:when>
				<c:otherwise>
					<c:set var="method" value="put" />
				</c:otherwise>
			</c:choose>

			<h2>
				<c:if test="${emergency['new']}">New </c:if>
				Emergency
			</h2>

			<form:form modelAttribute="emergency" method="${method}"
				class="form-horizontal" id="add-emergency-form">

				<div class="control-group">
					<label class="control-label">Description </label>

					<div class="controls">
						<form:textarea path="textDescription" rows="5" cols="30" />
						<span class="help-inline"><form:errors
								path="textDescription" /></span>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Place </label>

					<div class="controls">
						<form:input path="place" />
						<span class="help-inline"><form:errors path="place" /></span>
					</div>
				</div>

				<div class="form-actions">
					<c:choose>
						<c:when test="${emergency['new']}">
							<button type="submit">Add emergency</button>
						</c:when>
						<c:otherwise>
							<button type="submit">Update emergency</button>
						</c:otherwise>
					</c:choose>
				</div>
			</form:form>

		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
