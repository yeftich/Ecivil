<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of corresponding property in bean object"%>
<%@ attribute name="label" required="true" rtexprvalue="true"
	description="Label appears in red color if input is considered as invalid after submission"%>
<%@ attribute name="names" required="true" rtexprvalue="true"
	type="java.util.List" description="Names in the list"%>

<spring:bind path="${name}">
	<c:set var="cssGroup"
		value="control-group ${status.error ? 'error' : '' }" />
	<div class="${cssGroup}">
		<label class="control-label">${label}</label>

		<div class="controls">
			<form:select path="${name}">
				<form:option value="-" label="-- Select --">
					<form:options items="${names}"></form:options>
				</form:option>
			</form:select>
			<span class="help-inline">${status.errorMessage}</span>
		</div>
	</div>
</spring:bind>