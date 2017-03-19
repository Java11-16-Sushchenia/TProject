;
	function validateForm(){
		var result = true;

		var PWD_NOT_EQUAL = "The 'Password' and 'Confirm password' fields do not match.";

		var password         = document.forms["registrationForm"]["password"];
		var confirmPassword  = document.forms["registrationForm"]["confirmPassword"];

		var passwordErrorSpan = document.getElementById("passwordError");

		if(password.value !== confirmPassword.value){

			passwordErrorSpan.innerHTML = PWD_NOT_EQUAL;
			password.value = "";
			confirmPassword.value = "";

			result = false;
		}
		return result;
	}

	var i = 0; // for extra email id

	function increment(){
		i += 1;
	}

	function removeElement(parentDiv, childDiv){

		if (childDiv == parentDiv){
			alert("The parent div cannot be removed.");
		}else if (document.getElementById(childDiv)){
			var child = document.getElementById(childDiv);
			var parent = document.getElementById(parentDiv);
			parent.removeChild(child);
		}else{
			alert("Child div has already been removed or does not exist.");

			return false;
		}
	}

    function addEmailFunction(){

		increment();

		var parentDiv = document.createElement('div');
			parentDiv.setAttribute("class","form-group");
			parentDiv.setAttribute("id","parent-div"+i);
			parentDiv.setAttribute("style","margin-top:10px;")

		var label = document.createElement('label');
			label.setAttribute("class","control-label col-sm-2");
			label.innerHTML = "Email "+i;

		parentDiv.appendChild(label);

		var inputParentDiv = document.createElement('div');
			inputParentDiv.setAttribute("class","col-sm-8");

		var newElement = document.createElement("INPUT");

			newElement.setAttribute("type", "email");
			newElement.setAttribute("class", "col-sm-10");
			newElement.setAttribute("name", "email"+i);
			newElement.setAttribute("pattern", "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			newElement.setAttribute("placeholder", "Email "+i);
			newElement.required = true;

		var remover = document.createElement("span");
			remover.setAttribute("class", "glyphicon glyphicon-remove");
			remover.setAttribute("onclick", "removeElement('myForm','parent-div" + i + "')");

		inputParentDiv.appendChild(newElement);

		inputParentDiv.setAttribute("id", "id_" + i);

		parentDiv.appendChild(inputParentDiv);
		parentDiv.appendChild(remover);

		document.getElementById("myForm").appendChild(parentDiv);
	}
