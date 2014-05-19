<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="fragments/headTag.jsp" />

<body>
	<script type="text/javascript">
		$(document).ready(
				function() {

					$('#login-form').validate(
							{
								rules : {
									j_username : {
										minlength : 4,
										required : true
									},
									j_password : {
										minlength : 4,
										required : true
									}
								},
								highlight : function(element) {
									$(element).closest('.control-group')
											.removeClass('success').addClass(
													'error');
								},
								success : function(element) {
									element.text('OK!').addClass('valid')
											.closest('.control-group')
											.removeClass('error').addClass(
													'success');
								}
							});
				});
	</script>
	<div id="main">
		<jsp:include page="fragments/header.jsp" />
		<div class="container">
			<jsp:include page="fragments/navBar.jsp" />

			<h1>
				<fmt:message key="page.login-form.title" />
			</h1>

			<p>
				<c:if test="${error == true}">
					<b class="error"><fmt:message
							key="page.login-form.invalid.login" /></b>
				</c:if>
			</p>

			<form id="login-form" method="post" class="form-horizontal"
				action="<c:url value='j_spring_security_check'/>">

				<div class="control-group">
					<label class="control-label" for="j_username"><fmt:message
							key="login-form.form.label.userLogin" /></label>
					<div class="controls">
						<input type="text" name="j_username" id="j_username" size="30"
							maxlength="40"
							placeholder="<fmt:message key="login-form.form.label.userLogin" />">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="j_password"><fmt:message key="login-form.form.label.password" /></label>
					<div class="controls">
						<input type="password" name="j_password" id="j_password" size="30"
							maxlength="32"
							placeholder="<fmt:message key="login-form.form.label.password" />">
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-success">
						<fmt:message key="login-form.form.button.submit" />
					</button>
				</div>
			</form>
			<%-- 	<table>
					<tbody>
						<tr>
							<td><fmt:message key="login-form.form.label.userLogin" /></td>
							<td><input type="text" name="j_username" id="j_username"
								size="30" maxlength="40" /></td>
						</tr>
						<tr>
							<td><fmt:message key="login-form.form.label.password" /></td>
							<td><input type="password" name="j_password" id="j_password"
								size="30" maxlength="32" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit"
								value="<fmt:message key="login-form.form.button.submit" />" /></td>
						</tr>
					</tbody>
				</table> --%>


			<%-- 			<p>
				<a href="${pageContext.request.contextPath}/index.html">Home
					page</a><br />
			</p>
 --%>
		</div>
	</div>
</body>
</html>