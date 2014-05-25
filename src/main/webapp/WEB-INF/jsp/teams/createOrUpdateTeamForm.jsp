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
</head>
<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<c:choose>
				<c:when test="${team['new']}">
					<c:set var="method" value="post" />
				</c:when>
				<c:otherwise>
					<c:set var="method" value="put" />
				</c:otherwise>
			</c:choose>

			<h2>
				<c:if test="${team['new']}">New </c:if>
				Team
			</h2>
			<form:form modelAttribute="team" method="${method}"
				class="form-horizontal" id="add-team-form">
				<ecivil:inputField label="Team Name" name="name" />
				<div class="control-group">
					<ecivil:selectField name="type" label="Type " names="${teamtypes}"	size="2" multiple="false"/>
				</div>
				<div class="control-group">
					<ecivil:selectField name="admin" label="Administrator " names="${users}"	size="5" multiple="false"/>
				</div>
				<ecivil:inputField label="Address" name="address" />
				<ecivil:inputField label="Email" name="email" />
				<ecivil:inputField label="Telephone" name="telephone" />

				<div class="form-actions">
					<c:choose>
						<c:when test="${team['new']}">
							<button type="submit">Add team</button>
						</c:when>
						<c:otherwise>
							<button type="submit">Update team</button>
						</c:otherwise>
					</c:choose>
				</div>
			</form:form>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
