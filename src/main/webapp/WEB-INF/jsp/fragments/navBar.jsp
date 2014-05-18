<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<div class="navbar" style="width: auto;">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a
				href="<spring:url value="/emergencys.html" htmlEscape="true" />"><i
					class="icon-home"></i> <fmt:message key="navbar.menu.home" /></a></li>
			<security:authorize access="isAuthenticated()">
				<li><a
					href="<spring:url value="/accidents.html" htmlEscape="true" />" ><i
						class="icon-warning-sign"></i> <fmt:message key="navbar.menu.accidents" /></a></li>
				<li>
				<li><a
					href="<spring:url value="/dangers.html" htmlEscape="true" />" ><i
						class="icon-flag"></i> <fmt:message key="navbar.menu.dangers" /></a></li>
				<li>
				</security:authorize>

			<security:authorize access="hasAnyRole('ROLE_ADMIN')">
				<li><a
					href="<spring:url value="/teams.html" htmlEscape="true" />" ><i
						class="icon-th-large"></i> <fmt:message key="navbar.menu.teams" /></a></li>
				<li><a
					href="<spring:url value="/users.html" htmlEscape="true" />" title="<fmt:message key="navbar.menu.users.title"/>"><i
						class="icon-user"></i> <fmt:message key="navbar.menu.users" /></a></li>
			</security:authorize>
			
			<security:authorize access="hasAnyRole('ROLE_INSTITUTIONS_ADMIN','ROLE_VOLUNTEERS_ADMIN')">
				<li><a
					href="<spring:url value="/users/manage.html" htmlEscape="true" />" ><i
						class="icon-user"></i> <fmt:message key="navbar.menu.manageTeams" /></a></li>
				<li>
			</security:authorize>

			
		</ul>
	</div>
</div>

