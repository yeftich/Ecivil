<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="header">

	<div id="logo">
		<spring:url value="/resources/images/ecivil_logo.gif" var="ecivilLogo" />
		<a href="<spring:url value="/" htmlEscape="true" />"> <img
			width="100%" src="${ecivilLogo}" id="logo" alt="E Civil Logo" />
		</a>
	</div>

	<h1 id="logoText">
		<fmt:message key="header.logo.text" />
	</h1>

	<div id="widgetBar">
		<!--signIn widget-->
		<security:authorize access="isAnonymous()">
			<div class="headerWidget">
				<a href="<spring:url value="/user-login.html" htmlEscape="true" />"
					class="bubble"><fmt:message key="header.user.login" /></a>
			</div>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<div class="headerWidget">
				Welcome <span id="loggedUser"><security:authentication
						property="principal.Username" /></span>
			</div>
		</security:authorize>

		<!--signUp widget-->
		<security:authorize access="isAnonymous()">
			<div class="headerWidget">
				<a href="<spring:url value="/users/new" htmlEscape="true" />"
					class="bubble"><fmt:message key="header.user.signup" /></a>
			</div>
		</security:authorize>

		<!--signOut widget-->
		<security:authorize access="isAuthenticated()">
			<div class="headerWidget">
				<a
					href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />"
					class="bubble"><fmt:message key="header.user.logout" /></a>
			</div>
		</security:authorize>
	</div>





</div>



<%-- this is not working  <p>Hi<security:authentication property="principal.username" /></p> --%>

