<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<div class="navbar" style="width: auto;">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a
				href="<spring:url value="/" htmlEscape="true" />"><i
					class="icon-home"></i> <fmt:message key="navbar.menu.home" /></a></li>
			<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_INSTITUTIONS_ADMIN','ROLE_VOLUNTEERS_ADMIN')">
				<li><a
					href="<spring:url value="/users.html" htmlEscape="true" />" title="<fmt:message key="navbar.menu.users.title"/>"><i
						class="icon-th-list"></i> <fmt:message key="navbar.menu.users" /></a></li>
			</security:authorize>
		</ul>
	</div>
</div>

