<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>User Page</title>

<!-- Bootstrap core CSS -->

<script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/myStyle.css">

<%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf"%>

</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="redirectToIndexPage">${title}</a>
			</div>

			<c:if test="${user != null }">
				<form action="Controller" method="get"
					class="navbar-form navbar-right authorize-user-form">
					<input type="hidden" name="command"
						value="AUTHORIZATION_USER_SIGN_OUT_COMMAND" />
					<button type="button" class="button btn-primary form-control"
						onclick="redirectToUserPersonalPage();">${user.email}
						<span class="badge">${user.cash}</span>
					</button>
					<input class="button signupbutton form-control" type="submit"
						value="${signoutbutton}" />
					<div class="form-group">
						<div class="dropdown">
							<input type="button" class="button dropbtn" value="${language}" />
							<div class="dropdown-content">
								<a href="#" onclick="setLanguage('redirectToUserPage','ru');">${russianLanguage}</a>
								<a href="#" onclick="setLanguage('redirectToUserPage','en');">${englishLanguage}</a>
							</div>
						</div>
					</div>
				</form>
			</c:if>
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">

		<c:if test="${empty rates}">
			<h1>${norates}</h1>
		</c:if>

		<c:if test="${not empty rates}">
			<div class="container">
				<h2>${yourrates}</h2>

				<div class="container">
					<table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<td class="success">${id}</td>
								<td>${firstteam}</td>
								<td>${secondteam}</td>
								<td>${ratedate}</td>
								<td>${ratemoney}</td>
								<td>${choice}</td>
								<td>${coefficient}</td>
								<td>${ratepofit}</td>
								<td>${ratestatus}</td>

							</tr>
						</thead>
						<c:forEach var="rate" items="${rates}">
							<tr>
								<td class="success">${rate.id}</td>
								<td>${rate.game.firstTeam}</td>
								<td>${rate.game.secondTeam}</td>
								<td>${rate.date}</td>
								<td>${rate.money}</td>
								<td>${rate.choice}</td>
								<td>${rate.gameCoefficient}</td>
								<td>${rate.profit}</td>
								<td><c:if test="${rate.state == -1}">${notplayed}</c:if> <c:if
										test="${rate.state == 0}">${defeat}</c:if> <c:if
										test="${rate.state == 1}">${victory}</c:if></td>
							</tr>
						</c:forEach>
					</table>

					<nav>
						<ul class="pagination ul-pagination">
							<c:if test="${currentPage != 1}">
								<li>
									<form action="Controller">
										<input type="hidden" name="command"
											value="GET_PAGE_WITH_RATES_COMMAND" /> <input type="hidden"
											name="go_to_page" value="userPage" /> <input type="hidden"
											name="pageNumber" value="${currentPage - 1}" /> <a href="#"
											onclick="$(this).closest('form').submit();">${previous}</a>
									</form>
								</li>
							</c:if>
							<c:if test="${currentPage == 1}">
								<li class="disabled"><a href="#">${previous}</a></li>
							</c:if>

							<c:forEach begin="1" end="${noOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<li class="active"><a href="#">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li>
											<form action="Controller">
												<input type="hidden" name="command"
													value="GET_PAGE_WITH_RATES_COMMAND" /> <input
													type="hidden" name="pageNumber" value="${i}" /> <input
													type="hidden" name="go_to_page" value="userPage" /> <a
													href="#" onclick="$(this).closest('form').submit();">${i}</a>
											</form>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test="${currentPage != noOfPages}">
								<li>
									<form action="Controller">
										<input type="hidden" name="command"
											value="GET_PAGE_WITH_RATES_COMMAND" /> <input type="hidden"
											name="go_to_page" value="userPage" /> <input type="hidden"
											name="pageNumber" value="${currentPage + 1}" /> <a href="#"
											onclick="$(this).closest('form').submit();">${next}</a>
									</form>
								</li>
							</c:if>
							<c:if test="${currentPage == noOfPages}">
								<li class="disabled"><a href="#">${next}</a></li>
							</c:if>
						</ul>
					</nav>


				</div>
			</div>
		</c:if>
	</div>

	<footer class="container-fluid text-center footer">
		<p>Epam 2017</p>
	</footer>
	<script src="bootstrap-3.3.7-dist/js/myScripts.js"></script>
</body>
</html>