<%@page import="java.util.Date"%>
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
<title>BookMaker Page</title>

<!-- Bootstrap core CSS -->

<script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/myStyle.css">

<%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf"%>

</head>
<body>

	<div class="changegame-error-modal">
		<div class="alert alert-danger fade in">
			<a href="#" class="close" data-dismiss="alert">x</a> <strong
				class="changeGameErrorType"></strong>: <span
				class="changeGameErrorMessage"></span>
		</div>
	</div>

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
								<a href="#"
									onclick="setLanguage('redirectToBookmakerPage','ru');">${russianLanguage}</a>
								<a href="#"
									onclick="setLanguage('redirectToBookmakerPage','en');">${englishLanguage}</a>
							</div>
						</div>
					</div>
				</form>
			</c:if>
		</div>
	</nav>

	<!-- Content -->
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<ul class="pagination ul-nav">
					<li>
						<form action="Controller" method="get">
							<input type="hidden" name="command"
								value="GET_PAGE_WITH_GAMES_COMMAND" /> <input type="hidden"
								name="go_to_page" value="bookmakerPage" /> <a href="#"
								onclick="$(this).closest('form').submit();">${allgames}</a>
						</form>
					</li>
					<li>
						<form action="Controller" method="get">
							<input type="hidden" name="command"
								value="GET_PAGE_WITH_GAMES_COMMAND" /> <input type="hidden"
								name="gameKind" value="football" /> <input type="hidden"
								name="go_to_page" value="bookmakerPage" /> <a href="#"
								onclick="$(this).closest('form').submit();">${football}</a>
						</form>
					</li>
					<li>
						<form action="Controller" method="get">
							<input type="hidden" name="command"
								value="GET_PAGE_WITH_GAMES_COMMAND" /> <input type="hidden"
								name="gameKind" value="basketball" /> <input type="hidden"
								name="go_to_page" value="bookmakerPage" /> <a href="#"
								onclick="$(this).closest('form').submit();">${basketball}</a>
						</form>
					</li>
					<li>
						<form action="Controller" method="get">
							<input type="hidden" name="command"
								value="GET_PAGE_WITH_GAMES_COMMAND" /> <input type="hidden"
								name="gameKind" value="hockey" /> <input type="hidden"
								name="go_to_page" value="bookmakerPage" /> <a href="#"
								onclick="$(this).closest('form').submit();">${hockey}</a>
						</form>
					</li>
				</ul>
			</div>

			<div class="col-sm-8 text-left">
				<c:if test="${ gameKind == 'FOOTBALL' }">
					<h2>${ soccer }</h2>
				</c:if>

				<c:if test="${ gameKind == 'BASKETBALL' }">
					<h2>${ basketball }</h2>
				</c:if>

				<c:if test="${ gameKind == 'HOCKEY' }">
					<h2>${ hockey }</h2>
				</c:if>

				<c:if test="${ gameKind == null }">
					<h2>${allgames}</h2>
				</c:if>

				<div class="row">
					<div class="container">
						<c:if test="${empty games}">
							<h3>${nogames}</h3>
						</c:if>
						<c:if test="${not empty games}">

							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr>
										<td class="success">${gameid}</td>
										<td>${event}</td>
										<td>${time}</td>
										<td>${home}</td>
										<td>${draw}</td>
										<td>${away}</td>
									</tr>
								</thead>
								<c:forEach var="game" items="${games}">
									<tr id="gameRow-${game.id}">
										<td class="success">
											<div class="col-md-2">${game.id}</div>
										</td>
										<td>${game.firstTeam}-${game.secondTeam}</td>
										<td>${game.date}</td>
										<td class="col-sm-2">
											<div>
												<input id="k1-${game.id}"
													onchange="changeButtonColorCommitable(this);" type="number"
													min="1.1" step="0.1" value="${game.k1}">
											</div>
										</td>
										<td class="col-sm-2"><input id="kx-${game.id}"
											onchange="changeButtonColorCommitable(this);" type="number"
											min="1.1" step="0.1" value="${game.kx}"></td>
										<td class="col-sm-2"><input id="k2-${game.id}"
											onchange="changeButtonColorCommitable(this);" type="number"
											min="1.1" step="0.1" value="${game.k2}"></td>
										<td>
											<button onclick="commitChanges(this);"
												id="saveButton-${game.id}" type="submit"
												class="button signinbutton">${commit}</button>
										</td>
										<td>
											<button onclick="removeTableRowWithGame(this);"
												id="saveButton-${game.id}" type="submit"
												class="button signupbutton">${remove}</button>
										</td>
									</tr>
								</c:forEach>
							</table>
							<nav>
								<ul class="pagination ul-pagination">
									<c:if test="${currentPage != 1}">
										<li>
											<form action="Controller">
												<input type="hidden" name="command"
													value="GET_PAGE_WITH_GAMES_COMMAND" /> <input
													type="hidden" name="gameKind" value="${gameKind}" /> <input
													type="hidden" name="go_to_page" value="bookmakerPage" /> <input
													type="hidden" name="pageNumber" value="${currentPage - 1}" />
												<a href="#" onclick="$(this).closest('form').submit();">${previous}</a>
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
															value="GET_PAGE_WITH_GAMES_COMMAND" /> <input
															type="hidden" name="pageNumber" value="${i}" /> <input
															type="hidden" name="go_to_page" value="bookmakerPage" />
														<input type="hidden" name="gameKind" value="${gameKind}" />
														<a href="#" onclick="$(this).closest('form').submit();">${i}</a>
													</form>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${currentPage != noOfPages}">
										<li>
											<form action="Controller">
												<input type="hidden" name="command"
													value="GET_PAGE_WITH_GAMES_COMMAND" /> <input
													type="hidden" name="go_to_page" value="bookmakerPage" /> <input
													type="hidden" name="pageNumber" value="${currentPage + 1}" />
												<input type="hidden" name="gameKind" value="${gameKind}" />
												<a href="#" onclick="$(this).closest('form').submit();">${next}</a>
											</form>
										</li>
									</c:if>
									<c:if test="${currentPage == noOfPages}">
										<li class="disabled"><a href="#">${next}</a></li>
									</c:if>
								</ul>
							</nav>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center footer">
		<p>Epam 2017</p>
	</footer>

	<script>
		function changeButtonColorCommitable(inputObject) {
			var changedGameId = inputObject.id.split("-")[1];
			var changeButtonId = "#saveButton-" + changedGameId;
			$(changeButtonId).removeClass('signinbutton').addClass('commitbtn');
		}

		function changeButtonColorUnCommitable(inputObject) {
			var changedGameId = inputObject.id.split("-")[1];
			var changeButtonId = "#saveButton-" + changedGameId;
			$(changeButtonId).removeClass('commitbtn').addClass('signinbutton');
		}

		function commitChanges(buttonObject) {

			var changedGameId = buttonObject.id.split("-")[1];

			var k1Value = $("#k1-" + changedGameId).val();
			var kxValue = $("#kx-" + changedGameId).val();
			var k2Value = $("#k2-" + changedGameId).val();

			$.ajax({
				type : "POST",
				data : {
					command : "SET_NEW_GAME_COEFFICIENTS_AJAX_COMMAND",
					gameId : changedGameId,
					k1 : k1Value,
					kx : kxValue,
					k2 : k2Value,
				},
				url : "AJAXController",
				success : function(data) {
					var json = JSON.parse(data);

					var errorType = json["errorType"];
					var errorMessage = json["errorMessage"];
					debugger;
					if (errorType === "changegameerror") {
						$(".changeGameErrorType").text(errorType);
						$(".changeGameErrorMessage").text(errorMessage);
						$(".changnegame-error-modal").css("display", "block");

						setTimeout(
								function() {
									$(".changegame-error-modal").css("display",
											"none");
								}, 3000);

					} else if (errorType === "success") {
						changeButtonColorUnCommitable(buttonObject);
					}

				}
			});
		}

		function removeTableRowWithGame(buttonObject) {
			if (confirm("Are you realy want to remove game?")) {
				var changedGameId = buttonObject.id.split("-")[1];

				$.ajax({
					type : "POST",
					data : {
						command : "MAKE_GAME_INVISIBLE_AJAX_COMMAND",
						gameId : changedGameId
					},
					url : "AJAXController",
					success : function(data) {
						var json = JSON.parse(data);

						var errorType = json["errorType"];

						if (errorType === "success") {
							$("#gameRow-" + changedGameId).hide("fast");
						}

					}
				});
			}
		}
	</script>
	<script src="bootstrap-3.3.7-dist/js/myScripts.js"></script>
</body>
</html>