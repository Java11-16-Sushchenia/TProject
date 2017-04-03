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
<title>Admin Page</title>

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
					<button data-toggle="modal" data-target="#myModal" type="button"
						class="button infobutton form-control">${user.email}
						<span class="badge">${user.cash}</span>
					</button>



					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Modal Header</h4>
								</div>
								<div class="modal-body">
									<p>Some text in the modal.</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>

						</div>
					</div>

					<input class="button signupbutton form-control" type="submit"
						value="${signoutbutton}" />
					<div class="form-group">
						<div class="dropdown">
							<button class="button dropbtn">${language}</button>
							<div class="dropdown-content">
								<a href="#" onclick="setLanguage('redirectToAdminPage','ru');">${russianLanguage}</a>
								<a href="#" onclick="setLanguage('redirectToAdminPage','en');">${englishLanguage}</a>
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

							<a href="#">${allgames}</a>
						</form>
					</li>
					<li>
						<form action="Controller" method="get">

							<a href="#">${football}</a>
						</form>
					</li>
					<li>
						<form action="Controller" method="get">

							<a href="#">${basketball}</a>
						</form>
					</li>
					<li>
						<form action="Controller" method="get">

							<a href="#">${hockey}</a>
						</form>
					</li>
				</ul>
			</div>

			<div class="col-sm-8 text-left">

				<div class="container">
					<table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<td>${id}</td>
								<td>${login}</td>
								<td>${email}</td>
								<td>${role}</td>
								<td>${cash}</td>
							</tr>
						</thead>
						<c:forEach var="user" items="${users}">
							<c:if test="${user.isVisible == 0}">
								<tr id="userRow-${user.id}" class="danger">
							</c:if>
							<c:if test="${user.isVisible != 0}">
								<tr id="userRow-${user.id}">
							</c:if>
							<td>
								<div>${user.isVisible}</div>
							</td>
							<td>${user.login}</td>
							<td>${user.email}</td>
							<td>${user.role}</td>
							<td>${user.cash}</td>

							<td><c:if test="${user.isVisible == 0}">
									<button onclick="unblockUser(this);"
										id="unblockButton-${user.id}" type="submit"
										class="button signinbutton">Разблокировать</button>
							</c:if>
							<c:if test="${user.isVisible == 1}">
								<button onclick="blockUser(this);" id="blockButton-${user.id}"
									type="submit" class="button signupbutton">Блокировать</button>
							</c:if>
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
											value="GET_PAGE_WITH_USERS_COMMAND" /> <input type="hidden"
											name="go_to_page" value="adminPage" /> <input type="hidden"
											name="pageNumber" value="${currentPage - 1}" /> <a href="#"
											onclick="$(this).closest('form').submit();">Previous</a>
									</form>
								</li>
							</c:if>
							<c:if test="${currentPage == 1}">
								<li class="disabled"><a href="#">Previous</a></li>
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
													value="GET_PAGE_WITH_USERS_COMMAND" /> <input
													type="hidden" name="pageNumber" value="${i}" /> <input
													type="hidden" name="go_to_page" value="adminPage" /> <a
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
											value="GET_PAGE_WITH_USERS_COMMAND" /> <input type="hidden"
											name="go_to_page" value="adminPage" /> <input type="hidden"
											name="pageNumber" value="${currentPage + 1}" /> <a href="#"
											onclick="$(this).closest('form').submit();">Next</a>
									</form>
								</li>
							</c:if>
							<c:if test="${currentPage == noOfPages}">
								<li class="disabled"><a href="#">Next</a></li>
							</c:if>
						</ul>
						<button class="button signinbutton"
							onclick="window.location.replace('redirectToAddNewGamePage');">add
							new game</button>
					</nav>
				</div>
			</div>

		</div>
	</div>


	<footer class="container-fluid text-center">
		<p>Epam 2017</p>
	</footer>

	<script>
		function blockUser(buttonObject) {
			var blockUserId = buttonObject.id.split("-")[1];

			$.ajax({
				type : "POST",
				data : {
					command : "BLOCK_USER_AJAX_COMMAND",
					user : blockUserId
				},
				url : "AJAXController",
				success : function(data) {
					/*changeButtonColorUnCommitable(buttonObject);*/
					var json = JSON.parse(data);
					location.reload();
				}
			});
		}

		function unblockUser(buttonObject) {
			var unblockUserId = buttonObject.id.split("-")[1];

			$.ajax({
				type : "POST",
				data : {
					command : "UNBLOCK_USER_AJAX_COMMAND",
					user : unblockUserId
				},
				url : "AJAXController",
				success : function(data) {
					var json = JSON.parse(data);
					location.reload();
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
						$("#gameRow-" + changedGameId).hide();
					}
				});
			}
		}
	</script>
	<script src="bootstrap-3.3.7-dist/js/myScripts.js"></script>
</body>
</html>