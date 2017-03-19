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
 
 	   <%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf" %>
 
  </head> 

  <body>   
  
    <!--  New navigation bar testing -->
  
    
  <%-- this in nav bar
    <c:if test="${add_game_error == 'firstequalstwo'}">          
 		<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${firstequalstwo}
		    </div>
		</div>		    
   </c:if> 
    <c:if test="${add_game_error == 'badtime'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${badtime}
		    </div>
		</div>   
  </c:if>
  <c:if test="${add_game_error == 'k1isstring'}">          
		 <div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k1isstring}
		    </div>
		</div>      
 </c:if> 
 <c:if test="${add_game_error == 'k1islittle'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k1islittle}
		    </div>
		</div>   
 </c:if> 
   <c:if test="${add_game_error == 'k2isstring'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k2isstring}
		    </div>
		</div>     
 </c:if> 
 <c:if test="${add_game_error == 'k2islittle'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k2islittle}
		    </div>
		</div>     
 </c:if> 
   <c:if test="${add_game_error == 'k3isstring'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k3isstring}
		    </div>
		</div>   
 </c:if> 
 <c:if test="${add_game_error == 'k3islittle'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k3islittle}
		    </div>
		</div>   
 </c:if> 
  <c:if test="${add_game_error == 'k1isempty'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k1isempty}
		    </div>
		</div>     
 </c:if> 
   <c:if test="${add_game_error == 'kxisempty'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${kxisempty}
		    </div>
		</div>   
 </c:if> 
   <c:if test="${add_game_error == 'k2isempty'}">          
	  	<div class="bs-example">
		    <div class="alert alert-danger fade in">
		        <a href="#" class="close" data-dismiss="alert">×</a>
		        <strong>${addgameerror}!</strong> ${k2isempty}
		    </div>
		</div>    
 </c:if> 
	    
	    <c:if test="${add_game_error != null}">
	    	 <c:remove var="add_game_error" scope="session" />		
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
 
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> --%>


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