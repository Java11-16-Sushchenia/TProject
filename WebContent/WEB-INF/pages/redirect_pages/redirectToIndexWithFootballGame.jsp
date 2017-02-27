<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" 
      uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<body>
	<c:redirect url="/Controller">
		 <c:param name="command" value="GET_GAMES_BY_KIND_COMMAND"/>		
		 <c:param name="game_kind" value="FOOTBALL"/>
		 <c:param name="makeRateError" value="${makeRateError}"/>
		 <c:param name="go_to_page" value="index"/>
	</c:redirect>
</body>
</html>