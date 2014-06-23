<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ecivil" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<div class="container-fluid">

		<div class="masthead">
			<jsp:include page="../fragments/header.jsp" />
		</div>
		<jsp:include page="../fragments/navBar.jsp" />

		<c:choose>
			<c:when test="${action['new']}">
				<c:set var="method" value="post" />
			</c:when>
			<c:otherwise>
				<c:set var="method" value="put" />
			</c:otherwise>
		</c:choose>

		<h3>
			<c:if test="${action['new']}">New </c:if>
			Action
		</h3>

		<form:form modelAttribute="action" method="${method}"
			class="form-horizontal" id="add-action-form">

			<div class="control-group">
				<label class="control-label">Message </label>

				<div class="controls">
					<form:textarea path="textDescription" rows="5" cols="30" />
					<span class="help-inline"><form:errors
							path="textDescription" /></span>
				</div>
			</div>

			<security:authorize
				access="hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTION', 'ROLE_VOLUNTEER', 'ROLE_INSTITUTIONS_ADMIN','ROLE_VOLUNTEERS_ADMIN')">
				<div class="control-group">
					<label class="control-label">Tool </label>

					<div class="controls">
						<form:input path="tool" />
						<span class="help-inline"><form:errors path="tool" /></span>
					</div>
				</div>
			</security:authorize>
			<div class="form-actions">
				<c:choose>
					<c:when test="${action['new']}">
						<button type="submit">Send</button>
					</c:when>
					<c:otherwise>
						<button type="submit">Update action</button>
					</c:otherwise>
				</c:choose>
			</div>
		</form:form>

	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>

</html>
