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

    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"> 

    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/myStyle.css">

   <%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf" %>

     
  </head> 

  <body>  
    <!--  New navigation bar testing -->
     <div class="authorization-error-modal">
		<div class="alert alert-danger fade in">
		    <a href="#" class="close" data-dismiss="alert">x</a>
		    <strong class="authorizationErrorType"></strong>: <span class="authorizationErrorMessage"></span>
	   </div>
	</div>	

<nav class="navbar navbar-default">
  	<div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="redirectToIndexPage">${title}</a>
	    </div>
   	
	     <c:if test="${user == null }">
		    <form class="navbar-form navbar-right authorize-user-form">	     
		     <input name="login" required type="text" placeholder="${login}" class="form-control">	      
		 	 <input name="password" required type="password" placeholder="${password}" class="form-control">	
		    
		      <div class="form-group">
		      	<button type="submit" class="button signinbutton">${signinbutton}</button>
		      </div>
		     <div class="form-group">
		      <button onclick="redirectToRegistrationPage();" class="button signupbutton"><span class="glyphicon glyphicon-log-in"></span>${signupbutton}</button>
		      </div>  
		      
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
		<c:if test="${user != null }">   			
 			  <form action="Controller" method="get" class="navbar-form navbar-right"> 
 			 	    <input type="hidden" name="command" value="AUTHORIZATION_USER_SIGN_OUT_COMMAND"/>
 			 	    <button type="button" class="button btn-primary form-control" onclick="redirectToUserPersonalPage();">${user.email} <span class="badge">${user.cash}</span></button>
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
  			<%--  <%@ include file="/WEB-INF/pages/jspf_component/game_selector.jspf" %> --%>
  			<ul class="pager ul-nav">
			  <li>
			  <form action="Controller" method="get">
			  	  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
				  <input type="hidden" name="go_to_page" value="index"/>  
			  	  <a href="#" onclick="$(this).closest('form').submit();">${allgames}</a>
			  </form>
			  </li>
			  <li >
	  		  <form action="Controller" method="get">
				  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
				  <input type="hidden" name="gameKind" value="football"/>
				  <input type="hidden" name="go_to_page" value="index"/> 
			  	<a href="#" onclick="$(this).closest('form').submit();">${football}</a>
			  </form>
			  </li>
			  <li>
	  		  <form action="Controller" method="get">
				  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
				  <input type="hidden" name="gameKind" value="basketball"/>
				  <input type="hidden" name="go_to_page" value="index"/> 
				  	<a href="#" onclick="$(this).closest('form').submit();">${basketball}</a>
			  </form>
			</li>
			  <li>
  	  		  <form action="Controller" method="get">
				  <input type="hidden" name="command" value="GET_PAGE_WITH_GAMES_COMMAND"/>
				  <input type="hidden" name="gameKind" value="hockey"/>
				  <input type="hidden" name="go_to_page" value="index"/> 
				  	<a href="#" onclick="$(this).closest('form').submit();">${hockey}</a>
			  </form>
			  </li>
			</ul>
  	 </div>
  	     
    <div class="col-sm-8 text-left"> 
	     <c:if test="${ gameKind == 'FOOTBALL' }">
	      	<h2>${ soccer }</h2>
	      </c:if>
	      
	      <c:if test="${ gameKind == 'BASKETBALL' }">
			<h2>${ basketball }</h2>
		</c:if>
		
		 <c:if test="${ gameKind == 'HOCKEY' }">
			<h2>${ hockey }</h2>
		</c:if>
		
	      <c:if test="${ gameKind == null }">
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
		<c:forEach var="game" items="${games}" >	
				<tr class="tr-hover">				  
				  <td >
				  		${game.date}
				  </td>				  
				  <td>
				  		${game.firstTeam} -	${game.secondTeam}
				  </td>	 
				  <td>
				  	<form class="init-form">
			  			<input type="hidden" name="command" value="MAKE_RATE_INIT_COMMAND"/>
			  			<input type="hidden" name="game_id" value="${game.id}"/>
			  			<input type="hidden" name="first_team" value="${game.firstTeam}"/>
			  			<input type="hidden" name="second_team" value="${game.secondTeam}"/>
			  			<input type="hidden" name="game_kind" value="${game.gameKind}"/>
			  			<input type="hidden" name="rate_coefficient" value="${game.k1 }"/>	
			  			<input type="hidden" name="choice" value="t1" /> 		  			
			  			<button type="submit" class="btn btn-link">${game.k1}</button>
			  		</form>
				  </td>
				    <td>
				    <c:set var="currentGameKind">${game.gameKind}</c:set>
				    <c:if test="${currentGameKind == 'BASKETBALL'}">
				    <form>
				    	<button type="button" class="btn btn-link">-</button>
				    </form>
				    </c:if>
				    <c:if test="${currentGameKind != 'BASKETBALL'}">
				  	<form class="init-form">
			  			<input type="hidden" name="command" value="MAKE_RATE_INIT_COMMAND"/>
			  			<input type="hidden" name="game_id" value="${game.id}"/>
			  			<input type="hidden" name="first_team" value="${game.firstTeam}"/>
			  			<input type="hidden" name="second_team" value="${game.secondTeam}"/>
			  			<input type="hidden" name="game_kind" value="${game.gameKind}"/>
			  			<input type="hidden" name="rate_coefficient" value="${game.kx }"/>	
			  			<input type="hidden" name="choice" value="x" /> 		  			
			  			<button type="submit" class="btn btn-link">${game.kx}</button>
			  		</form>
			  		</c:if>
			  		<c:remove var="currentGameKind"/>
				  </td>
				    <td>
				  	<form class="init-form">
			  			<input type="hidden" name="command" value="MAKE_RATE_INIT_COMMAND"/>
			  			<input type="hidden" name="game_id" value="${game.id}"/>
			  			<input type="hidden" name="first_team" value="${game.firstTeam}"/>
			  			<input type="hidden" name="second_team" value="${game.secondTeam}"/>
			  			<input type="hidden" name="game_kind" value="${game.gameKind}"/>
			  			<input type="hidden" name="rate_coefficient" value="${game.k2 }"/>	
			  			<input type="hidden" name="choice" value="t2" /> 		  			
			  			<button type="submit" class="btn btn-link">${game.k2}</button>
			  		</form>
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
						<input type="hidden" name="go_to_page" value="index"/>		
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
									<input type="hidden" name="go_to_page" value="index"/>		
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
						<input type="hidden" name="go_to_page" value="index"/>	
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
      
      <hr>
      <h3>Test</h3>
      <p>Lorem ipsum...</p>
    </div>
    <div class="col-sm-2 sidenav hidden-make-rate">
      <div class="well">

      	 <form   class="sendRateToController">  			 
	          	<div class="error-modal">
					<div class="alert alert-danger fade in">
					    <a href="#" class="close" data-dismiss="alert">x</a>
					    <strong class="errorType"></strong> <p class="errorMessage"></p>
				   </div>
				</div>						
				<div class="success-modal">
					<div class="alert alert-success fade in">
					    <a href="#" class="close" data-dismiss="alert">x</a>
					    <strong class="errorType"></strong>
					    </div>
					</div>	
					
	        <p><span class="firstTeam"></span> - <span class="secondTeam"></span></p>      
	       
	        <p class="gameChoice"></p>
	        <p class=rateCoefficient></p>
	        <p>Ставка:<input type="text" name="rate_money" class="form-control input-sm"/></p>
	        
	        <input type="hidden" name="command" value="MAKE_RATE_COMMAND"/>
	        <input type="hidden" name="choice" class="gameChoice"/>
	        <input type="hidden" name="game_id" class="gameId"/>
	        <input type="hidden" name="rate_koefficient" class="rateCoefficient"/>
	        <input type="hidden" name="game_kind" value="${game_kind}"/>	        
	        <button type="submit" class="button signinbutton">Поставить</button>   
	        	   
	    </form>
	     <c:remove var="makeRateError" scope="session" />		
    
      </div>
      <div class="well">
     	<p>adv</p>
      </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
    <!-- Main jumbotron for a primary marketing message or call to action -->
