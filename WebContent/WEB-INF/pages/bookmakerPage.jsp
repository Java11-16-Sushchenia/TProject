<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
  uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/myStyle.css">
    	  
 		 <%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf" %>

  </head>
  <body>    
  
  <nav class="navbar navbar-default">
  	<div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="redirectToIndexPage">${title}</a>
	    </div>

		<c:if test="${user != null }">   			
 			  <form action="Controller" method="get" class="navbar-form navbar-right authorize-user-form"> 
 			 	    <input type="hidden" name="command" value="log_out_command"/>
 			 	    	<button type="button" class="button btn-primary form-control" onclick="redirectToUserPersonalPage();">${user.email} <span class="badge">${user.cash}</span></button>
 			  		<input class="button signupbutton form-control" type="submit" value="${signoutbutton}" /> 
 			  	<div class="form-group">
		 	       <div class="dropdown">
			           <button class="button dropbtn">${language}</button>
			           <div class="dropdown-content">		           		
			                <a href="#" onclick="setLanguage('redirectToIndexPage','ru');">${russianLanguage}</a>
			                <a href="#" onclick="setLanguage('redirectToIndexPage','en');">${englishLanguage}</a>
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
				  	  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
					  <input type="hidden" name="go_to_page" value="bookmakerPage"/>  
				  	  <a href="#" onclick="$(this).closest('form').submit();">${allgames}</a>
				  </form>
			  </li>
			  <li >
		  		  <form action="Controller" method="get">
					  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
					  <input type="hidden" name="gameKind" value="football"/>
					  <input type="hidden" name="go_to_page" value="bookmakerPage"/> 
				  	<a href="#" onclick="$(this).closest('form').submit();">${football}</a>
				  </form>
			  </li>
			  <li>
		  		  <form action="Controller" method="get">
					  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
					  <input type="hidden" name="gameKind" value="basketball"/>
					  <input type="hidden" name="go_to_page" value="bookmakerPage"/> 
					  	<a href="#" onclick="$(this).closest('form').submit();">${basketball}</a>
				  </form>
			</li>
			  <li>
	  	  		  <form action="Controller" method="get">
					  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
					  <input type="hidden" name="gameKind" value="hockey"/>
					  <input type="hidden" name="go_to_page" value="bookmakerPage"/> 
					  	<a href="#" onclick="$(this).closest('form').submit();">${hockey}</a>
				  </form>
			  </li>
			</ul>
     </div>
  
      <div class="col-sm-8 text-left">
      <c:if test="${ game_kind == 'FOOTBALL' }">
      	<h2>${ soccer }</h2>
      </c:if>
      
      <c:if test="${ game_kind == 'BASKETBALL' }">
		<h2>${ basketball }</h2>
	</c:if>
	
	 <c:if test="${ game_kind == 'HOCKEY' }">
		<h2>${ hockey }</h2>
	</c:if>
	
      <c:if test="${ game_kind == null }">
		<h2>${allgames}</h2>
	</c:if>        
        
     <div class="container">
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
		<c:forEach var="game" items="${games}" >	 		
				<tr>			
					  <td class="success">
					  <div class="col-md-2">
					  		${game.id}
					  </div>
					  </td>				  
				  <td >
				  			${game.firstTeam} - ${game.secondTeam}
				  </td>				  
				  <td>
				            ${game.date}
				  </td>
				  <td class="col-sm-2">
					  	<div >
					  		<input id="k1-${game.id}" onchange="changeButtonColor(this);" type="number" min="1.1" value="${game.k1}">
					   </div>
				  </td>
				  <td class="col-sm-2">
				  			<input  id="kx-${game.id}" onchange="changeButtonColor(this);" type="number" min="1.1" value="${game.kx}">
				  </td>
				  <td class="col-sm-2">
				  			<input  id="k2-${game.id}" onchange="changeButtonColor(this);" type="number" min="1.1" value="${game.k2}">
				  </td>
				  <td>
				  	 	<button onclick="commitChanges(this);" id="saveButton-${game.id}" type="submit" class="button signinbutton">Сохранить</button>
				  </td>
				  <td>
				  	 	<button onclick="commitChanges(this);" id="saveButton-${game.id}" type="submit" class="button signщгеbutton">Удалить</button>
				  </td>
				</tr>			
		</c:forEach>
		</table> 
		<nav>
		  <ul class="pagination ul-pagination">  
		  	 <c:if test="${currentPage != 1}">
   				<li>
					<form action="Controller">
						<input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
					    <input type="hidden" name="gameKind" value="${gameKind}"/>		
						<input type="hidden" name="go_to_page" value="bookmakerPage"/>		
						<input type="hidden" name="pageNumber" value="${currentPage - 1}"/>
						<a href="#" onclick="$(this).closest('form').submit();">Previous</a>
					</form>
				</li>
			</c:if>
		    <c:if test="${currentPage == 1}">
   				<li class="disabled">
					<a href="#">Previous</a>
				</li>
			</c:if>
				
				<c:forEach begin="1" end="${noOfPages}" var="i">
					 <c:choose>
						<c:when test="${currentPage eq i}">
							 <li class="active"><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li>
								<form action="Controller">
									<input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
									<input type="hidden" name="pageNumber" value="${i}"/>	
									<input type="hidden" name="go_to_page" value="bookmakerPage"/>		
									<input type="hidden" name="gameKind" value="${gameKind}"/>							
									<a href="#" onclick="$(this).closest('form').submit();">${i}</a>
								</form>
							</li>
						</c:otherwise>
					</c:choose> 	
				</c:forEach>
			    	
			  <c:if test="${currentPage != noOfPages}">		    
   				<li>
					<form action="Controller">
						<input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>	
						<input type="hidden" name="go_to_page" value="bookmakerPage"/>	
						<input type="hidden" name="pageNumber" value="${currentPage + 1}"/>
					    <input type="hidden" name="gameKind" value="${gameKind}"/>	
						<a href="#" onclick="$(this).closest('form').submit();">Next</a>
					</form>
				</li>
				</c:if>
			  <c:if test="${currentPage == noOfPages}">		    
   				<li class="disabled">
						<a href="#">Next</a>
				</li>
				</c:if>
		  </ul>
		</nav>
	</div>	


      </div>     
    </div>
   </div>
    <footer class="container-fluid text-center">
	  <p>Epam 2017</p>
	</footer>
	
	<script>
		function changeButtonColor(inputObject){
			var changedGameId = inputObject.id.split("-")[1];			
			var changeButtonId = "#saveButton-"+changedGameId;
			$(changeButtonId).removeClass('signinbutton').addClass('commitbtn');
		}
		
		function commitChanges(buttonObject){
			var changedGameId = buttonObject.id.split("-")[1];
			
			var k1Value = $("#k1-"+changedGameId).val();
			var kxValue = $("#kx-"+changedGameId).val();
			var k2Value = $("#k2-"+changedGameId).val();
			debugger;
		}
	</script>
  </body>
</html>