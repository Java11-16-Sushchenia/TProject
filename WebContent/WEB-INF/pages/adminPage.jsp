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
 			 	    	<button type="button" class="button btn-primary form-control" onclick="redirectToUserPersonalPage();">${user.email} <span class="badge">totalizator cash:${user.cash}</span></button>
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
	        <p><a href="redirectToAddNewGamePage" class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-plus"/>${addnewgame}</a></p>	   
 </div>
 </div>
      <footer class="container-fluid text-center">
	  	 <p>Footer Text</p>
	  </footer>
  </body>
</html>