<script type="text/javascript">


 var redirectToRegistrationPage = function(){
	 window.location.replace("registrationPage");
};



$(".authorize-user-form").submit(function(event){		
	var login    = event.currentTarget['login'].value;
	var password = event.currentTarget['password'].value;
	
	$.ajax({
		type:"POST",
		data:{
			command:"AUTHORIZATION_SIGN_IN_AJAX_COMMAND",
			login:login,
			password:password
		},
		url:"AJAXController",
		success:function(data){
               var json = JSON.parse(data);
            	
               var errorType = json["errorType"];
               var errorMessage = json["errorMessage"];  
               debugger;
               if(errorType === "authorizationerror"){
            	   
            	   errorType = "${authorizationerror}";
            	   
            	  if(errorMessage === "notregistred"){
            		  errorMessage = "${notregistred}";
            	  }
            	  if(errorMessage === "invalidpassword"){
            		  errorMessage = "${invalidpassword}";
            	  }	            	   
            	   
            	$(".authorizationErrorType").text(errorType);
            	$(".authorizationErrorMessage").text(errorMessage);	
            	$(".authorization-error-modal").css("display","block");
            	
            	setTimeout(function() {
            		  $(".authorization-error-modal").css("display","none");
            		}, 3000);
               }else if (errorType === "success"){
            	  
            	   if(json["userRole"] === "ADMIN"){
            		   window.location.replace("redirectToAdminPage");
            	   }
            	   if(json["userRole"] === "BOOKMAKER"){
            		   window.location.replace("redirectToBookmakerPage");
            	   }
            	   if(json["userRole"] === "USER"){
            		   window.location.replace("redirectToUserPage");
            	   }            	     	   
               }
		}
	});				
      event.preventDefault(); 
});

