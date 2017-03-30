$(".go-to-registration-page").click(function(){
	 window.location.replace("registrationPage");
});

$(".authorize-user-form").submit(function(event){		
	var command  = event.currentTarget['command'].value;
	var login    = event.currentTarget['login'].value;
	var password = event.currentTarget['password'].value;
	
	$.ajax({
		type:"POST",
		data:{
			command:command,
			login:login,
			password:password
		},
		url:"AJAXController",
		success:function(data){
               var json = JSON.parse(data);
            	
               var errorType = json["errorType"];
               var errorMessage = json["errorMessage"];  
               
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
               }else if (errorType === "ok"){
            	   
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
  console.log(event.currentTarget);
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


function setLanguage(goToPage,local){		
	$.get(
		    "Controller",
		    {
		    	 command : "CHANGE_LOCALIZATION_COMMAND",			    
			     go_to_page : goToPage,
			     local:local			    
		    },
		    function(data) {
		       window.location.replace(goToPage);
		    }
		);
}

function redirectToUserPersonalPage(){
	  window.location.replace("redirectToPersonalPage");
}