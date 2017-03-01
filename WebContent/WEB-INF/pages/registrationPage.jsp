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
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    
        <c:if test="${userRegistrationError == 'emptylogin'}">          
 		 <span class="col-md-12 label label-danger">${emptylogin}</span>    
   		</c:if> 
   		<c:if test="${userRegistrationError == 'emptyemail'}">          
 		 <span class="col-md-12 label label-danger">${emptyemail}</span>    
   		</c:if> 
   		 <c:if test="${userRegistrationError == 'emptypassword'}">          
 		 <span class="col-md-12 label label-danger">${emptypassword}</span>    
   		</c:if> 
   		 <c:if test="${userRegistrationError == 'loginalreadyexists'}">          
 		 <span class="col-md-12 label label-danger">${loginalreadyexists}</span>    
   		</c:if> 
   		 <c:if test="${userRegistrationError == 'emailalreadyexists'}">          
 		 <span class="col-md-12 label label-danger">${emailalreadyexists}</span>    
   		</c:if>

   		
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
           <a class="navbar-brand" href="redirectToIndexPage">На главную</a>
          <a class="navbar-brand" href="#">Регистрация нового пользователя</a>
        </div>
        <div class="navbar-collapse collapse">
          <form action="Controller" method="get"  class="navbar-form navbar-right">       

          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h2>Заполните информацию о себе</h2>
        
		    <div class="container">
		        <form action="Controller" method="get" >
				<div class="form-group row">
				  <input type="hidden" name="command" value="registration_user_command"/>
				  <label for="example-text-input" class="col-2 col-form-label">Логин</label>
				  <div class="col-10">
				    <input name="login" class="form-control" type="text" placeholder="Введите логин" id="example-text-input">
				  </div>
				</div>
		
				<div class="form-group row">
				  <label for="example-email-input" class="col-2 col-form-label">Электронная почта</label>
				  <div class="col-10">
				    <input name="email" class="form-control" type="email" placeholder="asuschenya@gmail.com" id="example-email-input">
				  </div>
				</div>
			
				<div class="form-group row">
				  <label for="example-password-input" class="col-2 col-form-label">Password</label>
				  <div class="col-10">
				    <input name="password" class="form-control" type="password" placeholder="password" id="example-password-input">
				  </div>
				</div>
				  <button type="submit" class="btn btn-success">Зарегистрироваться</button>
				</form>
			</div>       
       
         
      </div>
    </div>
    
  </body>
</html>