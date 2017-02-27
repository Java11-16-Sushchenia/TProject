<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<div class="container bg-warning">
	<div class="row">
  <div class="col-md-3">
	
		<form action="GetAllGamesServlet" method="get">	
			<input type="submit" class="btn btn-info" value="Список игр">	
		</form>	
		
		<form action="GetAllTeamsServlet" method="get">	
			<input type="submit" class="btn btn-info" value="Добавить игру">	
		</form>	
  </div>
  <div class="col-md-3">
	  <a href="RegisteredNewUser.jsp">
		 <button type="button" class="btn btn-warning">Регистрация</button>
	 </a>
  </div>
  
  <div class="col-md-6">    
  	<c:if test="${user == null}">
  		<p><c:out value="${userAuthorizationError}"> </c:out> </p>
  	
  		<form class="form-inline" action="AuthorizationUserServlet" method="get">
		  <div class="form-group">
		    <label class="sr-only" for="exampleInputEmail3">Логин</label>
		    <input name="login" type="text" class="form-control" placeholder="Логин">
		  </div>
		  <div class="form-group">
		    <label class="sr-only" for="exampleInputPassword3">Пароль</label>
		    <input name="password" type="password" class="form-control" id="exampleInputPassword3" placeholder="Пароль">
		  </div>		 
		  <button type="submit" class="btn btn-danger">Войти</button>
	</form>
  	
  	</c:if>  	
  		<c:if test="${user != null}">
  		<c:out value="${user.id}"></c:out>
  		<c:out value="${user.login}"></c:out>
  		<c:out value="${user.role}"></c:out>
  		<c:out value="${user.cash}"></c:out>
  	</c:if>      
  	 </div>

</body>
</html>