<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Registration</title>

<script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/myStyle.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/validationStyles.css">

<%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf"%>

</head>
<body>
	<div class="error-modal">
		<div class="alert alert-danger fade in">
			<a href="#" class="close" data-dismiss="alert">x</a> <strong
				class="registrationErrorType"></strong>: <span
				class="registrationErrorMessage"></span>
		</div>
	</div>
	<div class="success-modal">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert">x</a> <strong
				class="authorizationErrorType">${registrationsuccess}</strong>: <span
				class="authorizationErrorMessage"></span>
		</div>
	</div>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="redirectToIndexPage">${title}</a>
			</div>
			<form action="Controller" method="get"
				class="navbar-form navbar-right">
				<div class="form-group">
					<div class="dropdown">
						<button class="button dropbtn">${language}</button>
						<div class="dropdown-content">
							<a href="#" onclick="setLanguage('registrationPage','ru');">${russianLanguage}</a>
							<a href="#" onclick="setLanguage('registrationPage','en');">${englishLanguage}</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</nav>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<h3>Left Side</h3>
			</div>
			<div class="col-sm-10 text-left">
				<div class="container">
					<h2>Registration</h2>
					<form class="form-horizontal"
						<%--   onsubmit="return validateForm();"--%> id="registrationForm"
						name="registrationForm">
						<div class="form-group">
							<label class="control-label col-sm-2" for="login">${login}:</label>
							<div class="col-sm-10">
								<input type="text" name="login" class="form-control" id="login"
									placeholder="${login}" pattern="^[a-zA-Z][a-z0-9_]{5,15}$"
									title="More than 6 symbols (Letters, numbers or _)" required>
								<span class="validation-error-span"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="email">${email}:</label>
							<div class="col-sm-9">
								<input type="email" name="email" class="form-control" id="email"
									placeholder="${email}"
									pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
									required> <span id="myForm"></span>
							</div>
							<div class="col-sm-1">
								<button type="button" class="button signinbutton"
									onclick="addEmailFunction();">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">${password}:</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="pwd" placeholder="${password}"
									pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})"
									title="More tham 6 symbols" required> <span></span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="confirmpwd">${confirmpassword}:</label>
							<div class="col-sm-10">
								<input type="password" name="confirmPassword"
									class="form-control" id="confirmpwd"
									placeholder="${confirmpassword}"
									pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})"
									title="Confirm title" required> <span
									class="validation-error-span" id="passwordError"></span>
							</div>
						</div>


						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-8">
								<button type="submit" class="btn btn-default">${userregistration}</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<footer class="container-fluid text-center">
		<p>Footer Text</p>
	</footer>

	<script type="text/javascript"
		src="bootstrap-3.3.7-dist/js/validation.js"></script>
	<script>
		$("#registrationForm")
				.submit(
						function(event) {
							if (validateForm() === true) {
								var registrationData = event.currentTarget;

								var login = registrationData["login"].value;
								var email = registrationData["email"].value;
								var password = registrationData["pwd"].value;
								debugger;
								$
										.ajax({
											type : "POST",
											data : {
												command : "REGISTRATION_USER_AJAX_COMMAND",
												login : login,
												email : email,
												password : password
											},
											url : "AJAXController",
											success : function(data) {
												debugger;
												var json = JSON.parse(data);
												var errorType = json["errorType"];
												var errorMessage = json["errorMessage"];

												if (errorType === "registrationerror") {
													errorType = "${registrationerror}";

													if (errorMessage === "userexists") {
														errorMessage = "${loginalreadyexists}";

														$(
																".registrationErrorType")
																.text(errorType);
														$(
																".registrationErrorMessage")
																.text(
																		errorMessage);

														$(".error-modal").css(
																"display",
																"block");
														$("#login").val("");

														setTimeout(
																function() {
																	$(
																			".error-modal")
																			.css(
																					"display",
																					"none");
																}, 3000);

													} else if (errorMessage === "emailexists") {
														errorMessage = "${emailalreadyexists}";

														$(
																".registrationErrorType")
																.text(errorType);
														$(
																".registrationErrorMessage")
																.text(
																		errorMessage);

														$(".error-modal").css(
																"display",
																"block");
														$("#email").val("");
														setTimeout(
																function() {
																	$(
																			".error-modal")
																			.css(
																					"display",
																					"none");
																}, 3000);
													}
												} else if (errorType === "success") {
													$(".success-modal").css(
															"display", "block");
													setTimeout(
															function() {
																window.location
																		.replace("redirectToIndexPage");
															}, 3000);
												}
											}
										});
							}
							event.preventDefault();
						});
	</script>
	<script src="bootstrap-3.3.7-dist/js/myScripts.js"></script>
</body>
</html>
