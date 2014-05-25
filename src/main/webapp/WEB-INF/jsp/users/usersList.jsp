<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>

<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<div id="main">
		<jsp:include page="../fragments/header.jsp" />
		<div class="container">
			<jsp:include page="../fragments/navBar.jsp" />
			<h2>Users</h2>

			<c:choose>
				<c:when test="${fn:length(itemList) > 0}">
					<datatables:table id="users" data="${itemList}" cdn="true"
						row="user" theme="bootstrap2" cssClass="table table-striped"
						paginate="false" info="false">
						<datatables:column title="Login" property="login" />
						<datatables:column title="Name" cssStyle="width: 150px;"
							display="html">
							<spring:url value="/users/{userId}.html" var="userUrl">
								<spring:param name="userId" value="${user.id}" />
							</spring:url>
							<a href="${fn:escapeXml(userUrl)}"><c:out
									value="${user.firstName} ${user.lastName}" /></a>
						</datatables:column>
						<datatables:column title="Role">
							<c:out value="${user.role.role}" />
						</datatables:column>
						<datatables:column title="Address" property="address"
							cssStyle="width: 200px;" />
						<datatables:column title="City" property="city" />
						<datatables:column title="Telephone" property="telephone" />
						<datatables:column display="html">
							<spring:url value="/users/{userId}/delete.html"
								var="userDeleteUrl">
								<spring:param name="userId" value="${user.id}" />
							</spring:url>
							<a href='${fn:escapeXml(userDeleteUrl)}'
								class="btn btn-danger btn-mini">Delete User</a>
						</datatables:column>

					</datatables:table>
				</c:when>

				<c:otherwise>
					<H4>No users found</H4>
				</c:otherwise>
			</c:choose>

			<jsp:include page="../fragments/footer.jsp" />

		</div>
	</div>
</body>

</html>