$(".init-form").submit(function(event) {
  var command		  = event.currentTarget['command'].value;
  var game_id 		  = event.currentTarget['game_id'].value;     
  var first_team 	  = event.currentTarget['first_team'].value;      
  var second_team 	  = event.currentTarget['second_team'].value;
  var game_kind		  = event.currentTarget['game_kind'].value;      
  var rateCoefficient = event.currentTarget['rate_coefficient'].value;       
  var gameChoice 	  = event.currentTarget['choice'].value;
	
	$(".gameChoice").html(gameChoice);
    $(".gameChoice").val(gameChoice);
	$(".firstTeam").html(first_team);
	$(".firstTeam").val(first_team);
	$(".secondTeam").html(second_team);
	$(".secondTeam").val(second_team);
	$(".gameId").val(game_id);
	$(".rateCoefficient").val(rateCoefficient);
	$(".rateCoefficient").html(rateCoefficient);   		
	
	$(".sendRateToController").show();
	$(".error-modal").hide();
	$(".success-modal").hide();
	$(".hidden-make-rate").css("visibility","visible");
	
  event.preventDefault(); 
 });

$(".sendRateToController").submit(function(event) {
	
	var showErrorAtView = function(errorType, errorMessage){
     	$(".error-modal").show();
    	$(".errorType").text(errorType);
    	$(".errorMessage").text(errorMessage);
	}
	
     var rateMoney		 = event.currentTarget[0].value;
   	 var command  		 = event.currentTarget[1].value;
	 var choice   		 = event.currentTarget[2].value;
	 var gameId    		 = event.currentTarget[3].value;
	 var rateCoefficient = event.currentTarget[4].value;
	 
	
	if("${user.id}".length === 0){
		errorType = "${makerateerror}";
    	errorMessage = "${emptyuser}";
    	
    	showErrorAtView(errorType, errorMessage);
		
	} else if(isNaN(parseFloat(rateMoney)) === true){	
		errorType = "${makerateerror}";
    	errorMessage = "${ratenotanumber}";
    	
    	showErrorAtView(errorType, errorMessage);
    	
	} else if(rateMoney < 0 ){			 
		 errorType = "${makerateerror}";
		 errorMessage = "${lessthanzero}";
		 
		 showErrorAtView(errorType, errorMessage);
    	
	} else{				 
		$.ajax({
			type:"POST",
			data:{command:"MAKE_RATE_AJAX_COMMAND",
				  choice:choice,
				  gameId:gameId,
				  rateCoefficient:rateCoefficient,
				  rateMoney:rateMoney
				 },
			url:"AJAXController",
            success : function(data) {
               var json = JSON.parse(data);
            	
               var errorType = json["errorType"];
               var errorMessage = json["errorMessage"];  				
				
               if(errorType === "makerateerror"){
            	   errorType = "${makerateerror}";
            	   
	           if(errorMessage === "nomoney"){
	            		errorMessage = "${nomoney}";
	           }
	            	
	            	showErrorAtView(errorType, errorMessage);
	            	
               } else if(errorType === "ok"){	      
            		$(".error-modal").hide();
            		$(".success-modal").show();
            		
            		errorType = "${makeratesuccess}";	            		
	            	$(".errorType").text(errorType);     		            	
	            	
	            	setTimeout(function() {
	            		  $(".hidden-make-rate").css("visibility","hidden");
	            		}, 5000);
            	}
            }					 
		});
	 }
		event.preventDefault();		
});	

function redirectToUserPersonalPage(){
	  window.location.replace("redirectToPersonalPage");
}
</script>
    <script src="bootstrap-3.3.7-dist/js/myScripts.js"></script>    
</body>
</html>