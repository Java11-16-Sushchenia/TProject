<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container col-md-6">
	<div class="row">
		<form class="form-horizontal" action="RegisteredNewUserServlet" method="get">
		
		 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Логин</label>
		    <div class="col-sm-4">
		      <input name="login" type="text" class="form-control" placeholder="login">
		    </div>
		  </div>
		  
				
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Почта</label>
		    <div class="col-sm-4">
		      <input name="email" type="email" class="form-control" id="inputEmail3" placeholder="email">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">Пароль</label>
		    <div class="col-sm-4">
		      <input name="password" type="password" class="form-control" id="inputPassword3" placeholder="password">
		    </div>
		  </div>
		  		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Зарегистрироваться</button>
		    </div>
		  </div>
		</form>
	</div>
</div>

</body>
</html>