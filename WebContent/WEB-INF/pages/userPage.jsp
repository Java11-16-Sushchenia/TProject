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
    <title>User Page</title>

    <!-- Bootstrap core CSS -->
 
    <script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>   
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/myStyle.css">
    	
  <%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf" %>

  </head>
  <body>   
   <nav class="navbar navbar-default">
 	 <div class="container-fluid">
  
	     <c:if test="${userAuthorizationError != null}">          
	   		 <span class="col-md-12 label label-danger"><c:out value="${userAuthorizationError}"></c:out></span>    
	     </c:if> 

    
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
      
          <c:if test="${user == null }">
        	<c:redirect url="/Controller">
				<c:param name="command" value="GET_GAMES_BY_KIND_COMMAND"/>		
				<c:param name="game_kind" value="FOOTBALL"/>		
				<c:param name="go_to_page" value="index"/>
			</c:redirect>
         </c:if>        
     	<c:if test="${user != null }">   	
     	
     	<li>   <a href="redirectToPersonalPage"><span class="label label-primary form-control">${user.email}</span></a></li>		
   		<li>   			   			 
<!-- Large modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".fade">Large modal</button>

		<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		     		${usercashinfo}
		      </div>
		      <div class="modal-body">
				${currentcashstate} : $${user.cash}
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">${closemodal}</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
   		</li>	
   		   			 
   		<li> 
	   		 <form action="Controller" method="get" class="navbar-form navbar-left"> 
			   		<input type="hidden" name="command" value="log_out_command"/>
			  		<input class="navbar-form navbar-right" type="submit" value="${signoutbutton}" />    			       	   
			  </form>
   		</li>
   	    </c:if>   
       
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${language} <span class="caret"></span></a>
          <ul class="dropdown-menu">
      		 <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToUserPage"/>   
             	 	<input type="hidden" name="command" value="LOCALIZATION_COMMAND"/>          		
             		<input type="hidden" name="local" value="en"/>
             		<input type="submit" class="btn btn-default btn-xs form-control" value="${englishLanguage}"/>
             	</form>
             </li>
              <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToUserPage"/>   
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
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    
    <c:if test="${rates == null}">
    	<h1>${norates}</h1>    
    </c:if>
    
     <c:if test="${rates != null}">
      <div class="container">
        <h2>${yourrates}</h2>         
        
       <div class="container">
		<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<td class="success">Номер</td>
				<td>Команда 1</td>
				<td>Команда 2</td>
				<td>Дата ставки</td>
				<td>Сумма ставки</td>
				<td>Выбор</td>
				<td>Коэффициент</td>
				<td>Выигрыш</td>
				<td>Статус</td>
			</tr>
		</thead>
			<c:forEach var="rate" items="${rates}" >	 		
					<tr>
					  <td class="success">
					  		<c:out value="${rate.id}"></c:out>
					  </td>				  
					  <td>
					  		<c:out value="${rate.game.firstTeam}"></c:out>
					  </td>				  
					  <td>
					  		<c:out value="${rate.game.secondTeam}"></c:out>
					  </td>	
					  <td>
					  		<c:out value="${rate.date}"></c:out>
					  </td>		
					  <td>
					  		<c:out value="${rate.money}"></c:out>
					  </td>	
					  <td>
					  		<c:out value="${rate.choice}"></c:out>
					  </td>		 
					  <td>
					  		<c:out value="${rate.gameCoefficient}"></c:out>
					  </td>	
					 <td>
					  		<c:out value="${rate.profit}"></c:out>
					  </td>	
					   <td>
					  		<c:out value="${rate.isSuccess}"></c:out>
					  </td>					
					</tr>			
			</c:forEach>
			</table> 
	</div>	
 
        <p><a class="btn btn-primary btn-lg" role="button">Сделать ставку</a></p>
      </div>
      </c:if> 
    </div>
    
    <footer class="container-fluid text-center">
  <p>Epam 2017</p>
</footer>
  </body>
</html>