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
    <meta name="authorr" content="">
    <title>Add New Game</title>

    <!-- Bootstrap core CSS -->

    <script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>   
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/myStyle.css">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

  
  <fmt:setLocale value="${sessionScope.local}"/>
  <fmt:setBundle basename="localization.local" var="loc"/>
  
  <fmt:message bundle="${loc}" key="local.title" var="title"/>
  <fmt:message bundle="${loc}" key="local.signinbutton" var="signinbutton"/>
  <fmt:message bundle="${loc}" key="local.signupbutton" var="signupbutton"/>
  <fmt:message bundle="${loc}" key="local.signoutbutton" var="signoutbutton"/>
  <fmt:message bundle="${loc}" key="local.language.ru"  var="russianLanguage"/>
  <fmt:message bundle="${loc}" key="local.language.en"  var="englishLanguage"/>
  <fmt:message bundle="${loc}" key="local.language"  var="language"/>
  
  <fmt:message bundle="${loc}" key="local.placeholder.login" var="login"/>
  <fmt:message bundle="${loc}" key="local.placeholder.password" var="password"/>
  
  <fmt:message bundle="${loc}" key="local.game.football" var="football"/>
  <fmt:message bundle="${loc}" key="local.game.basketball" var="basketball"/>
  <fmt:message bundle="${loc}" key="local.game.hockey" var="hockey"/>
  <fmt:message bundle="${loc}" key="local.game.gamekind" var="gamekind"/> 
  <fmt:message bundle="${loc}" key="local.table.game.addnewgame" var="addnewgame"/>
  <fmt:message bundle="${loc}" key="local.table.game.firstteam" var="firstteam"/>
  <fmt:message bundle="${loc}" key="local.table.game.secondteam" var="secondteam"/>
   <fmt:message bundle="${loc}" key="local.game.newgameadding" var="newgameadding"/>
  
  <fmt:message bundle="${loc}" key="local.table.game.time" var="time"/>
  <fmt:message bundle="${loc}" key="local.table.game.event" var="event"/>
  <fmt:message bundle="${loc}" key="local.table.game.home" var="home"/>
  <fmt:message bundle="${loc}" key="local.table.game.draw" var="draw"/>
  <fmt:message bundle="${loc}" key="local.table.game.away" var="away"/>
  

   <fmt:message bundle="${loc}" key="local.addgameerror.firstequalstwo" var="firstequalstwo"/>
   <fmt:message bundle="${loc}" key="local.addgameerror.badtime" var="badtime"/>
   <fmt:message bundle="${loc}" key="local.addgameerror.littletime" var="littletime"/>
   
    <fmt:message bundle="${loc}" key="local.addgameerror.k1isstring" var="k1isstring"/>
    <fmt:message bundle="${loc}" key="local.addgameerror.k1islittle" var="k1islittle"/>
    <fmt:message bundle="${loc}" key="local.addgameerror.kxisstring" var="kxisstring"/>
    <fmt:message bundle="${loc}" key="local.addgameerror.kxislittle" var="kxislittle"/>
    <fmt:message bundle="${loc}" key="local.addgameerror.k2isstring" var="k2isstring"/>
    <fmt:message bundle="${loc}" key="local.addgameerror.k2islittle" var="k2islittle"/>
 	<fmt:message bundle="${loc}" key="local.addgameerror.k1isempty" var="k1isempty"/>
 	<fmt:message bundle="${loc}" key="local.addgameerror.kxisempty" var="kxisempty"/>
 	<fmt:message bundle="${loc}" key="local.addgameerror.k2isempty" var="k2isempty"/>
  </head> 

  <body>   
  
    <!--  New navigation bar testing -->
    <nav class="navbar navbar-default">
    
   <c:if test="${add_game_error == 'firstequalstwo'}">          
 		 <span class="col-md-12 label label-danger">${firstequalstwo}</span>    
   </c:if> 
    <c:if test="${add_game_error == 'badtime'}">          
	 <span class="col-md-12 label label-danger">${badtime}</span>    
 </c:if>
  <c:if test="${add_game_error == 'k1isstring'}">          
	 <span class="col-md-12 label label-danger">${k1isstring}</span>    
 </c:if> 
 <c:if test="${add_game_error == 'k1islittle'}">          
	 <span class="col-md-12 label label-danger">${k1islittle}</span>    
 </c:if> 
   <c:if test="${add_game_error == 'k2isstring'}">          
	 <span class="col-md-12 label label-danger">${k2isstring}</span>    
 </c:if> 
 <c:if test="${add_game_error == 'k2islittle'}">          
	 <span class="col-md-12 label label-danger">${k2islittle}</span>    
 </c:if> 
   <c:if test="${add_game_error == 'k3isstring'}">          
	 <span class="col-md-12 label label-danger">${k3isstring}</span>    
 </c:if> 
 <c:if test="${add_game_error == 'k3islittle'}">          
	 <span class="col-md-12 label label-danger">${k3islittle}</span>    
 </c:if> 
  <c:if test="${add_game_error == 'k1isempty'}">          
	 <span class="col-md-12 label label-danger">${k1isempty}</span>    
 </c:if> 
   <c:if test="${add_game_error == 'kxisempty'}">          
	 <span class="col-md-12 label label-danger">${kxisempty}</span>    
 </c:if> 
   <c:if test="${add_game_error == 'k2isempty'}">          
	 <span class="col-md-12 label label-danger">${k2isempty}</span>    
 </c:if> 
	    
	    
	    
	    
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
   			          <a href="redirectToPersonalPage"><span class="label label-default navbar-form">${user.email}</span></a>    	   
   			  </form>
   	    </c:if>  
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${language} <span class="caret"></span></a>
          <ul class="dropdown-menu">
      		 <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToAddNewGamePage"/>   
             	 	<input type="hidden" name="command" value="LOCALIZATION_COMMAND"/>          		
             		<input type="hidden" name="local" value="en"/>
             		<input type="submit" class="btn btn-default btn-xs form-control" value="${englishLanguage}"/>
             	</form>
             </li>
              <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToAddNewGamePage"/>   
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

    <!--  Content -->
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">

    empty space

    </div>
    <div class="col-sm-8 text-left"> 
      <h1>${newgameadding}</h1>
            
      <div>
      		<form action="Controller" method="get">
      		<div class="row">
      			<div class="col-xs-4">
      			<label for="game-kind-select">${gamekind}</label>
      				<select class="form-control" name="game_kind" id="game-kind-select">
					  <option value="football">${football}</option>
					  <option value="basketball">${basketball}</option>
					  <option value="hockey">${hockey}</option>
					</select>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-xs-4">
      				<label for="game-first-team">${firstteam}</label>
      				<select id="game-first-team" name="team_1" class="form-control" >
      					<c:forEach var="team" items="${teams}" >      						
					 		 <option>${team.name}</option>
					 	 </c:forEach>					 
					</select>
      			</div>
      			
      			<div class="col-xs-4">
      				<label for="game-second-team">${secondteam}</label>
      				<select id="game-second-team" name="team_2" class="form-control">
      					<c:forEach var="team" items="${teams}" >
					 		 <option>${team.name}</option>
					 	 </c:forEach>	
					</select>
      			</div>
      		</div>	 
      			
      	 	<div class="row">	
	      		<div class="col-xs-4">
	      			<label for="game-second-team">${time}</label>
	      			<input name="date" type="datetime-local" class="form-control">
	      		</div>
	       </div>	
    		
			<div class="row">
			  <div class="col-xs-2">
			  <label for="game-koefficient-k1">${home}</label>
			    <input id="game-koefficient-k1" name="k1" type="text" class="form-control" placeholder="k1">
			  </div>
			  <div class="col-xs-2">
			   <label for="game-koefficient-kx">${draw}</label>
			    <input id="game-koefficient-kx" name="kx" type="text" class="form-control" placeholder="kx">
			  </div>
			  <div class="col-xs-2">
			   <label for="game-koefficient-k2">${away}</label>
			    <input id="game-koefficient-k2" name="k2" type="text" class="form-control" placeholder="k2">
			  </div>
			</div>	
			<div>			
			   <input type="hidden" name="command" value="ADD_NEW_GAME_COMMAND"/>
          			  		<input class="btn" type="submit" value="${addnewgame}" />
			</div>			
			</form>		
      </div>      
      <hr>       
    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>Empty</p>
      </div>
      <div class="well">
        <p>Empty</p>
      </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Epam 2017  </p>
</footer>
    <!-- Main jumbotron for a primary marketing message or call to action -->
</body>
</html>