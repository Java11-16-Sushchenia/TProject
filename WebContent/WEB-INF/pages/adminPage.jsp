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
    <title>Admin Page</title>

    <!-- Bootstrap core CSS -->
 
    <script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>   
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/myStyle.css">

  <fmt:setLocale value="${sessionScope.local}"/>
  <fmt:setBundle basename="localization.local" var="loc"/>

  <fmt:message bundle="${loc}" key="local.title" var="title"/>
  <fmt:message bundle="${loc}" key="local.language.ru"  var="russianLanguage"/>
  <fmt:message bundle="${loc}" key="local.language.en"  var="englishLanguage"/>
  <fmt:message bundle="${loc}" key="local.language"  var="language"/>
  <fmt:message bundle="${loc}" key="local.signoutbutton" var="signoutbutton"/>
  
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
             	 	<input type="hidden" name="go_to_page" value="redirectToAdminPage"/>   
             	 	<input type="hidden" name="command" value="LOCALIZATION_COMMAND"/>          		
             		<input type="hidden" name="local" value="en"/>
             		<input type="submit" class="btn btn-default btn-xs form-control" value="${englishLanguage}"/>
             	</form>
             </li>
              <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToAdminPage"/>   
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
	 			Some content
	    </div>   
        <h2>Все пользователи</h2>
        
    <div class="col-sm-8 text-left">
	<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Логин</td>
				<td>Почта</td>
				<td>Роль</td>
				<td>Счет</td>
			</tr>		
		</thead>
			<c:forEach var="user" items="${users}" >
				<tr>	
    					<c:if test="${user.role == 'ADMIN' }">
    						<tr class="danger">			
    					</c:if>
    					<c:if test="${user.role == 'BOOKMAKER' }">
    						<tr class="warning">			
    					</c:if>
    					<c:if test="${user.role == 'USER' }">
    						<tr class="success">			
    					</c:if>
  				
  					  <td class="success">
  					  		${user.id}
  					  </td>					  
  					  <td >
  					  			${user.login}
  					  </td>					  
  					  <td>
  					  			${user.email}
  					  </td>					  
  					  <td>
  					  			${user.role}
  					  </td>					  
  					  <td>
  					  			${user.cash}
  					  </td>				  
					</tr>			
			</c:forEach>
		</table> 
	</div>	   
 </div>
 </div>
      <footer class="container-fluid text-center">
	  	 <p>Footer Text</p>
	  </footer>
  </body>
</html>