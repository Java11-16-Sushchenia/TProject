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
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="redirectToIndexPage">${title}</a>
    </div>
     
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

      <ul class="nav navbar-nav navbar-right">      
     
     	<c:if test="${user != null }">   			
   			  <form action="Controller" method="get" class="navbar-form navbar-left"> 
   			   <input type="hidden" name="command" value="log_out_command"/>
   			  		<input class="navbar-form navbar-right" type="submit" value="${signoutbutton}" /> 
   			          <a href="redirectToPersonalPage"><span class="label label-primary navbar-form navbar-left">${user.email}</span></a>    	   
   			  </form>
   	    </c:if>
  
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${language} <span class="caret"></span></a>
          <ul class="dropdown-menu">
      		 <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToBookmakerPage"/>   
             	 	<input type="hidden" name="command" value="LOCALIZATION_COMMAND"/>          		
             		<input type="hidden" name="local" value="en"/>
             		<input type="submit" class="btn btn-default btn-xs form-control" value="${englishLanguage}"/>
             	</form>
             </li>
              <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToBookmakerPage"/>   
             	 	<input type="hidden" name="command" value="LOCALIZATION_COMMAND"/>          		
             		<input type="hidden" name="local" value="ru"/>
             		<input type="submit" class="btn btn-default btn-xs form-control" value="${russianLanguage}"/>
             	</form>
             </li>
          </ul>
        </li>        
      </ul>
    </div>
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
					  		<input type="text" value="${game.k1}">
					   </div>
				  </td>
				  <td class="col-sm-2">
				  			<input type="text" value="${game.kx}">
				  </td>
				  <td class="col-sm-2">
				  			<input type="text" value="${game.k2}">
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

        <p><a href="redirectToAddNewGamePage" class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-plus"/>${addnewgame}</a></p>
      </div>     
    </div>
   </div>
    <footer class="container-fluid text-center">
	  <p>Epam 2017</p>
	</footer>
  </body>
</html>