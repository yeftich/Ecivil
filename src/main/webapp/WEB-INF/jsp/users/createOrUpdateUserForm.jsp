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
<script type="text/javascript">
	$(document).ready(
			function() {

				toggleFields();

				$("#volunteer").change(function() {
					toggleFields();
				});

				$('#add-user-form').validate(
						{
							rules : {
								login : {
									minlength : 4,
									required : true
								},
								password : {
									minlength : 4,
									required : true
								},
								googleAccount : {
									email : true,
									required : true
								},
								firstName : "required",
								lastName : "required",
								telephone : {
									number : true,
									minlength : 9,
									required : true
								},
								policy : "required"
							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							},
							success : function(element) {
								element.text('OK!').addClass('valid').closest(
										'.control-group').removeClass('error')
										.addClass('success');
							}
						});

			});

	function toggleFields() {
		if ($("#volunteer").is(':checked'))
			$("#teamsDiv").show();
		else
			$("#teamsDiv").hide();
	}
</script>

<body>
	<div class="container-fluid">

		<div class="masthead">
			<jsp:include page="../fragments/header.jsp" />
		</div>
		<jsp:include page="../fragments/navBar.jsp" />

		<c:choose>
			<c:when test="${user['new']}">
				<c:set var="method" value="post" />
			</c:when>
			<c:otherwise>
				<c:set var="method" value="put" />
			</c:otherwise>
		</c:choose>

		<h3>
			<c:if test="${user['new']}">New </c:if>
			User
		</h3>



		<form:form modelAttribute="user" method="${method}"
			class="form-horizontal" id="add-user-form">
			<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.userLogin" /> </label>

				<div class="controls">
					<form:input path="login" size="30" maxlength="40" />
					<span class="help-inline"><form:errors path="login" /></span>
				</div>
			</div>


			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.password" /></label>
				<div class="controls">
					<form:password path="password" size="30" maxlength="32" />
					<span class="help-inline"><form:errors path="password" /></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.googleAccount" /></label>
				<div class="controls">
					<form:input path="googleAccount" size="30" />
					<span class="help-inline"><form:errors path="googleAccount" /></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.firstName" /></label>
				<div class="controls">
					<form:input path="firstName" size="30" />
					<span class="help-inline"><form:errors path="firstName" /></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.lastName" /></label>
				<div class="controls">
					<form:input path="lastName" size="30" />
					<span class="help-inline"><form:errors path="lastName" /></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.address" /></label>
				<div class="controls">
					<form:input path="address" size="30" />
					<span class="help-inline"><form:errors path="address" /></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.city" /></label>
				<div class="controls">
					<form:input path="city" size="30" />
					<span class="help-inline"><form:errors path="city" /></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.telephone" /></label>
				<div class="controls">
					<form:input path="telephone" size="30" />
					<span class="help-inline"><form:errors path="telephone" /></span>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<label class="checkbox"><fmt:message
							key="createOrUpdateUserForm.form.label.volunteer" /> <input
						id="volunteer" type="checkbox" name="volunteer" /> </label>
				</div>
			</div>

			<div id="teamsDiv" class="control-group">
				<label class="control-label"><fmt:message
						key="createOrUpdateUserForm.form.label.userTeams" /></label>
				<div class="controls">
					<form:select path="userTeams" items="${teamList}" multiple="true" />
					<span class="help-inline"><form:errors path="userTeams" /></span>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<label class="checkbox"><fmt:message
							key="createOrUpdateUserForm.form.label.policy" /> <input
						id="policy" type="checkbox" name="policy" /> </label>
				</div>
			</div>

			<c:choose>
				<c:when test="${user['new']}">
					<button type="submit" class="btn btn-success">
						<fmt:message
							key="createOrUpdateUserForm.form.button.submit.add.user" />
					</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-primary">
						<fmt:message
							key="createOrUpdateUserForm.form.button.submit.update.user" />
					</button>
				</c:otherwise>
			</c:choose>
		</form:form>
		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
