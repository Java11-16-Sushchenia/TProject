<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding='UTF-8'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content='text/html; charset=UTF-8'>
<title>404</title>

<script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/myStyle.css">

<%@ include file="/WEB-INF/pages/jspf_component/local_include.jspf"%>
</head>
<body>
	<h1 align="center">404 ${notfound}</h1>

	<div class="at-main">
		<a href="redirectToIndexPage"> ${atmain} </a>
	</div>

	<div class="text-center">
		<a href="redirectToIndexPage"> <img
			src="bootstrap-3.3.7-dist/images/cat.jpg" class="img-responsive"
			alt="Index page"></a>
	</div>
</body>
</html>