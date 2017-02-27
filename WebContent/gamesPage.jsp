<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyJSP</title>
</head>
<body>
	<h1>Some JSTL Learn</h1>
	
	<c:set var="a" scope="session" value="<%=new  Random().nextInt(255) %>"></c:set>
	
	<c:if test="${a % 2 == 0}">
		<p align="center"
		 style="color:rgb(<%=new Random().nextInt(255) %>,<%=new  Random().nextInt(255)%>,<%=new  Random().nextInt(255)%>);font-size:<%=new  Random().nextInt(100)%>px">
		
		<c:out value="${a}"></c:out>			
			</p>
		</c:if>
	
		<c:if test="${a %2 != 0}">
		<p align="right"
		 style="color:rgb(<%=new Random().nextInt(255) %>,<%=new  Random().nextInt(255)%>,<%=new  Random().nextInt(255)%>);font-size:<%=new  Random().nextInt(100)%>px">
		
		<c:out value="${a}"></c:out>			
	</p>
	</c:if>	
		<br>
		 <br>
			
	<div class="container">
	<table class="table table-striped table-hover table-bordered">
	<thead>
		<td class="success">Номер</td>
		<td>Первая команда</td>
		<td>Вторая команда</td>
		<td>Дата</td>
		<td>Победа K1</td>
		<td>Ничья KX</td>
		<td>Победа K2</td>
	</thead>
		<c:forEach var="game" items="${games}" >	 		
				<tr>
				  <td class="success">
				  			<c:out value="${game.id}"></c:out>
				  </td>
				  
				  <td >
				  			<c:out value="${game.firstTeam}"></c:out>
				  </td>
				  
				  <td>
				  			<c:out value="${game.secondTeam}"></c:out><br>
				  </td>
				  
				  <td>
				  			<c:out value="${game.date}"></c:out>
				  </td>
				    <td>
				  			<c:out value="${game.k1}"></c:out>
				  </td>
				    <td>
				  			<c:out value="${game.kx}"></c:out>
				  </td>
				    <td>
				  			<c:out value="${game.k2}"></c:out>
				  </td>
				</tr>			
		</c:forEach>
		</table> 
	</div>	
</body>
</html>