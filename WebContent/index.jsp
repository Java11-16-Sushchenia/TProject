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
    <title>Totalizator</title>

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
   <nav class="navbar navbar-default">
                <c:if test="${makeRateSuccess == 'betisplaced'}"> 	 		
		 		 <div class="bs-example">
				    <div class="alert alert-success fade in">
				        <a href="#" class="close" data-dismiss="alert">×</a>
				        <strong>${makeratesuccess}!</strong>
				    </div>
				</div>	
				<c:remove var="makeRateSuccess" scope="session" /> 				 	
		   	 </c:if>
        <c:if test="${userAuthorizationError != null}">          
	   		 <span class="col-md-12 label label-danger"><c:out value="${userAuthorizationError}"></c:out></span>    
	    </c:if> 
  <div class="container-fluid">   
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">${title}</a>
    </div>
 
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
		
              <c:if test="${user == null }">
          <form action="Controller" method="get" class="navbar-form navbar-left">
	            <div class="form-group">
	            <input type="hidden" name="command" value="authorization_user_command"/>
	              <input name="login" type="text" placeholder="${login}" class="form-control input-sm">
	            </div>
	            <div class="form-group">
	              <input name="password" type="password" placeholder="${password}" class="form-control input-sm">
	            </div>
              <button type="submit" class="btn btn-sm btn-success">
    			<span class="glyphicon glyphicon-log-in"></span>${signinbutton}
             </button>          
            <a href="registrationPage"> 
	            <button type="button" class="btn btn-sm btn-warning">
	           		<span class="glyphicon glyphicon-plus"></span>${signupbutton}
	            </button>
            </a>         	
          </form>
         </c:if>        
     	<c:if test="${user != null }">   			
   			  <form action="Controller" method="get" class="navbar-form navbar-left"> 
   			   <input type="hidden" name="command" value="log_out_command"/>
   			  		<input class="navbar-form navbar-right" type="submit" value="${signoutbutton}" /> 
   			          <a href="redirectToPersonalPage"><span class="label label-primary navbar-form">${user.email}</span></a>    	   
   			  </form>
   	    </c:if>
      
        <li><a href="#">Link</a></li>

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${language} <span class="caret"></span></a>
          <ul class="dropdown-menu">
      		 <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToIndexPage"/>   
             	 	<input type="hidden" name="command" value="LOCALIZATION_COMMAND"/>          		
             		<input type="hidden" name="local" value="en"/>
             		<input type="submit" class="btn btn-default btn-xs form-control" value="${englishLanguage}"/>
             	</form>
             </li>
              <li>
             	<form action="Controller">             	 	
             	 	<input type="hidden" name="go_to_page" value="redirectToIndexPage"/>   
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
  			 <%@ include file="/WEB-INF/pages/jspf_component/game_selector.jspf" %>
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
      <div>
      		<table class="table table-striped table-hover table-bordered">
	<thead>
		<td>${time }</td>
		<td>${event}</td>
		<td>${home }</td>
		<td>${draw }</td>
		<td>${away }</td>	
	</thead>
		<c:forEach var="game" items="${games}" >	<%-- // id для каждой строки --%>		
				<tr>				  
				  <td >
				  		${game.date}
				  </td>				  
				  <td>
				  		${game.firstTeam} -	${game.secondTeam}
				  </td>	 
				  <td>
				  	<form action="Controller">
			  			<input type="hidden" name="command" value="MAKE_RATE_INIT_COMMAND"/>
			  			<input type="hidden" name="game_id" value="${game.id}"/>
			  			<input type="hidden" name="first_team" value="${game.firstTeam}"/>
			  			<input type="hidden" name="second_team" value="${game.secondTeam}"/>
			  			<input type="hidden" name="game_kind" value="${game.gameKind}"/>
			  			<input type="hidden" name="rate_koefficient" value="${game.k1 }"/>	
			  			<input type="hidden" name="choice" value="t1" />			  			
			  			<button type="submit" class="btn btn-link">${game.k1}</button>
			  		</form>
				  </td>
				    <td>
				    <c:if test="${game.gameKind == 'basketball'}">
				    <form>
				    	<button type="button" class="btn btn-link">-</button>
				    </form>
				    </c:if>
				    <c:if test="${game.gameKind != 'basketball'}">
				  	<form action="Controller">
			  			<input type="hidden" name="command" value="MAKE_RATE_INIT_COMMAND"/>
			  			<input type="hidden" name="game_id" value="${game.id}"/>
			  			<input type="hidden" name="first_team" value="${game.firstTeam}"/>
			  			<input type="hidden" name="second_team" value="${game.secondTeam}"/>
			  			<input type="hidden" name="game_kind" value="${game.gameKind}" />
			  			<input type="hidden" name="rate_koefficient" value="${game.kx}"/>		
			  			<input type="hidden" name="choice" value="x" />		  			
			  			<button type="submit" class="btn btn-link">${game.kx}</button>
			  		</form>
			  		</c:if>
				  </td>
				    <td>
				  	<form action="Controller">
			  			<input type="hidden" name="command" value="MAKE_RATE_INIT_COMMAND"/>
			  			<input type="hidden" name="game_id" value="${game.id}"/>
			  			<input type="hidden" name="first_team" value="${game.firstTeam}"/>
			  			<input type="hidden" name="second_team" value="${game.secondTeam}"/>
			  			<input type="hidden" name="rate_koefficient" value="${game.k2}"/>	
  						<input type="hidden" name="game_kind" value="${game.gameKind}" />
  						<input type="hidden" name="choice" value="t2" />
			  			<button type="submit" class="btn btn-link">${game.k2}</button>					
			  		</form>
				  </td>
				</tr>			
		</c:forEach>
		</table> 
      </div>
      
      <hr>
      <h3>Test</h3>
      <p>Lorem ipsum...</p>
    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">

       <c:if test="${choice != null}">
      	 <form action="Controller">  
     		
              <c:if test="${makeRateError == 'emptyuser'}"> 	 		
		 		 <div class="bs-example">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">×</a>
				        <strong>${makerateerror}!</strong> ${emptyuser}
				    </div>
				</div>						 	
		   	 </c:if>
		
		   	  <c:if test="${makeRateError == 'nomoney'}">   
 				<div class="bs-example">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">×</a>
				        <strong>${makerateerror}!</strong> ${nomoney}
				    </div>
				</div>		 	
		   	 </c:if>
		   	 
		   	 <c:if test="${makeRateError == 'emptyrate'}">          
 				<div class="bs-example">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">×</a>
				        <strong>${makerateerror}!</strong> ${emptyrate}
				    </div>
				</div> 
			</c:if>
			<c:if test="${makeRateError == 'ratenotanumber'}">          
 				<div class="bs-example">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">×</a>
				        <strong>${makerateerror}!</strong> ${ratenotanumber}
				    </div>
				</div>			 			
			</c:if>			
			<c:if test="${makeRateError == 'unknownerror'}">          
 				<div class="bs-example">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">×</a>
				        <strong>${makerateerror}!</strong> ${unknownerror}
				    </div>
				</div>		 	
			</c:if>         
			 
	         
	        <p>${first_team} - ${second_team}</p>        
	        <p>${choice}</p>
	        <p>Ставка:<input type="text" name="rate_money" class="form-control input-sm"/>${rate_koefficient}</p>
	        
	        <input type="hidden" name="command" value="MAKE_RATE_COMMAND"/>
	        <input type="hidden" name="choice" value="${choice}"/>
	        <input type="hidden" name="game_id" value="${game_id}"/>
	        <input type="hidden" name="rate_koefficient" value="${rate_koefficient}"/>
	        <input type="hidden" name="game_kind" value="${game_kind}"/>
	        
	        <input type="submit" value="Поставить"/>        
	    </form>
	     <c:remove var="makeRateError" scope="session" />		
     </c:if>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
    <!-- Main jumbotron for a primary marketing message or call to action -->
</body>
</html>